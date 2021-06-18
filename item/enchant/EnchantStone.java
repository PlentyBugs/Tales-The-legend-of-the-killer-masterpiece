package item.enchant;

import abilities.enchants.Enchant;
import item.Item;
import support.ItemProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class EnchantStone extends Item {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Item.propertyList);
        propertyList.add(ItemProperty.ENCHANT_STONE);
    }
    private Enchant enchant;

    public EnchantStone(){
        name = "Камень зачарования";
        cost = 500000;
    }

    public void setEnchant(Enchant enchant) {
        this.enchant = enchant;
    }

    public Enchant getEnchant() {
        return enchant;
    }

    @Override
    public String getName(){
        if(enchant != null){
            return name + "(" + enchant.getName() + ")";
        }
        return name + "(Пусто)";
    }
}
