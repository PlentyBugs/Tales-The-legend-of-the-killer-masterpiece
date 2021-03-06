package abilities.enchants;

import abilities.Ability;
import creature.LiveCreature;
import item.Item;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Enchant extends Ability {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
        propertyList.add(AbilityProperty.ENCHANTMENT);
    }

    protected double damage;

    protected Item item;
    protected EnchantType enchantType;
    protected int power;
    protected String description;
    protected EnchantUse enchantUse;

    public Enchant(){}

    public Enchant(Item item){
        this.item = item;
    }

    public void use(LiveCreature liveCreature){
        if(enchantUse != null){
            enchantUse.use(liveCreature);
        }
    }

    public Enchant setEnchantUse(EnchantUse enchantUse){
        this.enchantUse = enchantUse;
        return this;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public EnchantType getEnchantType() {
        return enchantType;
    }

    public String getDescription() {
        return description;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Enchant setEnchantType(EnchantType enchantType) {
        this.enchantType = enchantType;
        return this;
    }

    public Enchant setDescription(String description) {
        this.description = description;
        return this;
    }

    public Enchant setName(String name) {
        this.name = name;
        return this;
    }
}
