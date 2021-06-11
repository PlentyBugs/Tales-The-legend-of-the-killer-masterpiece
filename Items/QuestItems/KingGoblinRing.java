package Items.QuestItems;

import Items.Grade;
import Items.Material;
import Items.Rarity;
import Items.Armors.Ring;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class KingGoblinRing extends Ring  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ring.propertyList);
    }

    public KingGoblinRing(){
        stockName = "Кольцо Короля гоблинов";
        this.protection = 250;
        this.material = Material.CRYSTAL;
        this.grade = Grade.ARTIFACT;
        this.rarity = Rarity.MYSTICAL;
        quality = 100;

        countProperty();
    }
}
