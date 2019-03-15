package Items.Enchanting;

import Abilities.Enchants.Enchant;
import Items.Item;

public class EnchantStone extends Item {
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
