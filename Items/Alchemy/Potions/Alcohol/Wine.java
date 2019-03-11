package Items.Alchemy.Potions.Alcohol;

import Effects.AlcoholBuff;
import Effects.EffectType;
import Items.Alchemy.Potions.PotionMaterial;
import Items.Grade;
import Items.Rarity;

public class Wine extends Alcohol {

    public Wine(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Вино";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new AlcoholBuff(EffectType.MOMENT);
    }

    public Wine(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON);
    }
}
