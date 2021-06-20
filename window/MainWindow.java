package window;

import creature.Difficulty;
import window.battle.ChooseEnemyWindow;
import window.battle.FightWindow;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements MultiWindow, Creator {

    private final CardLayout manager = new CardLayout();
    private boolean gameCreated;
    private final MainMenu mainMenu;
    private final ChooseEnemyMenu chooseEnemy;
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
        chooseEnemy = new ChooseEnemyMenu(this);
        getContentPane().add(chooseEnemy, Screen.ENEMY.name());

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
    public void newChooseEnemy(ChooseEnemyWindow cmw) {
        chooseEnemy.setCmw(cmw);
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
    public void removeWindow(JPanel window) {
        getContentPane().remove(window);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    @Override
    public void removeCMW(ChooseEnemyWindow chooseEnemyWindow) {
        chooseEnemy.remove(chooseEnemyWindow);
        chooseEnemy.setVisible(false);
    }
}
