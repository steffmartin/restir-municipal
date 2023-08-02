package br.com.smaconsulting.sped.editor;

import br.com.smaconsulting.sped.editor.view.ApplicationPreLoader;
import com.sun.javafx.application.LauncherImpl;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.prefs.Preferences;

@SpringBootApplication
public class SpringApplication {

    public static void main(String[] args) {
//    System.setProperty("prism.allowhidpi", "false");
        LauncherImpl.launchApplication(JavafxApplication.class, ApplicationPreLoader.class, args);
    }

    @Bean
    public Preferences getPreferences() {
        return Preferences.userNodeForPackage(this.getClass());
    }

    @Bean
    @SneakyThrows
    public DocumentBuilder getDocumentBuilder() {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
}
