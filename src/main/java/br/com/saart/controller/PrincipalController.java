package br.com.saart.controller;

import br.com.saart.view.StageFactory;
import br.com.saart.view.principal.navbar.NavTreeCell;
import br.com.saart.view.principal.navbar.Navitem;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.extern.slf4j.Slf4j;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Controller
public class PrincipalController implements Initializable {

    @Autowired
    private ConfiguracoesController configuracoes;

    @Autowired
    private StageFactory stageFactory;

    @Value("${spring.application.ui.version}")
    private String versao;

    public TreeView<Navitem> navbar;
    public Label footer;
    public ScrollPane page;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        montaMenu();
        footer.setText(footer.getText().replace("0.0.0", versao));
    }

    public void abrirConfig() {
        configuracoes.montar();
        configuracoes.abrir();
    }

    //Monta Menu
    private void montaMenu() {
        navbar.setRoot(new TreeItem<>());
        navbar.setCellFactory(tv -> new NavTreeCell());

        //Grupo Declarações
        TreeItem<Navitem> declaracoes = criaGrupoMenu("Declarações", "mdsmz-receipt_long");
        criaItemMenu(declaracoes, "DIRF", "dirf", false);

        //Grupos Relatórios
        TreeItem<Navitem> relatorios = criaGrupoMenu("Relatórios", "mdsmz-picture_as_pdf");
        criaItemMenu(relatorios, "Restituição IRRF", "restituicao_irrf", false);

        //Grupo Tabelas
        TreeItem<Navitem> tabelas = criaGrupoMenu("Tabelas", "mdomz-table_rows");
        criaItemMenu(tabelas, "Códigos de Receita", "cod_receita", true);
        criaItemMenu(tabelas, "Taxa de Juros Selic", "selic", false);
    }

    private TreeItem<Navitem> criaGrupoMenu(String label, String iconCode) {
        TreeItem<Navitem> item = new TreeItem<>(new Navitem(label, new FontIcon(iconCode), true, false, null));
        item.setExpanded(true);
        navbar.getRoot().getChildren().add(item);
        return item;
    }

    private void criaItemMenu(TreeItem<Navitem> grupo, String label, String fxml, boolean novo) {
        TreeItem<Navitem> item = new TreeItem<>(new Navitem(label, null, false, novo,
                () -> stageFactory.updateScrollPane(new ClassPathResource("/view/" + fxml + ".fxml"), page)));
        grupo.getChildren().add(item);
    }

}
