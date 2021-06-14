package Abilities.Buffs;

import Creatures.LiveCreature;
import Creatures.Player;
import Items.Weapons.Weapon;
import support.AbilityProperty;
import support.Property;
import support.PropertyProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Buff implements Cloneable, Serializable, PropertyProvider {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.add(AbilityProperty.BUFF);
    }

    protected int stepCount;
    protected String name;
    protected int power;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void tick(){
        stepCount --;
    }

    public void use(Player player){}

    public void use(LiveCreature liveCreature){}

    public void upgrade(LiveCreature liveCreature){
        double bonusPower = 0;
        for(Weapon staff : liveCreature.getEquipment().getStaffs()){
            bonusPower += staff.getDamage();
        }
        bonusPower += liveCreature.getStats().getIntelligence() / 60.0;
        power += (int)(power*bonusPower/18);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
