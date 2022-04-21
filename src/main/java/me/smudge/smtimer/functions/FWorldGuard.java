package me.smudge.smtimer.functions;

import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import java.util.Set;

public class FWorldGuard {

    public static boolean isFlagInSet(Set<ProtectedRegion> regions, StateFlag flag) {
        for (ProtectedRegion protectedRegion : regions) {
            if (protectedRegion.getFlags().containsKey(flag) && protectedRegion.getFlags().containsValue(StateFlag.State.ALLOW)) return true;
        }
        return false;
    }

}
