package JGame.LiveCreatures;

import JGame.LiveCreatures.StatsEnum;

public class Stats {

    public int strength;
    public int speed;
    public int agility;
    public int intelligence;
    public int luck;
    public int eloquence;
    public int blacksmith;
    public int alchemy;
    public int one_handed_weapon;
    public int two_handed_weapon;
    public int pole_weapon;
    public int chopping_weapon;
    public int long_range_weapon;

    public int knowledge;
    public int energy;

    public int militarism;
    public int pacifism;

    public void upStat(StatsEnum stat){
        switch (stat){
            case STRENGTH: strength += 1; break;
            case SPEED: speed += 1; break;
            case AGILITY: agility += 1; break;
            case INTELLIGENCE: intelligence += 1; break;
            case LUCK: luck += 1; break;
            case ELOQUENCE: eloquence += 1; break;
            case BLACKSMITH: blacksmith += 1; break;
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
}
