package JGame;

import java.io.IOException;
public class Game {
    public static void main(String[] args) throws InterruptedException, IOException {
        Player player = new Player(0,0, "Никита");

        Map map = new Map(player.getVision());

        Window window1 = new Window("Окно 1", 1024, 720, player.getVision(), map.getMap());

        Narrator narrator = new Narrator("easy", window1);

        narrator.theBeginning(player);

        while (player.hp > 0){
            break;
        }
    }
}
