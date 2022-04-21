package me.smudge.smtimer.functions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FPlugin {

    public static Plugin plugin;

    public static void setup(Plugin pluginInstance) {
        plugin = pluginInstance;
    }

    public static void disable(String reason) {
        FSend.log("PLUGIN DISABLED");
        FSend.log("Reason: " + reason);
        Bukkit.getPluginManager().disablePlugin(plugin);
    }
}
