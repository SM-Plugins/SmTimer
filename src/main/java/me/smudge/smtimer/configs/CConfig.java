package me.smudge.smtimer.configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CConfig {

    private static File file;
    private static FileConfiguration configFile;

    // Generates or finds file
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("SmTimer").getDataFolder(), "config.yml");
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
        CConfig.get().addDefault("prefix", "&4&l[&6&lServer&4&l]&r&a");

        CConfig.get().addDefault("max amount of timers a player can have", 2);

        ArrayList<String> permissions = new ArrayList<String>();
        permissions.add("teamA");
        permissions.add("teamB");
        CConfig.get().addDefault("leaderboard info.permissions to look for", permissions);

        CConfig.get().addDefault("bossbar.title", "&6&lTimer &f&l{s}&e&ls");
        CConfig.get().addDefault("bossbar.colour", "RED");
        CConfig.get().addDefault("bossbar.style", "SEGMENTED_6");

        CConfig.get().addDefault("quickest time first", true);
    }

    public static int maxAmountOfTimers() {
        return CConfig.get().getInt("max amount of timers a player can have");
    }
    public static boolean quickestFirst () {return CConfig.get().getBoolean("quickest time first");}
}