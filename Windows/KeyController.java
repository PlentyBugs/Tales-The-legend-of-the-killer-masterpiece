package Windows;

import Creatures.GodCreature;
import Creatures.Player;
import Locations.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface KeyController extends KeyListener, Controller {

    @Override
    default void keyPressed(KeyEvent event) {
        Map map = getMap();
        Player player = map.getPlayer();
        GodCreature[][] lower = map.getMapLowerObjects();
        GodCreature[][] upper = map.getMapUpperObjects();
        int height = lower.length;
        int width = lower[0].length;
        int x = player.getX();
        int y = player.getY();
        int keyCode = event.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            int newX = x == 0 ? width - 1: x - 1;
            step(
                    upper == null || upper[y][newX] == null ?
                            lower[y][newX]:
                            upper[y][newX]
            );
            drawMap();
        } else if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            int newX = x == width - 1 ? 0: x + 1;
            step(
                    upper == null || upper[y][newX] == null ?
                            lower[y][newX]:
                            upper[y][newX]
            );
            drawMap();
        } else if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            int newY = y == 0 ? height - 1: y - 1;
            step(
                    upper == null || upper[newY][x] == null ?
                            lower[newY][x]:
                            upper[newY][x]
            );
            drawMap();
        } else if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            int newY = y == height - 1 ? 0: y + 1;
            step(
                    upper == null || upper[newY][x] == null ?
                            lower[newY][x]:
                            upper[newY][x]
            );
            drawMap();
        } else if(keyCode == KeyEvent.VK_ESCAPE) {
            getMultiWindow().switchScreen(Screen.MAIN_MENU);
        }
    }

    @Override
    default void keyReleased(KeyEvent event){}

    @Override
    default void keyTyped(KeyEvent event){}
}
