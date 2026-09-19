package com.codesn.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {

    private final Properties props = new Properties();

    public ConfigLoader(String serviceName) {

        String fileName = serviceName + ".config.properties";

        try (InputStream configStream = getClass().getClassLoader().getResourceAsStream(fileName)) {

            if (configStream == null) {
                throw new RuntimeException("Config not found on classpath: " + fileName);
            }

            props.load(configStream);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + fileName, e);
        }
    }

    public String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing key in " + props + ": " + key);
        }
        return value;
    }
}
