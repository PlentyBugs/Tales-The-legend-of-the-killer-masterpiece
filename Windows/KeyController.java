package Windows;

import Creatures.GodCreature;
import Creatures.Player;
import Locations.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface KeyController extends KeyListener, Controller {

    @Override
    default void keyPressed(KeyEvent event){
        Map map = getMap();
        Player player = map.getPlayer();
        GodCreature[][] lower = map.getMapLowerObjects();
        GodCreature[][] upper = map.getMapUpperObjects();
        int height = lower.length;
        int width = lower[0].length;
        int x = player.getX();
        int y = player.getY();
        if(event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A) {
            int newX = x == 0 ? width - 1: x - 1;
            step(
                    upper[y][newX] == null ?
                            lower[y][newX]:
                            upper[y][newX]
            );
            drawMap();
        } else if(event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D) {
            int newX = x == width - 1 ? 0: x + 1;
            step(
                    upper[y][newX] == null ?
                            lower[y][newX]:
                            upper[y][newX]
            );
            drawMap();
        } else if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W) {
            int newY = y == 0 ? height - 1: y - 1;
            step(
                    upper[newY][x] == null ?
                            lower[newY][x]:
                            upper[newY][x]
            );
            drawMap();
        } else if(event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_S) {
            int newY = y == height - 1 ? 0: y + 1;
            step(
                    upper[newY][x] == null ?
                            lower[newY][x]:
                            upper[newY][x]
            );
            drawMap();
        }
    }

    @Override
    default void keyReleased(KeyEvent event){}

    @Override
    default void keyTyped(KeyEvent event){}
}
