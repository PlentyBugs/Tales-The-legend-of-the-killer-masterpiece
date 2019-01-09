package JGame;

import java.io.IOException;
public class Game {
    public static void main(String[] args) throws InterruptedException, IOException {
        Player player = new Player(0,0, "Вы");

        player.addItemToInventory(
        new Sword(Material.COPPER, Rarity.COMMON, Grade.COMMON, 3, WeaponType.ONEHANDED),
                new Torso(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 2),
                new Helmet(Material.CHAIN, Rarity.COMMON, Grade.COMMON, 1)
                );

        Map map = new Map(player, 100, 200);

        ChooseDifficultyWindow chooseDifficultyWindow = new ChooseDifficultyWindow();
        Difficulty difficulty = Difficulty.STOPIT;
        while (!chooseDifficultyWindow.getCheck()){
            difficulty = chooseDifficultyWindow.getDifficulty();
            player.setDifficulty(difficulty);
        }

        Window window1 = new Window("Поле", 1024, 720, player.getVision(), player, map.getMap(player.x, player.y));
        window1.setCurrentMap(map);

        player.setWindow(window1);

        chooseDifficultyWindow.close();

        Narrator narrator = new Narrator(difficulty, window1);

        narrator.theBeginning(player);

        while (player.hp > 0){
            break;
        }
    }
}
