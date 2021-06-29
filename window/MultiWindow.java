package window;

import creature.Difficulty;
import window.battle.ChooseEnemyWindow;
import window.battle.FightWindow;

import javax.swing.*;

public interface MultiWindow {
    void switchScreen(Screen screen);

    void newGame(Difficulty difficulty);

    void newWindow(JPanel window, Screen screen);

    void gameOver();

    boolean isGameCreated();

    void removeWindow(JPanel fightWindow);
}
