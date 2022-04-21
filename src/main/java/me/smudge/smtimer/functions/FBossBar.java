package me.smudge.smtimer.functions;

import me.smudge.smtimer.configs.CConfig;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class FBossBar {

    BossBar bossbar;
    String configTitle;
    Player player;

    public FBossBar(Player player) {

        this.player = player;
        this.configTitle = CConfig.get().getString("bossbar.title");

        BarColor colour = BarColor.valueOf(CConfig.get().getString("bossbar.colour"));
        BarStyle style = BarStyle.valueOf(CConfig.get().getString("bossbar.style"));

        this.bossbar = Bukkit.getServer().createBossBar(FSend.convert(this.configTitle.replace("{s}", "0"))
                , colour, style);
        this.bossbar.setVisible(true);
        this.bossbar.addPlayer(player);
    }

    public void tick(int time) {
        this.bossbar.setTitle(FSend.convert(this.configTitle.replace("{s}", String.valueOf(time))));
    }

    public void terminate() {
        this.bossbar.removePlayer(this.player);
    }
}
