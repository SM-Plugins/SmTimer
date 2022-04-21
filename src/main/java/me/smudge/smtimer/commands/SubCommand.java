package me.smudge.smtimer.commands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract ArrayList<ArrayList<String>> getTabComplete();

    public abstract String getPermission();

    public abstract boolean preform(Player player, String[] args, Plugin plugin);

}
