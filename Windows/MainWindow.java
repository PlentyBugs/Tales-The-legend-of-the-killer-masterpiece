package Windows;

import Creatures.Difficulty;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements MultiWindow, Creator {

    private final CardLayout manager = new CardLayout();
    private boolean gameCreated;
    private final MainMenu mainMenu;

    public MainWindow() {
        super("Tales of the Killer Masterpiece");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        getContentPane().setLayout(manager);

        mainMenu = new MainMenu(this);
        getContentPane().add(mainMenu, Screen.MAIN_MENU.name());
        Menu loadMenu = new LoadMenu(this);
        getContentPane().add(loadMenu, Screen.LOAD.name());
        Menu difficultyMenu = new DifficultyMenu(this);
        getContentPane().add(difficultyMenu, Screen.DIFFICULTY.name());

        pack();
        setVisible(true);
    }

    @Override
    public void switchScreen(Screen screen) {
        manager.show(getContentPane(), screen.name());
    }

    @Override
    public void newGame(Difficulty difficulty) {
        getContentPane().add(createGame(difficulty, this), Screen.GAME.name());
        gameCreated = true;
        mainMenu.setReturnToGameVisible(true);
    }

    @Override
    public boolean isGameCreated() {
        return gameCreated;
    }
}
