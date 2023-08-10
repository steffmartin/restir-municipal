package br.com.smaconsulting.saart.view;

import br.com.smaconsulting.saart.JavafxApplication;
import br.com.smaconsulting.saart.JavafxApplication.StageReadyEvent;
import br.com.smaconsulting.saart.util.UserPreferences;
import javafx.application.Preloader.ProgressNotification;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {

    @Autowired
    private JavafxApplication application;
    @Autowired
    private StageFactory stageFactory;
    @Autowired
    private UserPreferences userPreferences;
    @Value("/view/main.fxml")
    private ClassPathResource primaryScene;
    @Value("${spring.application.ui.title}")
    private String title;

    @SneakyThrows
    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        Stage primaryStage = stageReadyEvent.getStage();
        stageFactory.updateStage(primaryStage, primaryScene, title);
        primaryStage.setResizable(false);

        application.notifyPreloader(new ProgressNotification(100D));

        primaryStage.show();
    }
}
