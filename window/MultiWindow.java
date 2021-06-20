package window;

import creature.Difficulty;
import window.battle.ChooseEnemyWindow;
import window.battle.FightWindow;

import javax.swing.*;

public interface MultiWindow {
    void switchScreen(Screen screen);

    void newGame(Difficulty difficulty);

    void newFight(FightWindow fightWindow);

    void newChooseEnemy(ChooseEnemyWindow cmw);

    void gameOver();

    boolean isGameCreated();

    void removeWindow(JPanel fightWindow);

    void removeCMW(ChooseEnemyWindow chooseEnemyWindow);
}
