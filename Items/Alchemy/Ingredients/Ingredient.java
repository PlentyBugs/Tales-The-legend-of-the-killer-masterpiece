package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.Potion;
import Items.Alchemy.Potions.PotionMaterial;
import Items.Grade;
import Items.Rarity;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Ingredient extends Potion  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Potion.propertyList);
        propertyList.add(GeneralProperty.INGREDIENT);
    }

    protected ArrayList<Potion> usage;
    protected Color color;

    public Ingredient(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON);
    }

    public Ingredient(PotionMaterial potionMaterial, Rarity rarity, Grade grade) {
        super(potionMaterial, rarity, grade);
        usage = new ArrayList<>();
        stackable = true;
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
