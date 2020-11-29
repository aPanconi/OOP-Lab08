package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class ConfigReader {

    private static final String CONFIG_FILE = "config.yml";
    private static final int NUM_TOKEN = 2;

    enum Param {
        MIN(0),
        MAX(1),
        ATTEMPTS(2);

        private final int index;

        Param(final int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }
    }

    private ConfigReader() {
    }

    private static String readLine(final int index) throws IOException {
        final InputStream in = ClassLoader.getSystemResourceAsStream(CONFIG_FILE);
        final BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        for (int i = 0; i <= index; i++) {
            line = br.readLine();
        }
        in.close();
        return line;
    }

    public static int getParam(final Param p) throws IOException {
        final String line = readLine(p.getIndex());
        final StringTokenizer st = new StringTokenizer(line);
        String param = null;
        for (int i = 0; i < NUM_TOKEN; i++) {
            param = st.nextToken();
        }
        return Integer.parseInt(param);
    }
}
