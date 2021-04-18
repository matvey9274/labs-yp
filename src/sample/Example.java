package sample;

import javafx.concurrent.Task;

public class Example extends Task<Void> {

    @Override
    public Void call() {
        metod_1();
        return null;
    }

    final int MAX_WORK = 10;

    private void metod_1() {
        updateMessage("Work in progress");
        for (int i = 0; i < MAX_WORK; i++) {
            if (isCancelled()) {
                updateMessage("Work has stopped");
                return;
            }

            System.out.print("Proccesing....");
            System.out.println(i + 1);
            updateProgress(i + 1, MAX_WORK);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException interrupted) {
                if (isCancelled()) {
                    updateMessage("Stopped");
                    return;
                }
            }
        }
        updateMessage("Work is completed");
    }

    protected void updateMessage(String message) {
        System.out.println(message);
        super.updateMessage(message);
    }
}