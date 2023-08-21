package br.com.saart.view;

import br.com.saart.util.Components;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Getter
public class StageFactory {

    @Value("${spring.application.ui.version}")
    private String version;
    @Value("${spring.application.ui.title}")
    private String title;
    @Value("/view/style.css")
    private ClassPathResource css;

    @Autowired
    private ApplicationContext context;

    public Stage createStage(ClassPathResource view, String title) {
        Stage stage = new Stage();
        updateStage(stage, view, title);
        return stage;
    }

    @SneakyThrows
    public void updateStage(Stage stage, ClassPathResource view, String title) {
        FXMLLoader loader = new FXMLLoader(view.getURL());
        loader.setControllerFactory(context::getBean);

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(css.getURL().toExternalForm());

        stage.getIcons().add(new Image(Components.icon.getInputStream()));
        stage.setTitle(title);
        stage.setScene(scene);
    }


}
