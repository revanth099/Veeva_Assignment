package com.company.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties props = new Properties();

    public static void loadConfig(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + filePath, e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
    public static int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }
}

