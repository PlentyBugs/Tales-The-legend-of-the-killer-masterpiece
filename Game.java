import Abilities.Auras.Vision;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Abilities.Passive.TwoOneHandedWeapon;
import Items.*;
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
        System.getProperty("user.dir");
        Player player = new Player(0,0, "Вы",1,250);
        Map map = new Map(player,20,20);
        try{
            FileInputStream fis = new FileInputStream("./temp.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            map = (Map) inputStream.readObject();
            map.setMapHeight();
            map.setMapWidth();
            map.setPlayer(player);
            inputStream.close();
        }catch(Exception e){
            System.out.println("Error" +e.getMessage());
        }


        player.addAbility(new TwoOneHandedWeapon(), new CriticalStrike(), new Evasion(), new Vision());


        Sword testerSword = new Sword(Material.MYTHRIL, Rarity.UNCOMMON, Grade.MAGIC, 3, WeaponType.ONEHANDED);
        testerSword.countProperty();

        player.addItemToInventory(
                testerSword,
                new Torso(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 2),
                new Helmet(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 1)
                );

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
