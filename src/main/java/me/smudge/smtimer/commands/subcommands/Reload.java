package me.smudge.smtimer.commands.subcommands;

import me.smudge.smtimer.commands.SubCommand;
import me.smudge.smtimer.configs.CConfig;
import me.smudge.smtimer.configs.CData;
import me.smudge.smtimer.configs.CLeaderboard;
import me.smudge.smtimer.configs.CMessages;
import me.smudge.smtimer.functions.FSend;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Reload extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads configs";
    }

    @Override
    public ArrayList<ArrayList<String>> getTabComplete() {
        return null;
    }

    @Override
    public String getPermission() {
        return "admin";
    }

    @Override
    public boolean preform(Player player, String[] args, Plugin plugin) {
        CConfig.reload();
        CData.reload();
        CLeaderboard.reload();
        CMessages.reload();

        FSend.player(player, "{prefix} Configs reloaded");
        return true;
    }
}
