package br.com.saart.view.principal;

import atlantafx.base.controls.Spacer;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class NavTreeCell extends TreeCell<Navitem> {

    private static final PseudoClass GROUP = PseudoClass.getPseudoClass("group");

    private final HBox root;
    private final Label titleLabel;
    private final Node arrowIcon;
    private final Label tagLabel;

    public NavTreeCell(TreeView<Navitem> treeView) {
        super();

        titleLabel = new Label();
        titleLabel.setGraphicTextGap(10);
        titleLabel.getStyleClass().add("title");

        arrowIcon = new FontIcon();
        arrowIcon.getStyleClass().add("arrow");

        tagLabel = new Label("novo");
        tagLabel.getStyleClass().add("tag");

        root = new HBox();
        root.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().setAll(titleLabel, new Spacer(), arrowIcon, tagLabel);
        root.setCursor(Cursor.HAND);
        root.getStyleClass().add("container");
        root.setMaxWidth(240);

        root.setOnMouseClicked(e -> {
            TreeItem<Navitem> item = getTreeItem();

            if (item.getValue().grupo() && e.getButton() == MouseButton.PRIMARY) {
                item.setExpanded(!item.isExpanded());
            }
        });

        setGraphic(root);
        getStyleClass().add("nav-tree-cell");
    }

    @Override
    protected void updateItem(Navitem item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setGraphic(null);
            titleLabel.setText(null);
            titleLabel.setGraphic(null);
        } else {
            setGraphic(root);

            titleLabel.setText(item.label());
            if (item.icon() != null) {
                titleLabel.setGraphic(item.icon());
            }

            pseudoClassStateChanged(GROUP, item.grupo());

            arrowIcon.setVisible(item.grupo());
            arrowIcon.setManaged(item.grupo());

            tagLabel.setVisible(item.novo());
            tagLabel.setManaged(item.novo());
        }
    }
}
