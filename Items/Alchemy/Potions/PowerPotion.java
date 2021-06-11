package Items.Alchemy.Potions;

import Effects.DamageUpEffect;
import Effects.EffectType;
import Items.Grade;
import Items.Rarity;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class PowerPotion extends Potion {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Potion.propertyList);
    }

    public PowerPotion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Зелье повышения урона";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new DamageUpEffect(EffectType.MOMENT);
    }

    public PowerPotion(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON);
    }

    @Override
    public void countCost(){
        cost = effect.getPower()*150;
    }
}
