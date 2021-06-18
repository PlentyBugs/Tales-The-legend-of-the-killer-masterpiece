package creature;

import java.io.Serial;

public class Stats extends StatsBonus {

    @Serial
    private static final long serialVersionUID = 5122589181835788912L;

    private final StatsBonus bonusStats;

    public Stats(){
        bonusStats = new StatsBonus();
        clear();
    }

    public int getAgility() {
        return agility + bonusStats.getAgility();
    }

    public int getAlchemy() {
        return alchemy + bonusStats.getAlchemy();
    }

    public int getBlacksmith() {
        return blacksmith + bonusStats.getBlacksmith();
    }

    public int getChoppingWeapon() {
        return choppingWeapon + bonusStats.getChoppingWeapon();
    }

    public int getEloquence() {
        return eloquence + bonusStats.getEloquence();
    }

    public int getEnergy() {
        return energy + bonusStats.getEnergy();
    }

    public int getIntelligence() {
        return intelligence + bonusStats.getIntelligence();
    }

    public int getKnowledge() {
        return knowledge + bonusStats.getKnowledge();
    }

    public int getLongRangeWeapon() {
        return longRangeWeapon + bonusStats.getLongRangeWeapon();
    }

    public int getLuck() {
        return luck + bonusStats.getLuck();
    }

    public int getMilitarism() {
        return militarism + bonusStats.getMilitarism();
    }

    public int getOneHandedWeapon() {
        return oneHandedWeapon + bonusStats.getOneHandedWeapon();
    }

    public int getPacifism() {
        return pacifism + bonusStats.getPacifism();
    }

    public int getPoleWeapon() {
        return poleWeapon + bonusStats.getPoleWeapon();
    }

    public int getSpeed() {
        return speed + bonusStats.getSpeed();
    }

    public int getStrength() {
        return strength + bonusStats.getStrength();
    }

    public int getTheft() {
        return theft + bonusStats.getTheft();
    }

    public int getTwoHandedWeapon() {
        return twoHandedWeapon + bonusStats.getTwoHandedWeapon();
    }

    public StatsBonus getBonusStats() {
        return bonusStats;
    }

    public void setStat(StatsEnum statType, int value) {
        switch (statType) {
            case MAX_HP -> {setMaxHp(value); setHp(value);}
            case HP -> setHp(value);
            case LEVEL -> setLevel(value);
            case STRENGTH -> setStrength(value);
            case SPEED -> setSpeed(value);
            case AGILITY -> setAgility(value);
            case INTELLIGENCE -> setIntelligence(value);
            case LUCK -> setLuck(value);
            case ELOQUENCE -> setEloquence(value);
            case BLACKSMITH -> setBlacksmith(value);
            case THEFT -> setTheft(value);
            case ALCHEMY -> setAlchemy(value);
            case ONE_HANDED_WEAPON -> setOneHandedWeapon(value);
            case TWO_HANDED_WEAPON -> setTwoHandedWeapon(value);
            case POLE_WEAPON -> setPoleWeapon(value);
            case CHOPPING_WEAPON -> setChoppingWeapon(value);
            case LONG_RANGE_WEAPON -> setLongRangeWeapon(value);
            case KNOWLEDGE -> setKnowledge(value);
            case ENERGY -> setEnergy(value);
        }
    }

    public void setStat(StatsEnum statType, String value) {
        int statValue = 5;
        if (value.matches("\\d+")) statValue = Integer.parseInt(value);
        setStat(statType, statValue);
    }

    public int getStat(StatsEnum statType) {
        return switch (statType) {
            case MAX_HP -> getMaxHp();
            case HP -> getHp();
            case LEVEL -> getLevel();
            case STRENGTH -> getStrength();
            case SPEED -> getSpeed();
            case AGILITY -> getAgility();
            case INTELLIGENCE -> getIntelligence();
            case LUCK -> getLuck();
            case ELOQUENCE -> getEloquence();
            case BLACKSMITH -> getBlacksmith();
            case THEFT -> getTheft();
            case ALCHEMY -> getAlchemy();
            case ONE_HANDED_WEAPON -> getOneHandedWeapon();
            case TWO_HANDED_WEAPON -> getTwoHandedWeapon();
            case POLE_WEAPON -> getPoleWeapon();
            case CHOPPING_WEAPON -> getChoppingWeapon();
            case LONG_RANGE_WEAPON -> getLongRangeWeapon();
            case KNOWLEDGE -> getKnowledge();
            case ENERGY -> getEnergy();
        };
    }
}
