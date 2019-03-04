package Effects;

import Creatures.LiveCreature;
import Creatures.StatsEnum;

public class StatsUpEffect extends Effect {
    private StatsEnum stat;

    public StatsUpEffect(){
        this(EffectType.MOMENT, StatsEnum.STRENGTH);
    }

    public StatsUpEffect(EffectType type, StatsEnum stat){
        this.type = type;
        power = 1;
        this.stat = stat;
    }

    public void use(LiveCreature liveCreature){
        switch (stat){
            case STRENGTH: liveCreature.getStats().strength += power; break;
            case SPEED: liveCreature.getStats().speed += power; break;
            case AGILITY: liveCreature.getStats().agility += power; break;
            case INTELLIGENCE: liveCreature.getStats().intelligence += power; break;
            case LUCK: liveCreature.getStats().luck += power; break;
            case ELOQUENCE: liveCreature.getStats().eloquence += power; break;
            case BLACKSMITH: liveCreature.getStats().blacksmith += power; break;
            case THEFT: liveCreature.getStats().theft += power; break;
            case ALCHEMY: liveCreature.getStats().alchemy += power; break;
            case ONEHANDEDWEAPON: liveCreature.getStats().one_handed_weapon += power; break;
            case TWOHANDEDWEAPON: liveCreature.getStats().two_handed_weapon += power; break;
            case POLEWEAPON: liveCreature.getStats().pole_weapon += power; break;
            case CHOPPINGWEAPON: liveCreature.getStats().chopping_weapon += power; break;
            case LONGRANGEWEAPON: liveCreature.getStats().long_range_weapon += power; break;
            case KNOWLEDGE: liveCreature.getStats().knowledge += power; break;
            case ENERGY: liveCreature.getStats().energy += power; break;
        }
    }

    @Override
    public void setPowerAlchemy(int alchemyPower){
        power += alchemyPower/10;
    }

    public StatsUpEffect getClearCopy(){
        return new StatsUpEffect();
    }
}
