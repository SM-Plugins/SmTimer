package me.smudge.smtimer;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.session.SessionManager;
import me.smudge.smtimer.commands.MainCommand;
import me.smudge.smtimer.configs.CConfig;
import me.smudge.smtimer.configs.CData;
import me.smudge.smtimer.configs.CLeaderboard;
import me.smudge.smtimer.configs.CMessages;
import me.smudge.smtimer.flaghandlers.HEnd;
import me.smudge.smtimer.flaghandlers.HStarts;
import me.smudge.smtimer.functions.FFlag;
import me.smudge.smtimer.functions.FPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmTimer extends JavaPlugin {

    public static FFlag start;
    public static FFlag end;

    @Override
    public void onEnable() {

        // Setup Config
        CConfig.setup();
        CConfig.setupDefaults();
        CConfig.get().options().copyDefaults(true);
        CConfig.save();

        CMessages.setup();
        CMessages.setupDefaults();
        CMessages.get().options().copyDefaults(true);
        CMessages.save();

        CData.setup();
        CData.get().options().copyDefaults(true);
        CData.save();

        CLeaderboard.setup();
        CLeaderboard.get().options().copyDefaults(true);
        CLeaderboard.save();

        // Setup Commands
        getCommand("timer").setExecutor(new MainCommand(this, "timer"));

        // Setup Plugin
        FPlugin.setup(this);

        // Setup Handlers
        SessionManager sessionManager = WorldGuard.getInstance().getPlatform().getSessionManager();

        sessionManager.registerHandler(HStarts.FACTORY, null);
        sessionManager.registerHandler(HEnd.FACTORY, null);

        // Setup PlaceholderAPI
        new PlaceholderAPI().register();
    }

    @Override
    public void onLoad() {

        // Setup Flags
        start = new FFlag("smtimer-start", "start");
        end = new FFlag("smtimer-end", "end");
    }
}
