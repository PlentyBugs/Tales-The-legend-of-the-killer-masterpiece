package Items.Alchemy.Potions;

import Creatures.LiveCreature;
import Effects.Effect;
import Effects.EffectType;
import Effects.Poison;
import Items.BattleItem;
import Items.Grade;
import Items.Item;
import Items.Rarity;
import support.ItemProperty;
import support.Property;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Potion extends Item implements BattleItem  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Item.propertyList);
        propertyList.add(ItemProperty.POTION);
    }

    @Serial
    private static final long serialVersionUID = -1676624921038826829L;

    protected PotionMaterial potionMaterial;
    protected Effect effect;

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

    public Potion setEffect(Effect effect) {
        this.effect = effect;
        return this;
    }

    public Effect getEffect() {
        return effect;
    }

    public PotionMaterial getPotionMaterial() {
        return potionMaterial;
    }

    public int compareTo(Potion o) {
        if(
                getName().equals(o.getName()) &&
                getGrade() == o.getGrade() &&
                getQuality() == o.getQuality() &&
                getRarity() == o.getRarity() &&
                effect.getPower() == o.getEffect().getPower()
        ){
            return 0;
        }
        return -1;
    }

    public Potion countProperty(){
        int addPower = 0;
        switch (potionMaterial) {
            case WATER -> addPower += 30;
            case AIR -> addPower += 800;
            case BLOOD -> addPower += 4500;
            case DRAGONBLOOD -> addPower += 33000;
            case GODBLOOD -> addPower += 178000;
        }

        switch (grade) {
            case COMMON -> addPower += 300;
            case MAGIC -> addPower += 4800;
            case CURSE -> addPower += 13000;
            case ARTIFACT -> addPower += 51000;
            case HEROIC -> addPower += 230000;
            case ABOVE_THE_GODS -> addPower += 580000;
        }
        switch (rarity) {
            case COMMON -> addPower += 120;
            case UNCOMMON -> addPower += 3500;
            case RARE -> addPower += 14200;
            case MYSTICAL -> addPower += 35400;
            case LEGENDARY -> addPower += 78000;
            case DRAGON -> addPower += 347000;
            case DIVINE -> addPower += 975000;
        }

        effect.setPower(effect.getPower() + addPower);
        return this;
    }

    @Override
    public void useItemInBattle() {

    }

    @Override
    public void countCost(){
        int addCost = 0;
        switch (potionMaterial) {
            case WATER -> addCost += 500;
            case AIR -> addCost += 1450;
            case BLOOD -> addCost += 4500;
            case DRAGONBLOOD -> addCost += 14200;
            case GODBLOOD -> addCost += 45200;
        }

        switch (grade) {
            case COMMON -> addCost += 300;
            case MAGIC -> addCost += 2100;
            case CURSE -> addCost += 6400;
            case ARTIFACT -> addCost += 17500;
            case HEROIC -> addCost += 57800;
            case ABOVE_THE_GODS -> addCost += 145000;
        }

        switch (rarity) {
            case COMMON -> addCost += 120;
            case UNCOMMON -> addCost += 1400;
            case RARE -> addCost += 3540;
            case MYSTICAL -> addCost += 18000;
            case LEGENDARY -> addCost += 42000;
            case DRAGON -> addCost += 54200;
            case DIVINE -> addCost += 175000;
        }

        cost = addCost;
    }

    @Override
    public Potion getClearCopy() {
        Potion potion = new Potion(potionMaterial, rarity, grade);
        potion.setName(name);
        potion.setEffect(effect);
        potion.setMaterial(material);
        potion.setQuality(quality);
        return potion;
    }
}
