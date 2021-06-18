package creature.peaceful;

import conversation.Conversation;
import support.Property;
import support.CreatureProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Peaceful {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Peaceful.propertyList);
        propertyList.add(CreatureProperty.DEALER);
    }

    public static final Dealer commonDealer = new Dealer(0,0, "", 0,0);

    public Dealer(){
        this(0,0,"Торговец",1,100);
    }

    public Dealer(int x, int y, String name, int lvl, int hp) {
        super(x, y, "Торговец " + name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        talkative = true;

        this.name = name;
        color = new Color(255, 100, 204);

        stats.setStrength(510);
        stats.setSpeed(120);
        stats.setAgility(5);
        stats.setIntelligence(310);
        stats.setLuck(210);
        stats.setEloquence(240);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOneHandedWeapon(5);
        stats.setTwoHandedWeapon(5);
        stats.setPoleWeapon(5);
        stats.setChoppingWeapon(5);
        stats.setLongRangeWeapon(5);

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);

        conversation = new Conversation();

        if(talkative){
            initializeWindowConv();
        }
    }

    @Override
    public Dealer clone() throws CloneNotSupportedException
    {
        return (Dealer) super.clone();
    }

    @Override
    public Dealer getClearCopy() {
        return new Dealer();
    }

    public static Dealer getInstance() {
        return commonDealer;
    }
}
