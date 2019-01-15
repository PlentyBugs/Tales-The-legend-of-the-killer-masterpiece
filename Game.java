import Ability.Passive.TwoOneHandedWeapon;
import Items.*;
import Items.Potions.HealPotion;
import Items.Potions.PoisonPotion;
import Items.Potions.PotionMaterial;
import LiveCreatures.Difficulty;
import LiveCreatures.Player;
import Locations.Map;
import Windows.SupportWindows.ChooseDifficultyWindow;
import Windows.FieldWindow;

import java.io.IOException;
public class Game {
    public static void main(String[] args) throws InterruptedException, IOException {
        Player player = new Player(0,0, "Вы",1,250);

        player.addAbility(new TwoOneHandedWeapon());

        player.addItemToInventory(
                new Sword(Material.IRON, Rarity.COMMON, Grade.COMMON, 3, WeaponType.ONEHANDED),
                new Torso(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 2),
                new Helmet(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 1),
                new HealPotion(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON),
                new PoisonPotion(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON),
                new PoisonPotion(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON)
                );

        Map map = new Map(player, 100, 200);

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
