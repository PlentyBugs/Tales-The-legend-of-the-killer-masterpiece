package Items.Alchemy.Potions;

import Effects.EffectType;
import Effects.NecrosisEffect;
import Items.Grade;
import Items.Rarity;

public class NecrosisPotion extends Potion {

    public NecrosisPotion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Зелье некроза";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new NecrosisEffect(EffectType.MOMENT);
    }

    public NecrosisPotion(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON);
    }
}
