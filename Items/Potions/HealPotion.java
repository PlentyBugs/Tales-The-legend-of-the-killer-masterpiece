package JGame.Items.Potions;

import JGame.Effects.EffectType;
import JGame.Effects.Heal;
import JGame.Items.Grade;
import JGame.Items.Rarity;

public class HealPotion extends Potion {

    public HealPotion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Зелье жизни";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new Heal(EffectType.ACTIVE);
    }
}
