package me.smudge.smtimer.functions;

import me.smudge.smtimer.configs.CConfig;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class FSend {

    public static String convert(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void player(Player player, String message) {
        String prefix = CConfig.get().getString("prefix");
        player.sendMessage(convert(message.replace("{prefix}", Objects.requireNonNull(prefix))));
    }
    public static void playerTextComponent(Player player, TextComponent message) {
        player.spigot().sendMessage(message);
    }
    public static void playerError(Player player, String message) {
        player.sendMessage(convert("&7&l> &7" + message));
    }
    public static void all(String message) {
        String prefix = CConfig.get().getString("prefix");
        Bukkit.broadcastMessage(convert(message.replace("{prefix}", Objects.requireNonNull(prefix))));
    }
    public static void log(String message) {
        Bukkit.getLogger().info(message);
    }
}
