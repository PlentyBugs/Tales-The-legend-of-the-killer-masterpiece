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
        liveCreature.getStats().upStat(stat, power);
    }

    @Override
    public void setPowerAlchemy(int alchemyPower){
        power += alchemyPower/10;
    }

    public StatsUpEffect getClearCopy(){
        return new StatsUpEffect(type, stat);
    }
}
