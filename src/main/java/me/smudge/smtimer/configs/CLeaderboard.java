package me.smudge.smtimer.configs;

import me.smudge.smtimer.functions.FUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CLeaderboard {

    private static File file;
    private static FileConfiguration configFile;

    // Generates or finds file
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("SmTimer").getDataFolder(), "leaderboard.yml");
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

    public static void addToLeaderboard(Player player, String time) {
        ArrayList<String> permissions = (ArrayList<String>) CConfig.get().getStringList("leaderboard info.permissions to look for");
        String permissionTheyHave = FUtil.getPermissionTheyHave(player, permissions);

        // Make sure there are no duplicate keys
        if (CLeaderboard.get().getKeys(false).contains(time)) {addToLeaderboard(player, time + "0"); return;}

        CLeaderboard.get().set(time + ".name", player.getName());
        CLeaderboard.get().set(time + ".permission", permissionTheyHave);
        CLeaderboard.save();
    }

    public static List<Long> getValues() {
        Set<String> times = CLeaderboard.get().getKeys(false);
        List<Long> timesToReturn = new ArrayList<Long>();
        for (String time : times) {
            timesToReturn.add(Long.valueOf(time));
        }
        return timesToReturn;
    }

    public static Long getLeaderboardTime(int rank) {
        List<Long> times = getValues();
        Collections.sort(times);
        if (!(CConfig.quickestFirst())) {Collections.reverse(times);}

        if (!(rank >= times.size())) {
            return times.get(rank - 1);
        }
        return null;
    }

    public static String getLeaderboardName(int rank) {
        Long time = getLeaderboardTime(rank);
        return CLeaderboard.get().getString(time + ".name");
    }

    public static String getLeaderboardPermission(int rank) {
        Long time = getLeaderboardTime(rank);
        return CLeaderboard.get().getString(time + ".permission");
    }
}