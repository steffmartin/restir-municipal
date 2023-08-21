package br.com.saart;

import br.com.saart.view.preloader.PreLoaderStage;
import com.sun.javafx.application.LauncherImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.prefs.Preferences;

@EnableFeignClients
@SpringBootApplication
public class SpringApplication {

    public static void main(String[] args) {
//        System.setProperty("prism.allowhidpi", "false");
        LauncherImpl.launchApplication(JavafxApplication.class, PreLoaderStage.class, args);
    }

    @Bean
    public Preferences getPreferences() {
        return Preferences.userNodeForPackage(this.getClass());
    }

}
