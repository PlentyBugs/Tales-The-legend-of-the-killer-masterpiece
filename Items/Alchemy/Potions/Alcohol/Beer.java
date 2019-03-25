package Items.Alchemy.Potions.Alcohol;

import Effects.AlcoholBuff;
import Effects.Effect;
import Effects.EffectType;
import Items.Alchemy.Potions.PotionMaterial;
import Items.Grade;
import Items.Rarity;

public class Beer extends Alcohol {

    public Beer(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Пиво";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new AlcoholBuff(EffectType.MOMENT);
    }

    public Beer(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON);
    }

    public Beer setEffect(Effect effect) {
        this.effect = effect;
        return this;
    }
}
