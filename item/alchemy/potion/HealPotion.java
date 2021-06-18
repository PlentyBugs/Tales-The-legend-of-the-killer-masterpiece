package item.alchemy.potion;

import effect.EffectType;
import effect.Heal;
import item.Grade;
import item.Rarity;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class HealPotion extends Potion {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Potion.propertyList);
    }

    public HealPotion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Зелье жизни";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new Heal(EffectType.MOMENT);
    }

    public HealPotion(){
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

        cost = addCost + effect.getPower()*25;
    }
}
