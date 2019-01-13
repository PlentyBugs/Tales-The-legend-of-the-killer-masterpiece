package Items.Potions;

import Effects.Effect;
import Items.Grade;
import Items.Item;
import Items.Rarity;
import LiveCreatures.LiveCreature;

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

    public void setPotionMaterial(PotionMaterial potionMaterial) {
        this.potionMaterial = potionMaterial;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }

    public PotionMaterial getPotionMaterial() {
        return potionMaterial;
    }
}
