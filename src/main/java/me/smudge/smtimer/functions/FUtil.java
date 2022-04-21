package me.smudge.smtimer.functions;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FUtil {
    public static String getPermissionTheyHave(Player player, ArrayList<String> permissions) {
        for (String permission : permissions) {
            if (player.hasPermission(permission)) return permission;
        }
        return null;
    }
}
