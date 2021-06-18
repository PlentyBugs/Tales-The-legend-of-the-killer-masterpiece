package abilities.buffs;

import creature.LiveCreature;

public interface StackableBuff {
    boolean getStack(LiveCreature liveCreature);
}
