package Items.Alchemy.Potions;

import Effects.DamageUpEffect;
import Effects.EffectType;
import Items.Grade;
import Items.Rarity;

public class PowerPotion extends Potion {

    public PowerPotion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Зелье жизни";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new DamageUpEffect(EffectType.MOMENT);
    }

    public PowerPotion(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON);
    }
}
