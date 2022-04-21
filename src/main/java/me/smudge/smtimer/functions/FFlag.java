package me.smudge.smtimer.functions;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;

public class FFlag {

    StateFlag flag;
    String name;

    public FFlag(String name, String alternativeName) {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();

        try {
            StateFlag flag = new StateFlag(name, true);
            registry.register(flag);
            this.flag = flag;
            this.name = name;

        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get(alternativeName);
            if (!(existing instanceof StateFlag)) {disablePlugin(name, alternativeName); return;}
            this.flag = (StateFlag) existing;
            this.name = alternativeName;
        }
    }

    public StateFlag getFlag() {
        return this.flag;
    }

    private void disablePlugin(String name, String alternativeName) {
        FPlugin.disable("Flags already exist : " + name + " : " + alternativeName);
    }
}
