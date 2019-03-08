package Creatures;

public class Stats extends StatsBonus {

    private StatsBonus bonusStats;
    private static final long serialVersionUID = 5122589181835788912L;

    public Stats(){
        bonusStats = new StatsBonus();
        strength = 0;
        speed = 0;
        agility = 0;
        intelligence = 0;
        luck = 0;
        eloquence = 0;
        blacksmith = 0;
        theft = 0;
        alchemy = 0;
        one_handed_weapon = 0;
        two_handed_weapon = 0;
        pole_weapon = 0;
        chopping_weapon = 0;
        long_range_weapon = 0;
        knowledge = 0;
        energy = 0;
        militarism = 0;
        pacifism = 0;
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

    public int getChopping_weapon() {
        return chopping_weapon + bonusStats.getChopping_weapon();
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

    public int getLong_range_weapon() {
        return long_range_weapon + bonusStats.getLong_range_weapon();
    }

    public int getLuck() {
        return luck + bonusStats.getLuck();
    }

    public int getMilitarism() {
        return militarism + bonusStats.getMilitarism();
    }

    public int getOne_handed_weapon() {
        return one_handed_weapon + bonusStats.getOne_handed_weapon();
    }

    public int getPacifism() {
        return pacifism + bonusStats.getPacifism();
    }

    public int getPole_weapon() {
        return pole_weapon + bonusStats.getPole_weapon();
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

    public int getTwo_handed_weapon() {
        return two_handed_weapon + bonusStats.getTwo_handed_weapon();
    }

    public StatsBonus getBonusStats() {
        return bonusStats;
    }
}
