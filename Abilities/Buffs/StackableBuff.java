package Abilities.Buffs;

import Creatures.LiveCreature;

public interface StackableBuff {
    boolean getStack(LiveCreature liveCreature);
}
