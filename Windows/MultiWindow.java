package Windows;

import Creatures.Difficulty;
import Windows.BattleWindows.FightWindow;

public interface MultiWindow {
    void switchScreen(Screen screen);

    void newGame(Difficulty difficulty);

    void newFight(FightWindow fightWindow);

    boolean isGameCreated();

    void removeFightWindow(FightWindow fightWindow);
}
