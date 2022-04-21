package me.smudge.smtimer.configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CData {

    private static File file;
    private static FileConfiguration configFile;

    // Generates or finds file
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("SmTimer").getDataFolder(), "data.yml");
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

    public static void increment(Player player) {
        if (CData.get().getKeys(false).contains(player.getName())) {
            CData.get().set(player.getName(), CData.get().getInt(player.getName()) + 1);
        } else {
            CData.get().set(player.getName(), 1);
        }
        CData.save();
    }

    public static boolean hasPlayerExeededMaxTimers(Player player) {
        int allowed = CConfig.maxAmountOfTimers();
        int current = 0;

        if(allowed == -1) return false;

        if (CData.get().getKeys(false).contains(player.getName())) {
            current = CData.get().getInt(player.getName());
        }

        return current > allowed;
    }

    public static String getPlayer(Player player) {
        if (!CData.get().getKeys(false).contains(player.getName())) return null;
        return String.valueOf(CData.get().getInt(player.getName()));
    }
}