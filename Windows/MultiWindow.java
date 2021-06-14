package Windows;

import Creatures.Difficulty;

public interface MultiWindow {
    void switchScreen(Screen screen);

    void newGame(Difficulty difficulty);

    boolean isGameCreated();
}
