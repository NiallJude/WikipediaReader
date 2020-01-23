package com.nialljude.dev.files;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    public Properties getProperties() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {

            // load a properties file
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
