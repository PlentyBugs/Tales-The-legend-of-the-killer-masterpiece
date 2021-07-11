package window.menu;

import utils.KeyBinder;
import window.MultiWindow;
import window.Screen;
import window.player.UnfocusedButton;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {

    private final UnfocusedButton returnToGame;

    public MainMenu(MultiWindow mainWindow) {
        super();
        List<UnfocusedButton> buttons = new ArrayList<>();
        returnToGame = new UnfocusedButton("Вернуться в игру");
        returnToGame.addActionListener((e) -> mainWindow.switchScreen(Screen.GAME));
        buttons.add(returnToGame);
        returnToGame.setVisible(mainWindow.isGameCreated());
        UnfocusedButton newGame = new UnfocusedButton("Новая игра");
        newGame.addActionListener((e) -> mainWindow.switchScreen(Screen.DIFFICULTY));
        buttons.add(newGame);
        UnfocusedButton loadGame = new UnfocusedButton("Загрузить игру");
        loadGame.addActionListener((e) -> mainWindow.switchScreen(Screen.LOAD));
        buttons.add(loadGame);
        UnfocusedButton exit = new UnfocusedButton("Выйти из игры");
        exit.addActionListener((e) -> System.exit(0));
        buttons.add(exit);
        printInterface("Tales of the Killer Masterpiece", buttons.toArray(new UnfocusedButton[0]));
        KeyBinder.bindEscape(this, () -> mainWindow.switchScreen(Screen.GAME));
    }

    protected void close(Screen screen) {}

    public void setReturnToGameVisible(boolean visible) {
        returnToGame.setVisible(visible);
    }
}
