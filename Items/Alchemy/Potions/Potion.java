package Items.Alchemy.Potions;

import Creatures.LiveCreature;
import Effects.Effect;
import Effects.EffectType;
import Effects.Poison;
import Items.BattleItem;
import Items.Grade;
import Items.Item;
import Items.Rarity;

public class Potion extends Item implements BattleItem {

    protected PotionMaterial potionMaterial;
    protected Effect effect;
    private static final long serialVersionUID = -1676624921038826829L;

    public Potion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        this.name = "Зелье";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;
        stackable = true;
        effect = new Poison(EffectType.MOMENT);
    }

    public void use(LiveCreature liveCreature){
        if(effect.getType() != EffectType.MOMENT){
            liveCreature.addEffect(effect);
        } else {
            liveCreature.useMomentEffect(effect);
        }
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

    @Override
    public int compareTo(Object o) {
        if(getName().equals(((Item) o).getName()) && getGrade() == ((Item) o).getGrade() && getQuality() == ((Item) o).getQuality() && getRarity() == ((Item) o).getRarity() && effect.getPower() == ((Potion)o).getEffect().getPower()){
            return 0;
        }
        return -1;
    }

    public Potion countProperty(){
        int addPower = 0;
        switch (potionMaterial){
            case WATER: addPower += 30; break;
            case AIR: addPower += 800; break;
            case BLOOD: addPower += 4500; break;
            case DRAGONBLOOD: addPower += 33000; break;
            case GODBLOOD: addPower += 178000; break;
        }

        switch (grade){
            case COMMON: addPower += 300; break;
            case MAGIC: addPower += 4800; break;
            case CURSE: addPower += 13000; break;
            case ARTIFACT: addPower += 51000; break;
            case HEROIC: addPower += 230000; break;
            case ABOVETHEGODS: addPower += 580000; break;
        }
        switch (rarity){
            case COMMON: addPower += 120; break;
            case UNCOMMON: addPower += 3500; break;
            case RARE: addPower += 14200; break;
            case MYSTICAL: addPower += 35400; break;
            case LEGENDARY: addPower += 78000; break;
            case DRAGON: addPower += 347000; break;
            case DIVINE: addPower += 975000; break;
        }

        effect.setPower(effect.getPower() + addPower);
        return this;
    }

    @Override
    public void useItemInBattle() {

    }
}
