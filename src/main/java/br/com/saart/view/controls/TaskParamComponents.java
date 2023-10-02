package br.com.saart.view.controls;

import br.com.saart.task.exportreport.SQLOperations;
import br.com.saart.task.exportreport.TaskParam;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class TaskParamComponents {

    private static final String LIST_SEPARATOR = ",";

    @SneakyThrows
    public static Map<String, Object> showParamDialog(List<TaskParam> params) {
        if (CollectionUtils.isNotEmpty(params)) {
            Dialog<Map<String, Object>> dialog = new Dialog<>();
            dialog.setTitle("Parâmetros");
            dialog.setHeaderText("Informe os parâmetros a seguir para continuar.");

            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Components.icon.getInputStream()));

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            preencherGridParametros(grid, params, 0);

            ScrollPane scroll = new ScrollPane(grid);
            scroll.setFitToWidth(true);

            dialog.getDialogPane().setMaxHeight(600);
            dialog.getDialogPane().setContent(scroll);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            final Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
            okButton.addEventFilter(ActionEvent.ACTION, event -> {
                if (!isValidTaskParams(grid, 0)) {
                    event.consume();
                }
            });
            okButton.setPrefWidth(120);

            dialog.setResultConverter(button -> {
                if (ButtonType.OK.equals(button)) {
                    return converterGridParametros(grid, 0);
                }
                return null;
            });

            return dialog.showAndWait().orElse(null);
        } else {
            return Collections.emptyMap();
        }
    }

    public static void preencherGridParametros(GridPane grid, List<TaskParam> params, int beginLine) {
        for (int i = 0; i < params.size(); i++) {
            TaskParam param = params.get(i);

            Label label = new Label(param.getDescription());
            label.setPrefWidth(200);
            label.setWrapText(true);

            if (param.isRequired()) {
                label.setText(label.getText() + " *");
                label.setStyle("-fx-font-weight: bold");
            }

            Region region = getRegion(param);
            region.setPrefWidth(200);
            region.setUserData(param);

            grid.add(label, 0, i + beginLine);
            grid.add(region, 1, i + beginLine);
        }

    }

    public static Map<String, Object> converterGridParametros(GridPane grid, int beginLine) {
        Map<String, Object> result = new HashMap<>();
        grid.getChildren().stream().filter(node -> !(node instanceof Label))
                .skip(beginLine)
                .map(Region.class::cast).forEach(region -> {
                    TaskParam param = ((TaskParam) region.getUserData());
                    if(param != null) {
                        String key = param.getParam();
                        Object value = getObject(region);
                        if (param.getParamClass().isComparison() && value != null) {
                            key += "_" + ((Object[]) value)[0];
                            value = ((Object[]) value)[1];
                        }
                        result.put(key, value);
                    }
                });
        return result;
    }

    private static Region getRegion(TaskParam param) {
        TaskParam.ParamClass paramClass = param.getParamClass();
        boolean hasDefault = param.getDefaultValue() != null;

        switch (paramClass) {
            case DATE_AS_TIMESTAMP -> {
                DatePicker datePicker = new DatePicker();
                if (hasDefault) {
                    datePicker.setValue((LocalDate) param.getDefaultValue());
                }
                return datePicker;
            }
            case BOOLEAN -> {
                CheckBox checkBox = new CheckBox();
                if (hasDefault) {
                    checkBox.setSelected((Boolean) param.getDefaultValue());
                }
                return checkBox;
            }
            case DOUBLE_COMPARISON -> {
                ChoiceBox<String> choiceBox = new ChoiceBox<>(
                        FXCollections.observableList(SQLOperations.NUMERIC.getLabels()));
                choiceBox.setStyle("-fx-background-radius: 3 0 0 3");

                TextField textField = new TextField();
                textField.setStyle("-fx-background-radius: 0 3 3 0");

                if (hasDefault) {
                    Object[] defaults = (Object[]) param.getDefaultValue();
                    choiceBox.getSelectionModel().select((int) defaults[0]);
                    textField.setText(String.valueOf(defaults[1]));
                }

                return new HBox(choiceBox, textField);
            }
            default -> {
                TextField textField = new TextField();
                if (hasDefault) {
                    textField.setText((String) param.getDefaultValue());
                }
                return textField;
            }
        }
    }

    private static Object getObject(Region region) {
        TaskParam.ParamClass paramClass = ((TaskParam) region.getUserData()).getParamClass();
        switch (paramClass) {
            case DATE_AS_TIMESTAMP:
                LocalDate date = ((DatePicker) region).getValue();
                return date != null ? Timestamp.valueOf(date.atTime(0, 0)) : null;
            case BOOLEAN:
                return ((CheckBox) region).isSelected();
            case DOUBLE_COMPARISON: {
                HBox hBox = (HBox) region;
                String text = ((TextField) hBox.getChildren().get(1)).getText();
                if (isBlank(text)) {
                    return null;
                }
                String operator = SQLOperations.NUMERIC.getOperators()
                        .get(((ChoiceBox<?>) hBox.getChildren().get(0)).getSelectionModel().getSelectedIndex());
                double value = Double.parseDouble(text.replace(",", "."));
                return new Object[]{operator, value};
            }
            case INT_ARRAY: {
                String text = ((TextField) region).getText();
                return isBlank(text) ? ArrayUtils.EMPTY_INT_ARRAY
                        : Arrays.stream(StringUtils.split(((TextField) region).getText(), LIST_SEPARATOR))
                        .mapToInt(Integer::parseInt).toArray();
            }
            case INT_AS_STRING_ARRAY:
            case STRING_ARRAY: {
                String text = ((TextField) region).getText();
                return isBlank(text) ? ArrayUtils.EMPTY_STRING_ARRAY
                        : Arrays.stream(StringUtils.split(((TextField) region).getText(), LIST_SEPARATOR))
                        .toArray(String[]::new);
            }
            case INT_AS_BIG_DECIMAL_ARRAY: {
                String text = ((TextField) region).getText();
                return isBlank(text) ? new BigDecimal[0]
                        : Arrays.stream(StringUtils.split(((TextField) region).getText(), LIST_SEPARATOR))
                        .map(BigDecimal::new).toArray(BigDecimal[]::new);
            }
            case INT: {
                String text = ((TextField) region).getText();
                return isBlank(text) ? 0 : Integer.parseInt(text);
            }
            case STRING:
            default:
                String text = ((TextField) region).getText();
                return isBlank(text) ? null : text;
        }
    }

    private static boolean isValid(Region region) {
        TaskParam.ParamClass paramClass = ((TaskParam) region.getUserData()).getParamClass();
        boolean required = ((TaskParam) region.getUserData()).isRequired();

        switch (paramClass) {
            case DATE_AS_TIMESTAMP:
                return !required || ((DatePicker) region).getValue() != null;
            case BOOLEAN:
                return true;
            case INT_AS_BIG_DECIMAL_ARRAY:
            case INT_AS_STRING_ARRAY:
            case INT_ARRAY: {
                String text = ((TextField) region).getText();
                return (isBlank(text) && !required) ||
                        (isNotBlank(text) && Arrays.stream(StringUtils.split(text, LIST_SEPARATOR))
                                .allMatch(s -> s != null && s.matches("\\d+")));
            }
            case DOUBLE_COMPARISON: {
                HBox box = (HBox) region;
                boolean selected =
                        ((ChoiceBox<?>) box.getChildren().get(0)).getSelectionModel().getSelectedIndex() >= 0;
                String text = ((TextField) box.getChildren().get(1)).getText();
                return (isBlank(text) && !required) ||
                        (selected && isNotBlank(text) && text.matches("\\d+([.,]\\d+)?"));
            }
            case INT: {
                String text = ((TextField) region).getText();
                return (isBlank(text) && !required) || (isNotBlank(text) && text.matches("\\d+"));
            }
            case STRING:
            case STRING_ARRAY:
            default:
                return !required || isNotBlank(((TextField) region).getText());
        }
    }

    public static boolean isValidTaskParams(GridPane grid, int beginLine) {

        boolean valid = grid.getChildren().stream().filter(node -> !(node instanceof Label))
                .skip(beginLine).map(Region.class::cast).allMatch(TaskParamComponents::isValid);

        if (!valid) {
            Components.alert(AlertType.ERROR, "Erro", "Não é possível continuar",
                    "Há parâmetros obrigatórios não preenchidos ou parâmetros preenchidos incorretamente, verifique.",
                    false);
        }

        return valid;
    }

}
