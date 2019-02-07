package Items.QuestItems;

import Items.Grade;
import Items.Material;
import Items.Rarity;
import Items.Armors.Ring;

public class KingGoblinRing extends Ring {

    public KingGoblinRing(){
        stockName = "Кольцо Короля гоблинов";
        this.protection = 250;
        this.material = Material.CRYSTAL;
        this.grade = Grade.ARTIFACT;
        this.rarity = Rarity.MYSTICAL;
        quality = 100;

        countProperty();
    }
}
