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

    public void countProperty() {
        int addPower = 0;
        switch (potionMaterial) {
            case WATER:
                addPower += 1;
                break;
            case AIR:
                addPower += 4;
                break;
            case BLOOD:
                addPower += 8;
                break;
            case DRAGONBLOOD:
                addPower += 32;
                break;
            case GODBLOOD:
                addPower += 64;
                break;
        }

        switch (grade) {
            case COMMON:
                addPower += 1;
                break;
            case MAGIC:
                addPower += 4;
                break;
            case CURSE:
                addPower += 8;
                break;
            case ARTIFACT:
                addPower += 16;
                break;
            case HEROIC:
                addPower += 32;
                break;
            case ABOVETHEGODS:
                addPower += 64;
                break;
        }
        switch (rarity) {
            case COMMON:
                addPower += 1;
                break;
            case UNCOMMON:
                addPower += 4;
                break;
            case RARE:
                addPower += 8;
                break;
            case MYSTICAL:
                addPower += 16;
                break;
            case LEGENDARY:
                addPower += 32;
                break;
            case DRAGON:
                addPower += 64;
                break;
            case DIVINE:
                addPower += 128;
                break;
        }
    }
}
