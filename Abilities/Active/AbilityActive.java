package Abilities.Active;

import Abilities.Ability;
import Abilities.AbilityTarget;
import Abilities.Buffs.Buff;
import Creatures.LiveCreature;
import Creatures.Player;

public class AbilityActive extends Ability {

    protected AbilityTarget abilityTarget;
    protected Buff buff;

    public void use(Player player){}

    public void use(LiveCreature liveCreature){}

    public AbilityTarget getAbilityTarget() {
        return abilityTarget;
    }

    public void setAbilityTarget(AbilityTarget abilityTarget) {
        this.abilityTarget = abilityTarget;
    }

    public Buff getBuff() {
        try {
            return (Buff) buff.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }
}
