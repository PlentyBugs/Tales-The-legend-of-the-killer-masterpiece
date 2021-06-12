package Items.Alchemy.Potions.Alcohol;

        import Items.Alchemy.Potions.Potion;
        import Items.Alchemy.Potions.PotionMaterial;
        import Items.DiplomacyItem;
        import Items.Grade;
        import Items.Rarity;

public class Alcohol extends Potion implements DiplomacyItem {

    public Alcohol(PotionMaterial potionMaterial, Rarity rarity, Grade grade) {
        super(potionMaterial, rarity, grade);
    }

    public Alcohol countProperty() {
        int addPower = 0;
        switch (potionMaterial) {
            case WATER -> addPower += 1;
            case AIR -> addPower += 4;
            case BLOOD -> addPower += 8;
            case DRAGONBLOOD -> addPower += 32;
            case GODBLOOD -> addPower += 64;
        }

        switch (grade) {
            case COMMON -> addPower += 1;
            case MAGIC -> addPower += 4;
            case CURSE -> addPower += 8;
            case ARTIFACT -> addPower += 16;
            case HEROIC -> addPower += 32;
            case ABOVE_THE_GODS -> addPower += 64;
        }

        switch (rarity) {
            case COMMON -> addPower += 1;
            case UNCOMMON -> addPower += 4;
            case RARE -> addPower += 8;
            case MYSTICAL -> addPower += 16;
            case LEGENDARY -> addPower += 32;
            case DRAGON -> addPower += 64;
            case DIVINE -> addPower += 128;
        }
        return this;
    }

    @Override
    public void countCost(){
        cost = effect.getPower()*1200;
    }
}
