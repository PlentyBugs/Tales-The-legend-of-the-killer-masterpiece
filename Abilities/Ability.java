package Abilities;

public class Ability {
    private AbilityType abilityType;
    protected int power;
    protected int chance;
    protected int level;
    protected int maxLevel;

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
}
