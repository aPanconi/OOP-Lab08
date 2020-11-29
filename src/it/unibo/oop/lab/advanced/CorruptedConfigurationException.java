package it.unibo.oop.lab.advanced;

public class CorruptedConfigurationException extends Exception  {

    private static final long serialVersionUID = 1L;

    public CorruptedConfigurationException(final String message) {
        super(message);
    }
}
