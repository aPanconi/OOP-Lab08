package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class ConfigReader {

    private static final String CONFIG_FILE = "config.yml";
    private static final int NUM_LINES = 3;

    enum Param {
        MIN("minimum"),
        MAX("maximum"),
        ATTEMPTS("attempts");

        private final String token;

        Param(final String token) {
            this.token = token;
        }

        public String getToken() {
            return this.token;
        }
    }

    private ConfigReader() {
    }

    public static int getParam(final Param param) throws CorruptedConfigurationException {
        try {
            final InputStream in = ClassLoader.getSystemResourceAsStream(CONFIG_FILE);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            for (int i = 0; i < NUM_LINES; i++) {
                final StringTokenizer st = new StringTokenizer(br.readLine(), ":");
                if (st.nextToken().equals(param.getToken())) {
                    br.close();
                    return Integer.parseInt(st.nextToken().trim());
                }
            }
            br.close();
            throw new CorruptedConfigurationException("Configuration file is corrupted!");
        } catch (IOException ex) {
            throw new CorruptedConfigurationException("Configuration file not found!");
        }
    }
}
