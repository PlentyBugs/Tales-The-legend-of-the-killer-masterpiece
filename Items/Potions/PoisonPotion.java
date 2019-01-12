package JGame.Items.Potions;

import JGame.Effects.EffectType;
import JGame.Effects.Poison;
import JGame.Items.Grade;
import JGame.Items.Rarity;

public class PoisonPotion extends Potion {

    public PoisonPotion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Яд";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new Poison(EffectType.ACTIVE);
    }
}
