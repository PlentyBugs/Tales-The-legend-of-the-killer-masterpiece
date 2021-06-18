package window;

import creature.Difficulty;
import window.battle.FightWindow;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements MultiWindow, Creator {

    private final CardLayout manager = new CardLayout();
    private boolean gameCreated;
    private final MainMenu mainMenu;
    private Screen lastScreen;

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
        Menu lossMenu = new LossMenu(this);
        getContentPane().add(lossMenu, Screen.LOSS.name());

        pack();
        setVisible(true);
    }

    @Override
    public void switchScreen(Screen screen) {
        manager.show(getContentPane(), screen.name());
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    @Override
    public void newGame(Difficulty difficulty) {
        getContentPane().add(createGame(difficulty, this), Screen.GAME.name());
        gameCreated = true;
        mainMenu.setReturnToGameVisible(true);
    }

    @Override
    public void newFight(FightWindow fightWindow) {
        getContentPane().add(fightWindow, Screen.FIGHT.name());
    }

    @Override
    public void gameOver() {
        gameCreated = false;
        mainMenu.setReturnToGameVisible(false);
    }

    @Override
    public boolean isGameCreated() {
        return gameCreated;
    }

    @Override
    public void removeFightWindow(FightWindow fightWindow) {
        getContentPane().remove(fightWindow);
        getContentPane().revalidate();
        getContentPane().repaint();
    }
}
