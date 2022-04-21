package me.smudge.smtimer.configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CMessages {

    private static File file;
    private static FileConfiguration configFile;

    // Generates or finds file
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("SmTimer").getDataFolder(), "messages.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {}
        }
        configFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return configFile;
    }

    public static void save() {
        try {
            configFile.save(file);
        } catch (IOException e) {
            System.out.println("Could not save file");
        }
    }

    public static void reload() {
        configFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void setupDefaults() {
        CMessages.get().addDefault("messages.timer start", "{prefix} ䷕ Timer has started ䷕");
        CMessages.get().addDefault("messages.timer end", "{prefix} ䷕ You finished in &f&l{seconds}&e&ls");
    }

    public static String timerStart() {return CMessages.get().getString("messages.timer start");}
    public static String timerEnd() {return CMessages.get().getString("messages.timer end");}
}