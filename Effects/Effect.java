package Effects;

import Creatures.LiveCreature;
import support.Property;
import support.GeneralProperty;
import support.PropertyProvider;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Effect implements Serializable, PropertyProvider {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.add(GeneralProperty.EFFECT);
    }

    protected EffectType type;
    protected int power;
    @Serial
    private static final long serialVersionUID = -1522910815967517982L;

    public EffectType getType() {
        return type;
    }

    public void use(LiveCreature liveCreature){}

    public Effect setPower(int power) {
        this.power = power;
        return this;
    }

    public int getPower() {
        return power;
    }

    public void setPowerAlchemy(int alchemyPower){
        power += alchemyPower;
    }

    public Effect getClearCopy(){
        return new Effect();
    }
}
