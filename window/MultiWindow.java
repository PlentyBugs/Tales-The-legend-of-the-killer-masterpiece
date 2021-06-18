package window;

import creature.Difficulty;
import window.battle.FightWindow;

public interface MultiWindow {
    void switchScreen(Screen screen);

    void newGame(Difficulty difficulty);

    void newFight(FightWindow fightWindow);

    void gameOver();

    boolean isGameCreated();

    void removeFightWindow(FightWindow fightWindow);
}
