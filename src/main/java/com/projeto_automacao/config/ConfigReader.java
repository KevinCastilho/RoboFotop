package com.projeto_automacao.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final String CONFIG_FILE_PATH = "config.properties";
    private static Properties properties;

    public static void loadConfig() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        loadConfig();
        return properties.getProperty(key);
    }
}
