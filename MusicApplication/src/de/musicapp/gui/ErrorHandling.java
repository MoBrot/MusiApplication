package de.musicapp.gui;

import javax.swing.*;

public class ErrorHandling extends Thread {

    private final JLabel errorLabel;
    private static ErrorHandling instance;

    public ErrorHandling(JLabel label) {
        this.errorLabel = label;
        instance = this;
    }

    public void showError(Error error) {
        this.errorLabel.setText(error.getErrorMessage());
        super.start();
    }

    @Override
    public void run() {

        this.errorLabel.setVisible(true);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.errorLabel.setVisible(false);

    }

    public enum Error {

        UNSUPPORTED_FILE("Your file is not supported!"),
        NO_FILE_SELECTED("Please select a file before you do this action!"),
        MUSIC_NOT_AVAILABLE("The music isn't available anymore");

        private final String message;

        Error(String message) {
            this.message = message;
        }

        public String getErrorMessage() {
            return this.message;
        }
    }

    public static ErrorHandling getInstance() {
        return instance;
    }
}
