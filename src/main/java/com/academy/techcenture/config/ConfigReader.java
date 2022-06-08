package com.academy.techcenture.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    //constant - final and static
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    private ConfigReader() {
    }

    static {
        System.out.println("TEAM 1 is so smart!");
        try {
            FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        } catch (Exception e) {

        }
    }

    public static String getProperty(String keyName) {
        return properties.getProperty(keyName);
    }

    public static void setProperty(String key, String value) throws IOException {
        properties.store(new FileOutputStream(CONFIG_FILE_PATH), null);
        properties.setProperty(key, value);
    }
}
