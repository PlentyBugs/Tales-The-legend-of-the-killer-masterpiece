package JGame.Items.Potions;

import JGame.Effects.Effect;
import JGame.Items.Grade;
import JGame.Items.Item;
import JGame.Items.Rarity;
import JGame.LiveCreatures.LiveCreature;

public class Potion extends Item {

    protected PotionMaterial potionMaterial;
    protected Effect effect;

    public Potion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        this.name = "Зелье";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;
    }

    public void use(LiveCreature liveCreature){
        liveCreature.addEffect(effect);
    }
}
