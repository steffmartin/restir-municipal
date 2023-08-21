package br.com.saart.view.principal;

import br.com.saart.JavafxApplication;
import br.com.saart.JavafxApplication.StageReadyEvent;
import br.com.saart.view.StageFactory;
import javafx.application.Preloader.ProgressNotification;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class PrincipalStage implements ApplicationListener<StageReadyEvent> {

    @Autowired
    private JavafxApplication application;
    @Autowired
    private StageFactory stageFactory;

    @Value("/view/principal.fxml")
    private ClassPathResource primaryScene;
    @Value("${spring.application.ui.title}")
    private String title;

    @SneakyThrows
    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        Stage primaryStage = stageReadyEvent.getStage();
        stageFactory.updateStage(primaryStage, primaryScene, title);
        application.notifyPreloader(new ProgressNotification(100D));
        primaryStage.show();
    }
}
