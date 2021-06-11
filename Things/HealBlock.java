package Things;

import Creatures.Player;
import support.Property;
import support.GeneralProperty;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class HealBlock extends Thing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.HEAL_BLOCK);
    }

    public HealBlock(){
        this(0, 0);
    }

    public HealBlock(int x, int y){
        this.x = x;
        this.y = y;
        name = "Лужица Жизни";
        color = new Color(255,255,255);
        isStep = true;
    }

    public void heal(Player player){
        int healCount = player.getLvl()*50 + 100;
        if (healCount+player.getHp() > player.getMaxHp()){
            player.setHp(player.getMaxHp());
        } else {
            player.setHp(player.getHp() + healCount);
        }
    }
    @Override
    public HealBlock getClearCopy() {
        return new HealBlock();
    }
}
