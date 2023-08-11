package br.com.saart;

import br.com.saart.view.Components;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JavafxApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = springContext -> {
            springContext.registerBean(Application.class, () -> JavafxApplication.this);
            springContext.registerBean(Parameters.class, this::getParameters);
            springContext.registerBean(HostServices.class, this::getHostServices);
        };

        context = new SpringApplicationBuilder().sources(SpringApplication.class)
                .initializers(initializer).build().run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> Components.exception(e));
        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> Components.exception(e));
        context.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }

    public static class StageReadyEvent extends ApplicationEvent {

        public StageReadyEvent(Stage source) {
            super(source);
        }

        public Stage getStage() {
            return (Stage) getSource();
        }
    }
}
