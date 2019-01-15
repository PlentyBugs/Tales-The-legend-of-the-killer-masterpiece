package Items.Potions;

import Effects.EffectType;
import Effects.Poison;
import Items.Grade;
import Items.Rarity;

public class PoisonPotion extends Potion {

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
}
