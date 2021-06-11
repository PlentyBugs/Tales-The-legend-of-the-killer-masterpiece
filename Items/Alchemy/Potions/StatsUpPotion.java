package Items.Alchemy.Potions;

import Creatures.StatsEnum;
import Effects.EffectType;
import Effects.StatsUpEffect;
import Items.Grade;
import Items.Rarity;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class StatsUpPotion extends Potion {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Potion.propertyList);
    }

    public StatsUpPotion(PotionMaterial potionMaterial, Rarity rarity, Grade grade, StatsEnum stat){
        super(potionMaterial, rarity, grade);
        this.name = "Зелье повышения " + stat;
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new StatsUpEffect(EffectType.MOMENT, stat);
    }

    public StatsUpPotion(StatsEnum stat){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON, stat);
    }

    public StatsUpPotion(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON, StatsEnum.STRENGTH);
    }
    @Override
    public void countCost(){
        cost = effect.getPower()*1700;
    }
}
