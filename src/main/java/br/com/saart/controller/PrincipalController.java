package br.com.saart.controller;

import br.com.saart.view.principal.NavTreeCell;
import br.com.saart.view.principal.Navitem;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class PrincipalController implements Initializable {

    public TreeView<Navitem> navbar;
    public Label footer;

    @Value("${spring.application.ui.version}")
    private String versao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        montaMenu();
        footer.setText(footer.getText().replace("0.0.0", versao));
    }

    //Monta Menu
    private void montaMenu() {
        navbar.setRoot(new TreeItem<>());
        navbar.setCellFactory(NavTreeCell::new);

        //Grupo Declarações
        TreeItem<Navitem> declaracoes = criaGrupoMenu("Declarações", "mdsmz-receipt_long");
        criaItemMenu(declaracoes, "DIRF", true);

        //Grupo Arquivos
        TreeItem<Navitem> arquivos = criaGrupoMenu("Arquivos", "mdoal-description");
        criaItemMenu(arquivos, "XML", false);

        //Grupos Relatórios
        TreeItem<Navitem> relatorios = criaGrupoMenu("Relatórios", "mdsmz-picture_as_pdf");
        criaItemMenu(relatorios, "Restituição IRRF", false);
    }

    private TreeItem<Navitem> criaGrupoMenu(String label, String iconCode) {
        TreeItem<Navitem> item = new TreeItem<>(new Navitem(label, new FontIcon(iconCode), true, false));
        navbar.getRoot().getChildren().add(item);
        return item;
    }

    private void criaItemMenu(TreeItem<Navitem> grupo, String label, boolean novo) {
        TreeItem<Navitem> item = new TreeItem<>(new Navitem(label, null, false, novo));
        grupo.getChildren().add(item);
    }

    //Botão de Configuração
}
