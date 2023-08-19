package br.com.saart.task;

import br.com.saart.controller.ProgressController;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class GenericTask extends Task<Map<String, Throwable>> {

    protected AutowireCapableBeanFactory aw;
    protected ProgressController pc;

    //Output
    protected final Map<String, Throwable> errors = new HashMap<>();

    public void startInNewThread(AutowireCapableBeanFactory aw, ProgressController pc) {
        this.pc = pc;
        this.setOnScheduled(e -> this.pc.onScheduled(this.progressProperty(), this.messageProperty()));
        this.setOnSucceeded(e -> this.pc.onSucceeded(this.getValue()));
        this.setOnFailed(e -> this.pc.onFailed(this.getException()));

        aw.autowireBean(this);
        this.aw = aw;

        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();
    }

    @Override
    protected void updateMessage(String s) {
        super.updateMessage(s);
        log.info(s);
    }
}
