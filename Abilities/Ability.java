package Abilities;

import LiveCreatures.Player;

public class Ability {
    private AbilityType abilityType;
    protected int power;
    protected int chance;
    protected int level;
    protected int maxLevel;
    protected int cost = 1;
    protected String name;

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    public AbilityType getAbilityType() {
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
}
