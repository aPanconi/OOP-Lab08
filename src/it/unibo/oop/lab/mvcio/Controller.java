package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE_NAME = "output.txt";

    private File current = new File(USER_HOME + FILE_SEPARATOR + DEFAULT_FILE_NAME);

    public final void setCurrent(final File file) {
        final File path = file.getParentFile();

        if (path.exists()) {
            current = file;
        } else {
            throw new IllegalArgumentException("The specified path does not exist!"); 
        }
    }

    public final File getCurrent() {
        return this.current;
    }

    public final String getCurrentPath() {
        return current.getPath();
    }

    public final void save(final String text) throws IOException {
        try (PrintStream out = new PrintStream(current)) {
            out.println(text);
        }
    }
}
