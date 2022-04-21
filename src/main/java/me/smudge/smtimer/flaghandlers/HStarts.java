package me.smudge.smtimer.flaghandlers;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.FlagValueChangeHandler;
import com.sk89q.worldguard.session.handler.Handler;
import me.smudge.smtimer.SmTimer;
import me.smudge.smtimer.functions.FTimer;
import me.smudge.smtimer.functions.FWorldGuard;
import org.bukkit.entity.Player;

import java.util.Set;

public class HStarts extends FlagValueChangeHandler<StateFlag.State> {
    public static final Factory FACTORY = new Factory();

    public static class Factory extends Handler.Factory<HStarts> {
        @Override
        public HStarts create(Session session) {return new HStarts(session);}
    }

    public HStarts(Session session) {
        super(session, SmTimer.start.getFlag());
    }

    @Override
    public boolean onCrossBoundary(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, Set<ProtectedRegion> entered, Set<ProtectedRegion> exited, MoveType moveType) {
        Player bukkitPlayer = BukkitAdapter.adapt(player);

        // Walking into a region
        if (FWorldGuard.isFlagInSet(entered, SmTimer.start.getFlag())) {
            FTimer.start(bukkitPlayer);
        }

        return super.onCrossBoundary(player, from, to, toSet, entered, exited, moveType);
    }

    @Override
    protected void onInitialValue(LocalPlayer player, ApplicableRegionSet set, StateFlag.State value) {}

    @Override
    protected boolean onSetValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, StateFlag.State currentValue, StateFlag.State lastValue, MoveType moveType) {return false;}

    @Override
    protected boolean onAbsentValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, StateFlag.State lastValue, MoveType moveType) {return false;}
}
