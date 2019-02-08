import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Auras.Vision;
import Abilities.Passive.TwoOneHandedWeapon;
import Items.Armors.Helmet;
import Items.Armors.Torso;
import Items.Grade;
import Items.Item;
import Items.Material;
import Items.Rarity;
import Items.Weapons.Bows.ShortBow;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;
import LiveCreatures.Difficulty;
import LiveCreatures.Player;
import Locations.Map;
import Windows.FieldWindow;
import Windows.SupportWindows.ChooseDifficultyWindow;
import Windows.SupportWindows.LoadGameWindow;
import Windows.SupportWindows.StartWindow;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;

public class Game {

    public static void main(String[] args) throws InterruptedException, IOException {

        StartWindow startWindow = new StartWindow();

        while(true){
            System.out.println();
            if(startWindow.getStartGame()){
                break;
            }
        }
        String game = startWindow.getGame();
        startWindow.close();
        if(game.equals("new")) {
            Player player = new Player(0, 0, "Вы", 1, 250);
            Map map = new Map(player, 150, 150);
            try {
                FileInputStream fis = new FileInputStream("./Maps/temp.txt");
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                map = (Map) inputStream.readObject();
                map.setMapHeight();
                map.setMapWidth();
                map.setPlayer(player);
                inputStream.close();
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }


            player.addAbility(new TwoOneHandedWeapon(), new Vision(), new DamageUp(), new DecreaseDamage());

            player.addItemToInventory(
                    new Sword(Material.BRONZE, Rarity.COMMON, Grade.CURSE, 3, WeaponType.ONEHANDED),
                    new Sword(Material.BRONZE, Rarity.COMMON, Grade.CURSE, 3, WeaponType.ONEHANDED),
                    new Sword(Material.BRONZE, Rarity.COMMON, Grade.CURSE, 3, WeaponType.TWOHANDED),
                    new ShortBow(Material.BRONZE, Rarity.COMMON, Grade.CURSE, 3, WeaponType.LONGRANGE),
                    new Torso(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 2),
                    new Helmet(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 1)
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

            FieldWindow fieldWindow1 = new FieldWindow("Поле", 1024, 720, player.getVision(), player, map.getMap(player.getX(), player.getY()));
            fieldWindow1.setCurrentMap(map);

            player.setFieldWindow(fieldWindow1);

            chooseDifficultyWindow.close();

            Narrator narrator = new Narrator(difficulty, fieldWindow1);

            narrator.theBeginning(player);

            while (player.hp > 0) {
                break;
            }
        } else if(game.equals("load")){
            FieldWindow fieldWindow = null;

            FileInputStream fin = null;
            ObjectInputStream ois = null;

            FindFile ff = new FindFile();

            ff.findFile("save*.txt", new File("./Saves/"));

            LoadGameWindow loadGameWindow = new LoadGameWindow(ff.getFiles());

            String fileName;
            while(true){
                fileName = loadGameWindow.getFileName();
                System.out.println();
                if(fileName != null){
                    break;
                }
            }

            loadGameWindow.close();

            try {

                fin = new FileInputStream("./Saves/" + fileName);
                ois = new ObjectInputStream(fin);
                fieldWindow = (FieldWindow) ois.readObject();

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {

                if (fin != null) {
                    try {
                        fin.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            Player player = fieldWindow.getPlayer();
            fieldWindow.drawMap();
            player.initWindoows();
            while (player.hp > 0) {
                break;
            }
        }
    }
}
