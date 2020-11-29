package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintLogView implements DrawNumberView {

    private final PrintStream log;

    public PrintLogView(final PrintStream stream) {
        this.log = stream;
    }

    public PrintLogView(final String path) throws FileNotFoundException {
        this.log = new PrintStream(new FileOutputStream(new File(path)));
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }

    @Override
    public void start() {
    }

    @Override
    public final void numberIncorrect() {
        log.println("Enter a correct number!");
    }

    @Override
    public final void result(final DrawResult res) {
        log.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
    }

    @Override
    public final void displayError(final String message) {
        log.println("Error: " +  message);
    }
}
