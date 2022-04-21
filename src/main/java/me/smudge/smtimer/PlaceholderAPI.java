package me.smudge.smtimer;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.smudge.smtimer.configs.CData;
import me.smudge.smtimer.configs.CLeaderboard;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlaceholderAPI extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "smtimer";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Smudge";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) return "null";

        String[] args = params.split("_");

        if (Objects.equals(args[0], "time")) return timeInSeconds(args[1]);
        if (Objects.equals(args[0], "name")) return CLeaderboard.getLeaderboardName(Integer.parseInt(args[1]));
        if (Objects.equals(args[0], "permission")) return CLeaderboard.getLeaderboardPermission(Integer.parseInt(args[1]));
        if (Objects.equals(args[0], "count")) return CData.getPlayer(player);

        return "null";
    }

    private String timeInSeconds(String arg) {
        long time = CLeaderboard.getLeaderboardTime(Integer.parseInt(arg));
        double seconds = (double) time / 1000;
        return String.valueOf(seconds);
    }
}