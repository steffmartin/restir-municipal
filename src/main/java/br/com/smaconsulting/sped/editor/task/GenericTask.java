package br.com.smaconsulting.sped.editor.task;

import br.com.smaconsulting.sped.editor.controller.ProgressController;
import javafx.concurrent.Task;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class GenericTask extends Task<Map<String, Throwable>> {

    protected AutowireCapableBeanFactory aw;

    //Output
    protected final Map<String, Throwable> errors = new HashMap<>();

    public void startInNewThread(AutowireCapableBeanFactory aw, ProgressController pc) {
        this.setOnScheduled(e -> pc.onScheduled(this.progressProperty(), this.messageProperty()));
        this.setOnSucceeded(e -> pc.onSucceeded(this.getValue()));
        this.setOnFailed(e -> pc.onFailed(this.getException()));

        aw.autowireBean(this);
        this.aw = aw;

        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();
    }

}
