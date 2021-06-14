package Windows;

import Abilities.Buffs.DamageUpBuff;
import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Abilities.Enchants.EnchantUse;
import Abilities.Enchants.Weapon.Vampirism;
import Abilities.Passive.LittleFool;
import Creatures.Difficulty;
import Creatures.Player;
import Creatures.StatsEnum;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.*;
import Items.Weapons.Bows.ShortBow;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Staffs.Staff;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;
import Locations.Map;

import java.io.Serializable;

public interface Creator {

    default GameWindow createGame(Difficulty difficulty, MultiWindow multiWindow) {
        Player player = new Player(1, 1, "Вы", 1, 250);
        player.setDifficulty(difficulty);
        player.setX(1);
        player.setY(1);
        player.addAbility(new LittleFool());
        Map map = new Map(player, 100, 100)
                .setPlayer(player);
        /*
        try {
            FileInputStream fis = new FileInputStream("./Maps/temp.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            map = (Map) inputStream.readObject();
            map.setPlayer(player);
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
*/
        player.addItemToInventory(
                new Sword(Material.BRONZE, Rarity.DIVINE, Grade.MAGIC, 0, WeaponType.ONE_HANDED).addEnchant(new Vampirism()),
                new Sword(Material.BRONZE, Rarity.COMMON, Grade.CURSE, 0, WeaponType.TWO_HANDED),
                new ShortBow(Material.BRONZE, Rarity.COMMON, Grade.CURSE, 0, WeaponType.LONG_RANGE),
                new Staff(Material.BRONZE, Rarity.COMMON, Grade.CURSE, 0, WeaponType.ONE_HANDED),
                new Axe(Material.BRONZE, Rarity.COMMON, Grade.CURSE, 0, WeaponType.ONE_HANDED),
                new Torso(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 10000000),
                new Helmet(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 0),
                new Ring(Material.COPPER, Rarity.COMMON, Grade.COMMON, StatsEnum.ALCHEMY)
                        .addEnchant(
                                new Enchant()
                                        .setEnchantUse((EnchantUse & Serializable) liveCreature -> liveCreature.addBuffs(new DamageUpBuff(105, 5)))
                                        .setEnchantType(EnchantType.SELFUSE)
                                        .setDescription("С каждым ударом накладывает эффект повышения урона на 5% на 5 ходов")
                                        .setName("Подарок для тестера")
                        ),
                new Key()
        );
        for (Item item : player.getInventory()) {
            item.countProperty();
        }

        GameWindow gameWindow = new GameWindow(map, multiWindow);
        player.setWindowInterface(gameWindow);

//        Narrator narrator = new Narrator(difficulty, gameWindow);
//        narrator.theBeginning(player);

        return gameWindow;
    }
}
