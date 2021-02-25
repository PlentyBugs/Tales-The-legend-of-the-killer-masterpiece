package Abilities.Enchants;

import Abilities.Ability;
import Creatures.LiveCreature;
import Items.Item;

public class Enchant extends Ability {
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
