package Abilities.Active;

import Abilities.Ability;
import Abilities.AbilityTarget;
import Abilities.Buffs.Buff;
import LiveCreatures.LiveCreature;
import LiveCreatures.Player;

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
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }
}
