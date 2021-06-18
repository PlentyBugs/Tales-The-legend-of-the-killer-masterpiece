package window;

import window.player.UnfocusedButton;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {

    private final JButton returnToGame;

    public MainMenu(MultiWindow mainWindow) {
        super();
        List<JButton> buttons = new ArrayList<>();
        returnToGame = new UnfocusedButton("Вернуться в игру");
        returnToGame.addActionListener((e) -> mainWindow.switchScreen(Screen.GAME));
        buttons.add(returnToGame);
        returnToGame.setVisible(mainWindow.isGameCreated());
        JButton newGame = new UnfocusedButton("Новая игра");
        newGame.addActionListener((e) -> mainWindow.switchScreen(Screen.DIFFICULTY));
        buttons.add(newGame);
        JButton loadGame = new UnfocusedButton("Загрузить игру");
        loadGame.addActionListener((e) -> mainWindow.switchScreen(Screen.LOAD));
        buttons.add(loadGame);
        JButton exit = new UnfocusedButton("Выйти из игры");
        exit.addActionListener((e) -> System.exit(0));
        buttons.add(exit);
        printInterface("Tales of the Killer Masterpiece", buttons.toArray(new JButton[0]));
    }

    public void setReturnToGameVisible(boolean visible) {
        returnToGame.setVisible(visible);
    }
}
