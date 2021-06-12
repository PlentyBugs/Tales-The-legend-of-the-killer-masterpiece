import Abilities.Buffs.DamageUpBuff;
import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Abilities.Enchants.EnchantUse;
import Abilities.Enchants.Weapon.Vampirism;
import Abilities.Passive.LittleFool;
import Creatures.Difficulty;
import Creatures.Player;
import Creatures.StatsEnum;
import Items.*;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.Weapons.Bows.ShortBow;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Staffs.Staff;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;
import Locations.Map;
import Windows.FieldWindow;
import Windows.SupportWindows.ChooseDifficultyWindow;
import Windows.SupportWindows.LoadGameWindow;
import Windows.SupportWindows.StartWindow;

import java.io.*;

public class Game {

    public static void main(String[] args) throws InterruptedException, IOException {

        StartWindow startWindow = new StartWindow();

        synchronized (StartWindow.class) {
            try {
                StartWindow.class.wait();
            } catch (InterruptedException ex) {
                System.out.println("Game Started");
            }
        }

        String game = startWindow.getGame();
        startWindow.close();
        if(game.equals("new")) {
            Player player = new Player(1, 1, "Вы", 1, 250);
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
                    new Sword(Material.BRONZE, Rarity.DIVINE, Grade.MAGIC, 0, WeaponType.ONE_HANDED)
                            .addEnchant(new Vampirism()),
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

            ChooseDifficultyWindow chooseDifficultyWindow = new ChooseDifficultyWindow();
            Difficulty difficulty = Difficulty.STOPIT;
            while (!chooseDifficultyWindow.getCheck()) {
                difficulty = chooseDifficultyWindow.getDifficulty();
                player.setDifficulty(difficulty);
            }

            FieldWindow fieldWindow1 = new FieldWindow("Поле", map);

            player.setWindowInterface(fieldWindow1);

            chooseDifficultyWindow.close();

            Narrator narrator = new Narrator(difficulty, fieldWindow1);

            narrator.theBeginning(player);
        } else if(game.equals("load")){
            FindFile ff = new FindFile();

            ff.findFile("*.txt", new File("./Saves/"));

            LoadGameWindow loadGameWindow = new LoadGameWindow(ff.getFiles());

            String fileName;
            do {
                System.out.println();
                fileName = loadGameWindow.getFileName();
            } while (fileName == null);
            loadGameWindow.close();

            try (FileInputStream fin = new FileInputStream("./Saves/" + fileName);
                 ObjectInputStream ois = new ObjectInputStream(fin)) {
                FieldWindow fieldWindow = (FieldWindow) ois.readObject();
                Player player = fieldWindow.getPlayer();
                fieldWindow.drawMap();
                player.initWindows();
                fieldWindow.drawAllPlayerWindow(fieldWindow.getPlayer(), fieldWindow);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
