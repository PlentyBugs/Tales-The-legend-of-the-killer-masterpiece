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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Game {
    public static void main(String[] args) throws InterruptedException, IOException {

        Player player = new Player(0,0, "Вы",1,250);
        Map map = new Map(player,150,150);
        try{
            FileInputStream fis = new FileInputStream("./Maps/temp.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            map = (Map) inputStream.readObject();
            map.setMapHeight();
            map.setMapWidth();
            map.setPlayer(player);
            inputStream.close();
        }catch(Exception e){
            System.out.println("Error" +e.getMessage());
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
        for(Item item : player.getInventory()){
            item.countProperty();
        }

        ChooseDifficultyWindow chooseDifficultyWindow = new ChooseDifficultyWindow();
        Difficulty difficulty = Difficulty.STOPIT;
        while (!chooseDifficultyWindow.getCheck()){
            difficulty = chooseDifficultyWindow.getDifficulty();
            player.setDifficulty(difficulty);
        }

        FieldWindow fieldWindow1 = new FieldWindow("Поле", 1024, 720, player.getVision(), player, map.getMap(player.getX(), player.getY()));
        fieldWindow1.setCurrentMap(map);

        player.setFieldWindow(fieldWindow1);

        chooseDifficultyWindow.close();

        Narrator narrator = new Narrator(difficulty, fieldWindow1);

        narrator.theBeginning(player);

        while (player.hp > 0){
            break;
        }
    }
}
