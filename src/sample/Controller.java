package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class Controller {

    public ProgressBar progressBar1;
    public Button buttonStart;
    public Button buttonStop;
    public Label labelMessage;
    public Label progress_percent;

    private Example example;

    public void initialize() {
    }
    @FXML
    public void startTask(ActionEvent event) {
        if (example != null && example.isRunning()) {
            example.cancel();
        }

        example = new Example();
        Thread thread = new Thread(example);
        thread.setDaemon(true);
        thread.start();

        progressBar1.progressProperty().bind(example.progressProperty());
        labelMessage.textProperty().bind(example.messageProperty());

        buttonStart.disableProperty().bind(example.runningProperty());
        buttonStop.disableProperty().bind(example.runningProperty().not());
    }
    @FXML
    public void cancelTask(ActionEvent event) {
        if (example != null) example.cancel();
    }
}