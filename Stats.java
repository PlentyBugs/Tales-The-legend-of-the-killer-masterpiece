package JGame;

public class Stats {

    int strength;
    int speed;
    int agility;
    int intelligence;
    int luck;
    int eloquence;
    int blacksmith;
    int alchemy;
    int one_handed_weapon;
    int two_handed_weapon;
    int pole_weapon;
    int chopping_weapon;
    int long_range_weapon;

    int knowledge;
    int energy;

    int militarism;
    int pacifism;

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
