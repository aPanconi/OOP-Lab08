package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private String currentLine;
    private final List<String> history = new ArrayList<>();

    @Override
    public final void setNextString(final String line) throws IllegalArgumentException {
        if (line == null) {
            throw new IllegalArgumentException();
        } else {
            this.currentLine = line;
        }
    }

    @Override
    public final String getNexString() {
       return this.currentLine;
    }

    @Override
    public final List<String> getHistory() {
        return history;
    }

    @Override
    public final void printCurrentString() throws IllegalStateException {
        if (this.currentLine == null) {
            throw new IllegalStateException();
        } else {
            System.out.println(this.currentLine);
            this.history.add(currentLine);
        }
    }
}
