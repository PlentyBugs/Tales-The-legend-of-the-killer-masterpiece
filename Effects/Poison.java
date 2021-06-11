package Effects;

import Creatures.LiveCreature;
import support.Property;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Poison extends Effect  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Effect.propertyList);
        propertyList.add(Property.POISON_EFFECT);
    }

    @Serial
    private static final long serialVersionUID = 3324013978376654430L;

    public Poison(){
        this(EffectType.MOMENT);
    }

    public Poison(EffectType type){
        this.type = type;
        power = 150;
    }

    public void use(LiveCreature liveCreature){
        if (liveCreature.getHp() - power > 1){
            liveCreature.setHp(liveCreature.getHp() - power);
        } else
            liveCreature.setHp(1);
    }

    @Override
    public void setPowerAlchemy(int alchemyPower){
        power += alchemyPower*20;
    }

    public Poison getClearCopy(){
        return new Poison();
    }
}
