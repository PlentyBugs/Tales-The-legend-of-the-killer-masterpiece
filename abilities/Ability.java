package abilities;

import creature.LiveCreature;
import creature.Player;
import support.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ability implements Serializable, PropertyProvider, Sellable {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.add(AbilityProperty.ABILITY);
    }

    protected ArrayList<AbilityType> abilityType = new ArrayList<>();
    protected int power;
    protected int chance;
    protected int level;
    protected int maxLevel;
    protected int cost = 1;
    protected LiveCreature liveCreature;
    protected String name;
    protected CostType costType;

    public void addAbilityType(AbilityType abilityTypes) {
        abilityType.add(abilityTypes);
    }

    public ArrayList<AbilityType> getAbilityType() {
        return abilityType;
    }

    public void setLevel(int level) {
        if (level <= maxLevel){
            this.level = level;
        }
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getLevel() {
        return level;
    }

    public int getPower() {
        return power;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public boolean check(Player player){
        return false;
    }

    public void levelUp(Player player){
        if(check(player)){
            setLevel(getLevel()+1);
        }
    }

    public LiveCreature getLiveCreature() {
        return liveCreature;
    }

    public void setLiveCreature(LiveCreature liveCreature) {
        this.liveCreature = liveCreature;
    }

    public CostType getCostType() {
        return costType;
    }

    @Override
    public String getItemProperty() {
        return abilityType.stream().map(Enum::name).reduce("", (p, f) -> p + f + " ") + (costType == null ? "": costType);
    }
}
