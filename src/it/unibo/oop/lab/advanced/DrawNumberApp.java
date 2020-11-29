package it.unibo.oop.lab.advanced;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import it.unibo.oop.lab.advanced.ConfigReader.Param;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;
    private DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @throws IOException
     * @param views 
     */
    public DrawNumberApp(final DrawNumberView... views) {
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        try {
            this.model = new DrawNumberImpl(ConfigReader.getParam(
                    Param.MIN), ConfigReader.getParam(Param.MAX), ConfigReader.getParam(Param.ATTEMPTS));
        } catch (Exception ex) {
            displayError(ex.getMessage() + "\nHarcoded params will be loaded...");
            this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        }
    }

    public void displayError(final String msg) {
        for (final DrawNumberView view: views) {
            view.displayError(msg);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView view: views) {
                view.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws IOException 
     */
    public static void main(final String... args) throws IOException {
        new DrawNumberApp(new DrawNumberViewImpl(), new PrintLogView(System.out), new PrintLogView("output.log"));
    }

}
