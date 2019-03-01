package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.Potion;
import Items.Alchemy.Potions.PotionMaterial;
import Items.Grade;
import Items.Rarity;

import java.awt.*;
import java.util.ArrayList;

public class Ingredient extends Potion {
    protected ArrayList<Potion> usage;
    protected Color color;

    public Ingredient(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON);
    }

    public Ingredient(PotionMaterial potionMaterial, Rarity rarity, Grade grade) {
        super(potionMaterial, rarity, grade);
    }

    public ArrayList<Potion> getUsage() {
        return usage;
    }

    public void setUsage(ArrayList<Potion> usage) {
        this.usage = usage;
    }

    public boolean useIn(Potion potion){
        for(Potion ptn : usage){
            if(ptn.getClass().toString().equals(potion.getClass().toString())){
                return true;
            }
        }
        return false;
    }

    public void addUsage(Potion potion){
        usage.add(potion);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
