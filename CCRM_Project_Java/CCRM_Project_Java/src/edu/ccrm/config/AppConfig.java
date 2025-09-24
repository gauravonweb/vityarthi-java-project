package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppConfig {
    private static AppConfig instance;
    private Path dataFolder;

    private AppConfig() {
        this.dataFolder = Paths.get(System.getProperty("user.dir"), "data");
    }

    public static AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }

    public Path getDataFolder() {
        return dataFolder;
    }
}
