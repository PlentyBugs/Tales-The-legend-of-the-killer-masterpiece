package Items.Alchemy.Potions;

import Effects.EffectType;
import Effects.Poison;
import Items.Grade;
import Items.Rarity;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class PoisonPotion extends Potion {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Potion.propertyList);
    }

    public PoisonPotion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Яд";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new Poison(EffectType.MOMENT);
    }

    public PoisonPotion(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON);
    }

    @Override
    public void countCost(){
        int addCost = 0;
        switch (potionMaterial){
            case WATER: addCost += 500; break;
            case AIR: addCost += 1450; break;
            case BLOOD: addCost += 4500; break;
            case DRAGONBLOOD: addCost += 14200; break;
            case GODBLOOD: addCost += 45200; break;
        }

        switch (grade){
            case COMMON: addCost += 300; break;
            case MAGIC: addCost += 2100; break;
            case CURSE: addCost += 6400; break;
            case ARTIFACT: addCost += 17500; break;
            case HEROIC: addCost += 57800; break;
            case ABOVE_THE_GODS: addCost += 145000; break;
        }
        switch (rarity){
            case COMMON: addCost += 120; break;
            case UNCOMMON: addCost += 1400; break;
            case RARE: addCost += 3540; break;
            case MYSTICAL: addCost += 18000; break;
            case LEGENDARY: addCost += 42000; break;
            case DRAGON: addCost += 54200; break;
            case DIVINE: addCost += 175000; break;
        }

        cost = addCost + effect.getPower()*40;
    }
}
