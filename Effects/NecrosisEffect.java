package Effects;

import Creatures.LiveCreature;
import support.Property;
import support.GeneralProperty;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class NecrosisEffect extends Effect {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Effect.propertyList);
        propertyList.add(GeneralProperty.NECROSIS_EFFECT);
    }

    @Serial
    private static final long serialVersionUID = 3324013978376654430L;

    public NecrosisEffect(){
        this(EffectType.MOMENT);
    }

    public NecrosisEffect(EffectType type){
        this.type = type;
        power = 3;
    }

    public void use(LiveCreature liveCreature){
        if (liveCreature.getHp() - power > 1){
            liveCreature.setHp(liveCreature.getHp() - power);
        } else
            liveCreature.setHp(1);
    }

    @Override
    public void setPowerAlchemy(int alchemyPower){
        power += Math.pow(Math.E, Math.log(alchemyPower)/Math.log(10));
    }

    public Poison getClearCopy(){
        return new Poison();
    }
}
