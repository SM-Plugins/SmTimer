package me.smudge.smtimer.functions;

import me.smudge.smtimer.configs.CData;
import me.smudge.smtimer.configs.CLeaderboard;
import me.smudge.smtimer.configs.CMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FTimer {

    public static HashMap<String, Long> startTimes = new HashMap<String, Long>();

    public static void start(Player player) {
        // Do they already have a timer?
        if (startTimes.containsKey(player.getName())) return;

        // Have they exceeded the allowed amount?
        if (CData.hasPlayerExeededMaxTimers(player)) return;

        // Timer
        Date date = new Date();
        AtomicInteger seconds = new AtomicInteger();
        long startTime = date.getTime();

        // Initiate
        startTimes.put(player.getName(), startTime);

        // BossBar
        FBossBar bossBar = new FBossBar(player);

        // Message
        FSend.player(player, CMessages.timerStart());

        // Increment player timer amount
        CData.increment(player);

        // Scheduler
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(FPlugin.plugin, task -> {

            if (!startTimes.containsKey(player.getName())) {
                bossBar.terminate();
                task.cancel();
            }

            seconds.addAndGet(1);
            bossBar.tick(seconds.get());

        }, 20, 20);
    }

    public static void stop(Player player) {
        // Check it hasn't already stopped
        if (!startTimes.containsKey(player.getName())) return;
        if (startTimes.get(player.getName()) == null) return;

        // Calculate to get precise millisecond
        Date date = new Date();
        long currentTime = date.getTime();
        long startTime = startTimes.remove(player.getName());
        long time = currentTime - startTime;
        double seconds = (double) time / 1000;

        // Send message
        FSend.player(player, CMessages.timerEnd().replace("{seconds}", String.valueOf(seconds)));

        // Leaderboard
        CLeaderboard.addToLeaderboard(player, String.valueOf(time));
    }
}
