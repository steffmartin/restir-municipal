package br.com.saart.view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class Components {

    public static final ClassPathResource icon = new ClassPathResource("/view/img/ico.png");

    public static final List<Charset> standardCharsets = List.of(
            StandardCharsets.ISO_8859_1,
            StandardCharsets.US_ASCII,
            StandardCharsets.UTF_8,
            StandardCharsets.UTF_16BE,
            StandardCharsets.UTF_16LE,
            StandardCharsets.UTF_16
    );

    public static FileChooser fileChooser(String initialFile, String title,
                                          String... descriptionAndExtension) {
        FileChooser fileChooser = new FileChooser();
        if (initialFile != null) {
            File initialPath = new File(initialFile);
            if (initialPath.exists()) {
                fileChooser.setInitialDirectory(initialPath.getParentFile());
                fileChooser.setInitialFileName(initialPath.getName());
            }
        }
        fileChooser.setTitle(title);

        for (int i = 0; i < descriptionAndExtension.length; i += 2) {
            try {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                        descriptionAndExtension[i], descriptionAndExtension[i + 1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                //Ignore
            }
        }

        return fileChooser;
    }

    public static DirectoryChooser directoryChooser(String initialDir, String title) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File initialPath = new File(initialDir);
        if (initialPath.exists()) {
            directoryChooser.setInitialDirectory(initialPath);
        }
        directoryChooser.setTitle(title);
        return directoryChooser;
    }

    @SneakyThrows
    public static Optional<ButtonType> alert(AlertType type, String title, String header, String body,
                                             boolean wait) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(body);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setPrefWidth(120);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(icon.getInputStream()));

        if (wait) {
            return alert.showAndWait();
        } else {
            alert.show();
            return Optional.empty();
        }
    }

    @SneakyThrows
    public static Optional<String> passwordDialog(String title, String header, String label) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(icon.getInputStream()));

        PasswordField password = new PasswordField();
        HBox h = new HBox(10, new Label(label + ":"), password);
        h.setAlignment(Pos.CENTER);
        dialog.getDialogPane().setContent(h);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(btn -> btn == ButtonType.OK ? password.getText() : null);
        ((Button) dialog.getDialogPane().lookupButton(ButtonType.OK)).setPrefWidth(120);

        return dialog.showAndWait();
    }

    @SneakyThrows
    public static Optional<?> choiceDialog(String title, String header, List<?> choices) {
        ChoiceDialog<?> choiceDialog = new ChoiceDialog<>(null, choices);
        choiceDialog.setTitle(title);
        choiceDialog.setHeaderText(header);

        Stage stage = (Stage) choiceDialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(icon.getInputStream()));
        ((Button) choiceDialog.getDialogPane().lookupButton(ButtonType.OK)).setPrefWidth(120);

        return choiceDialog.showAndWait();
    }

    @SneakyThrows
    public static void html(String title, String htmlText) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(icon.getInputStream()));

        WebView webView = new WebView();
//    webView.setBlendMode(BlendMode.DARKEN);
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(htmlText);

        VBox v = new VBox(10, webView);
        v.setAlignment(Pos.CENTER);
        dialog.getDialogPane().setContent(v);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        ((Button) dialog.getDialogPane().lookupButton(ButtonType.CLOSE)).setPrefWidth(120);
        dialog.show();
    }

    public static void exception(Throwable e) {
        exception("Ocorreu um erro inesperado.",
                StringUtils.firstNonBlank(e.getMessage(), "Erro inesperado"),
                ExceptionUtils.getStackTrace(e));
    }

    @SneakyThrows
    public static void exception(String message, String text, String debugText) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(icon.getInputStream()));

        alert.setContentText("Detalhamento: ");

        TextArea textArea = new TextArea(text);
        textArea.setPrefRowCount(5);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        TextArea debutArea = new TextArea(debugText);
        textArea.setPrefRowCount(5);
        debutArea.setEditable(false);
        debutArea.setWrapText(true);
        GridPane.setVgrow(debutArea, Priority.ALWAYS);
        GridPane.setHgrow(debutArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setVgap(5);
        expContent.add(textArea, 0, 0);
        expContent.add(debutArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        alert.getDialogPane().setExpanded(true);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setPrefWidth(120);

        alert.showAndWait();
    }

    public static TextFormatter<String> maxSizeFormatter(int max) {
        return new TextFormatter<>(
                change -> StringUtils.length(change.getControlNewText()) <= max ? change : null);
    }

    public static void autoCompleteComboBox(ComboBox<?> comboBox) {
        StringConverter converter = new StringConverter<>() {
            @Override
            public String toString(Object object) {
                return object != null ? object.toString() : "";
            }

            @Override
            public Object fromString(String string) {
                return StringUtils.isNotBlank(string) ? comboBox.getItems().stream()
                        .filter(it -> StringUtils.containsIgnoreCase(it.toString(), string))
                        .findFirst().orElse(null) : null;
            }
        };

        comboBox.setConverter(converter);
        comboBox.setEditable(true);
        comboBox.setOnShowing(e -> Optional.ofNullable((ComboBoxListViewSkin<?>) comboBox.getSkin())
                .map(skin -> (ListView<?>) skin.getPopupContent())
                .ifPresent(view -> view.scrollTo(comboBox.getSelectionModel().getSelectedIndex())));
    }

    public static void configuraColuna(Class<?> clazz, TableView<?> table,
                                       TableColumn<?, String> col, String property) {
        configuraColuna(clazz, table, col, property, null);
    }

    @SneakyThrows
    public static void configuraColuna(Class<?> clazz, TableView<?> table,
                                       TableColumn<?, String> col, String property, String[] options) {
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        col.setCellFactory(options == null ?
                TextFieldTableCell.forTableColumn() :
                ComboBoxTableCell.forTableColumn(options));
        Method setter = clazz.getMethod(
                "set" + org.springframework.util.StringUtils.capitalize(property), String.class);
        col.setOnEditCommit(e -> {
            try {
                setter.invoke(e.getRowValue(), e.getNewValue());
                table.getFocusModel().focusRightCell();
            } catch (Exception ex) {
                Components.alert(AlertType.ERROR, "Erro", "Dados inválidos",
                        "Verifique o valor inserido.", false);
                table.refresh();
            }
            table.requestFocus();
        });
    }

}
