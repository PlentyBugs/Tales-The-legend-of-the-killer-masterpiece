package effect;

import creature.LiveCreature;
import creature.StatsEnum;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class StatsUpEffect extends Effect  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Effect.propertyList);
        propertyList.add(GeneralProperty.STATUS_UP_EFFECT);
    }

    private final StatsEnum stat;

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
        power += Math.pow(Math.E, Math.log(alchemyPower)/Math.log(8));
    }

    public StatsUpEffect getClearCopy(){
        return new StatsUpEffect(type, stat);
    }
}
