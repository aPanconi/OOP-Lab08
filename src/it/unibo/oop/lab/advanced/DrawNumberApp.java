package it.unibo.oop.lab.advanced;

import java.io.IOException;
import it.unibo.oop.lab.advanced.ConfigReader.Param;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;
    private DrawNumber model;
    private final DrawNumberView view;

    /**
     * @throws IOException 
     * 
     */
    public DrawNumberApp() {
        try {
            this.model = new DrawNumberImpl(ConfigReader.getParam(
                    Param.MIN), ConfigReader.getParam(Param.MAX), ConfigReader.getParam(Param.ATTEMPTS));
            System.out.println("MIN: " + ConfigReader.getParam(Param.MIN) + " MAX: " + ConfigReader.getParam(Param.MAX) + " ATTEMPTS: " + ConfigReader.getParam(Param.ATTEMPTS));
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage() + "\nHarcoded params will be loaded...");
            this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        }
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
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
        new DrawNumberApp();
    }

}
