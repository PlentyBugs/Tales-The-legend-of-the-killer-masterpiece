package window;

import creature.Difficulty;

import javax.swing.*;

public interface MultiWindow extends Switcher {

    void newGame(Difficulty difficulty);

    void newWindow(JPanel window, Screen screen);

    void gameOver();

    boolean isGameCreated();

    void removeWindow(JPanel fightWindow);
}
