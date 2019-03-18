package Abilities.Enchants;

import Abilities.Ability;
import Creatures.LiveCreature;
import Items.Item;

public class Enchant<T extends Item> extends Ability {
    protected double damage;

    protected T item;
    protected EnchantType enchantType;
    protected int power;
    protected String description;

    public Enchant(){}

    public Enchant(T item){
        this.item = item;
    }

    public void use(LiveCreature liveCreature){

    }

    public void setItem(T item){
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
}
