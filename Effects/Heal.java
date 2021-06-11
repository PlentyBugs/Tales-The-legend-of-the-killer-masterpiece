package Effects;

import Creatures.LiveCreature;
import support.Property;
import support.GeneralProperty;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Heal extends Effect {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Effect.propertyList);
        propertyList.add(GeneralProperty.HEAL_EFFECT);
    }

    @Serial
    private static final long serialVersionUID = -2237990859028001672L;

    public Heal(){
        this(EffectType.MOMENT);
    }

    public Heal(EffectType type){
        this.type = type;
        power = 70;
    }

    public void use(LiveCreature liveCreature){
        if (liveCreature.getHp() + power <= liveCreature.getMaxHp()){
            liveCreature.setHp(liveCreature.getHp() + power);
        } else {
            liveCreature.setHp(liveCreature.getMaxHp());
        }
    }

    @Override
    public void setPowerAlchemy(int alchemyPower){
        power += alchemyPower*30;
    }

    public Heal getClearCopy(){
        return new Heal();
    }
}
