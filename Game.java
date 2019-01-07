package JGame;

import java.io.IOException;
public class Game {
    public static void main(String[] args) throws InterruptedException, IOException {
        Player player = new Player(0,0, "Вы");

        Map map = new Map(player, 30, 30);

        Window window1 = new Window("Окно 1", 1024, 720, player.getVision(), player, map.getMap(player.x, player.y));
        window1.setCurrentMap(map);

        player.setWindow(window1);

        ChooseDifficultyWindow chooseDifficultyWindow = new ChooseDifficultyWindow();
        Difficulty difficulty = Difficulty.STOPIT;
        while (!chooseDifficultyWindow.getCheck()){
            difficulty = chooseDifficultyWindow.getDifficulty();
            player.setDifficulty(difficulty);
        }
        chooseDifficultyWindow.close();

        Narrator narrator = new Narrator(difficulty, window1);

        narrator.theBeginning(player);

        while (player.hp > 0){
            break;
        }
    }
}
