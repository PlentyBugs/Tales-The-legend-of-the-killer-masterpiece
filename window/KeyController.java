package window;

import creature.GodCreature;
import creature.Player;
import location.Map;
import utils.KeyBinder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public interface KeyController extends Controller {

    default void bindKeys() {
        KeyBinder.bindEscape(this, () -> getMultiWindow().switchScreen(Screen.MAIN_MENU));
        KeyStroke left = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        KeyStroke leftA = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(left,   "left");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftA,   "left");
        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control((x, y, width, height, lower, upper) -> {
                    int newX = x == 0 ? width - 1: x - 1;
                    return upper == null || upper[y][newX] == null ?
                            lower[y][newX]:
                            upper[y][newX];
                });
            }
        });
        KeyStroke right = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        KeyStroke rightD = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(right,   "right");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightD,   "right");
        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control((x, y, width, height, lower, upper) -> {
                    int newX = x == width - 1 ? 0: x + 1;
                    return upper == null || upper[y][newX] == null ?
                            lower[y][newX]:
                            upper[y][newX];
                });
            }
        });
        KeyStroke up = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke upW = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(up,   "up");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upW,   "up");
        getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control((x, y, width, height, lower, upper) -> {
                    int newY = y == 0 ? height - 1: y - 1;
                    return upper == null || upper[newY][x] == null ?
                            lower[newY][x]:
                            upper[newY][x];
                });
            }
        });
        KeyStroke down = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        KeyStroke downS = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(down,   "down");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downS,   "down");
        getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control((x, y, width, height, lower, upper) -> {
                    int newY = y == height - 1 ? 0: y + 1;
                    return upper == null || upper[newY][x] == null ?
                            lower[newY][x]:
                            upper[newY][x];
                });
            }
        });
    }

    default void control(ControlItem controlItem) {
        Map map = getMap();
        Player player = map.getPlayer();
        GodCreature[][] lower = map.getMapLowerObjects();
        GodCreature[][] upper = map.getMapUpperObjects();
        int height = lower.length;
        int width = lower[0].length;
        int x = player.getX();
        int y = player.getY();
        step(controlItem.control(x, y, width, height, lower, upper));
        drawMap();
    }
}
