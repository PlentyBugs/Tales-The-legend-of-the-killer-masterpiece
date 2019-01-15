package Items.Potions;

import Effects.EffectType;
import Effects.Heal;
import Items.Grade;
import Items.Rarity;

public class HealPotion extends Potion {

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
}
