package Creatures;

public class StatsBonus {

    protected int strength;
    protected int speed;
    protected int agility;
    protected int intelligence;
    protected int luck;
    protected int eloquence;
    protected int blacksmith;
    protected int theft;
    protected int alchemy;
    protected int one_handed_weapon;
    protected int two_handed_weapon;
    protected int pole_weapon;
    protected int chopping_weapon;
    protected int long_range_weapon;

    protected int knowledge;
    protected int energy;

    protected int militarism;
    protected int pacifism;

    public StatsBonus(){
        clear();
    }

    public void upStat(StatsEnum stat){
        switch (stat){
            case STRENGTH: strength += 1; break;
            case SPEED: speed += 1; break;
            case AGILITY: agility += 1; break;
            case INTELLIGENCE: intelligence += 1; break;
            case LUCK: luck += 1; break;
            case ELOQUENCE: eloquence += 1; break;
            case BLACKSMITH: blacksmith += 1; break;
            case THEFT: theft += 1; break;
            case ALCHEMY: alchemy += 1; break;
            case ONEHANDEDWEAPON: one_handed_weapon += 1; break;
            case TWOHANDEDWEAPON: two_handed_weapon += 1; break;
            case POLEWEAPON: pole_weapon += 1; break;
            case CHOPPINGWEAPON: chopping_weapon += 1; break;
            case LONGRANGEWEAPON: long_range_weapon += 1; break;
            case KNOWLEDGE: knowledge += 1; break;
            case ENERGY: energy += 1; break;
            case MILITARISM: militarism += 1; break;
            case PACIFISM: pacifism += 1; break;
        }
    }

    public void upStat(StatsEnum stat, int count){
        switch (stat){
            case STRENGTH: strength += count; break;
            case SPEED: speed += count; break;
            case AGILITY: agility += count; break;
            case INTELLIGENCE: intelligence += count; break;
            case LUCK: luck += count; break;
            case ELOQUENCE: eloquence += count; break;
            case BLACKSMITH: blacksmith += count; break;
            case THEFT: theft += count; break;
            case ALCHEMY: alchemy += count; break;
            case ONEHANDEDWEAPON: one_handed_weapon += count; break;
            case TWOHANDEDWEAPON: two_handed_weapon += count; break;
            case POLEWEAPON: pole_weapon += count; break;
            case CHOPPINGWEAPON: chopping_weapon += count; break;
            case LONGRANGEWEAPON: long_range_weapon += count; break;
            case KNOWLEDGE: knowledge += count; break;
            case ENERGY: energy += count; break;
            case MILITARISM: militarism += count; break;
            case PACIFISM: pacifism += count; break;
        }
    }

    public int getAgility() {
        return agility;
    }

    public int getAlchemy() {
        return alchemy;
    }

    public int getBlacksmith() {
        return blacksmith;
    }

    public int getChopping_weapon() {
        return chopping_weapon;
    }

    public int getEloquence() {
        return eloquence;
    }

    public int getEnergy() {
        return energy;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public int getLong_range_weapon() {
        return long_range_weapon;
    }

    public int getLuck() {
        return luck;
    }

    public int getMilitarism() {
        return militarism;
    }

    public int getOne_handed_weapon() {
        return one_handed_weapon;
    }

    public int getPacifism() {
        return pacifism;
    }

    public int getPole_weapon() {
        return pole_weapon;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public int getTheft() {
        return theft;
    }

    public int getTwo_handed_weapon() {
        return two_handed_weapon;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setAlchemy(int alchemy) {
        this.alchemy = alchemy;
    }

    public void setBlacksmith(int blacksmith) {
        this.blacksmith = blacksmith;
    }

    public void setChopping_weapon(int chopping_weapon) {
        this.chopping_weapon = chopping_weapon;
    }

    public void setEloquence(int eloquence) {
        this.eloquence = eloquence;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    public void setLong_range_weapon(int long_range_weapon) {
        this.long_range_weapon = long_range_weapon;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void setMilitarism(int militarism) {
        this.militarism = militarism;
    }

    public void setOne_handed_weapon(int one_handed_weapon) {
        this.one_handed_weapon = one_handed_weapon;
    }

    public void setPacifism(int pacifism) {
        this.pacifism = pacifism;
    }

    public void setPole_weapon(int pole_weapon) {
        this.pole_weapon = pole_weapon;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setTheft(int theft) {
        this.theft = theft;
    }

    public void setTwo_handed_weapon(int two_handed_weapon) {
        this.two_handed_weapon = two_handed_weapon;
    }

    public void clear(){
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
}
