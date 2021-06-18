package creature;

import java.io.Serializable;

public class StatsBonus implements Serializable {

    protected int maxHp;
    protected int hp;
    protected int level;
    protected int strength;
    protected int speed;
    protected int agility;
    protected int intelligence;
    protected int luck;
    protected int eloquence;
    protected int blacksmith;
    protected int theft;
    protected int alchemy;
    protected int oneHandedWeapon;
    protected int twoHandedWeapon;
    protected int poleWeapon;
    protected int choppingWeapon;
    protected int longRangeWeapon;

    protected int knowledge;
    protected int energy;

    protected int militarism;
    protected int pacifism;

    public StatsBonus(){
        clear();
    }

    public void upStat(StatsEnum stat){
        switch (stat) {
            case STRENGTH -> strength += 1;
            case SPEED -> speed += 1;
            case AGILITY -> agility += 1;
            case INTELLIGENCE -> intelligence += 1;
            case LUCK -> luck += 1;
            case ELOQUENCE -> eloquence += 1;
            case BLACKSMITH -> blacksmith += 1;
            case THEFT -> theft += 1;
            case ALCHEMY -> alchemy += 1;
            case ONE_HANDED_WEAPON -> oneHandedWeapon += 1;
            case TWO_HANDED_WEAPON -> twoHandedWeapon += 1;
            case POLE_WEAPON -> poleWeapon += 1;
            case CHOPPING_WEAPON -> choppingWeapon += 1;
            case LONG_RANGE_WEAPON -> longRangeWeapon += 1;
            case KNOWLEDGE -> knowledge += 1;
            case ENERGY -> energy += 1;
        }
    }

    public void upStat(StatsEnum stat, int count){
        switch (stat) {
            case STRENGTH -> strength += count;
            case SPEED -> speed += count;
            case AGILITY -> agility += count;
            case INTELLIGENCE -> intelligence += count;
            case LUCK -> luck += count;
            case ELOQUENCE -> eloquence += count;
            case BLACKSMITH -> blacksmith += count;
            case THEFT -> theft += count;
            case ALCHEMY -> alchemy += count;
            case ONE_HANDED_WEAPON -> oneHandedWeapon += count;
            case TWO_HANDED_WEAPON -> twoHandedWeapon += count;
            case POLE_WEAPON -> poleWeapon += count;
            case CHOPPING_WEAPON -> choppingWeapon += count;
            case LONG_RANGE_WEAPON -> longRangeWeapon += count;
            case KNOWLEDGE -> knowledge += count;
            case ENERGY -> energy += count;
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

    public int getChoppingWeapon() {
        return choppingWeapon;
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

    public int getLongRangeWeapon() {
        return longRangeWeapon;
    }

    public int getLuck() {
        return luck;
    }

    public int getMilitarism() {
        return militarism;
    }

    public int getOneHandedWeapon() {
        return oneHandedWeapon;
    }

    public int getPacifism() {
        return pacifism;
    }

    public int getPoleWeapon() {
        return poleWeapon;
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

    public int getTwoHandedWeapon() {
        return twoHandedWeapon;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getLevel() {
        return level;
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

    public void setChoppingWeapon(int choppingWeapon) {
        this.choppingWeapon = choppingWeapon;
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

    public void setLongRangeWeapon(int longRangeWeapon) {
        this.longRangeWeapon = longRangeWeapon;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void setMilitarism(int militarism) {
        this.militarism = militarism;
    }

    public void setOneHandedWeapon(int oneHandedWeapon) {
        this.oneHandedWeapon = oneHandedWeapon;
    }

    public void setPacifism(int pacifism) {
        this.pacifism = pacifism;
    }

    public void setPoleWeapon(int poleWeapon) {
        this.poleWeapon = poleWeapon;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setTheft(int theft) {
        this.theft = theft;
    }

    public void setTwoHandedWeapon(int twoHandedWeapon) {
        this.twoHandedWeapon = twoHandedWeapon;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setLevel(int level) {
        this.level = level;
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
        oneHandedWeapon = 0;
        twoHandedWeapon = 0;
        poleWeapon = 0;
        choppingWeapon = 0;
        longRangeWeapon = 0;
        knowledge = 0;
        energy = 0;
        militarism = 0;
        pacifism = 0;
    }
}
