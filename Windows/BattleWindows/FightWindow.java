package JGame.Windows.BattleWindows;

import JGame.LiveCreatures.LiveCreature;
import JGame.LiveCreatures.Player;
import JGame.Windows.Console;
import JGame.Windows.DialogWindow;
import JGame.Windows.FieldWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class FightWindow extends JFrame {

    private Player player;
    private LiveCreature enemy;
    private FieldWindow field;

    private boolean isPlayerTurn = true;

    private int width = 720;
    private int height = 480;

    private Console enemyConsoleActions = new Console();
    private Console enemyConsoleStatus = new Console();
    private Console playerConsole = new Console();
    private DialogWindow dialogWindow = new DialogWindow("");

    private JPanel panel = new JPanel(new BorderLayout());

    public FightWindow(Player player, LiveCreature enemy, FieldWindow field) {
        super("Бой");
        setResizable(false);

        this.player = player;
        this.enemy = enemy;
        this.field = field;

        dialogWindow.setVisible(false);
        field.setIsVisible(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel(new BorderLayout());

        JPanel enemyPanel = new JPanel(new BorderLayout());

        enemyConsoleActions = new Console();
        enemyConsoleActions.setSpeed(0);
        enemyConsoleActions.setRowsAndColumns(13,50);

        enemyConsoleStatus = new Console();
        Dimension d1 = new Dimension(340,48);
        enemyConsoleStatus.setMinimumSize(d1);
        enemyConsoleStatus.setPreferredSize(d1);
        enemyConsoleStatus.setMaximumSize(d1);
        enemyConsoleStatus.setSpeed(0);
        enemyConsoleStatus.setRowsAndColumns(13,46);

        enemyPanel.add(enemyConsoleActions, BorderLayout.WEST);
        enemyPanel.add(enemyConsoleStatus, BorderLayout.EAST);

        enemyPanel.setPreferredSize(new Dimension(width,height/2));

        Border blackline = BorderFactory.createLineBorder(Color.black);
        enemyPanel.setBorder(blackline);

        panel.add(enemyPanel, BorderLayout.LINE_START);


        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBorder(blackline);

        playerConsole = new Console();
        playerConsole.setSpeed(0);
        playerConsole.setRowsAndColumns(13,50);

        JPanel playerActions = new JPanel();
        BoxLayout boxLayout = new BoxLayout(playerActions, BoxLayout.Y_AXIS);
        playerActions.setLayout(boxLayout);

        JButton playerAttackButton = new JButton("Атаковать");
        Dimension d = new Dimension(340,48);
        playerAttackButton.setMinimumSize(d);
        playerAttackButton.setPreferredSize(d);
        playerAttackButton.setMaximumSize(d);

        JButton playerAbilityButton = new JButton("Способность");
        playerAbilityButton.setMinimumSize(d);
        playerAbilityButton.setPreferredSize(d);
        playerAbilityButton.setMaximumSize(d);

        JButton playerDefendButton = new JButton("Защищаться");
        playerDefendButton.setMinimumSize(d);
        playerDefendButton.setPreferredSize(d);
        playerDefendButton.setMaximumSize(d);

        JButton playerUseItemButton = new JButton("Предметы");
        playerUseItemButton.setMinimumSize(d);
        playerUseItemButton.setPreferredSize(d);
        playerUseItemButton.setMaximumSize(d);

        JButton playerRunAwayButton = new JButton("Побег");
        playerRunAwayButton.setMinimumSize(d);
        playerRunAwayButton.setPreferredSize(d);
        playerRunAwayButton.setMaximumSize(d);


        writeToEnemyActionConsole("Окно действий противника");
        writeToEnemyStatusConsole("Окно статуса противника");
        writeToPlayerConsole("Окно персонажа");
        if(player.getStats().speed >= enemy.getStats().speed){
            writeToPlayerConsole("Ваш ход");
            isPlayerTurn = true;
        } else {
            writeToPlayerConsole("Ход противника");
            isPlayerTurn = false;
            enemyTurn();
        }


        playerAttackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isPlayerTurn){
                    isPlayerTurn = false;
                    int damage = (int)((player.getStats().strength + player.getEquipment().getWeaponDamage())*(200 - (enemy.getStats().speed - player.getStats().speed) - (enemy.getStats().speed - player.getStats().speed) - (enemy.getStats().speed - player.getStats().speed))/200);
                    enemy.setHp(enemy.getHp()-damage);
                    writeToEnemyStatusConsole(enemy.getName() + " получил " + Integer.toString(damage) + " единиц урона");
                    writeToPlayerConsole("Вы нанесли " + Integer.toString(damage) + " единиц урона");
                    if (enemy.getHp() > 0){
                        writeToEnemyStatusConsole("Осталось жизней: " + enemy.getHp());
                    } else {
                        writeToEnemyStatusConsole(enemy.getName() + " повержен!");
                        getReward();
                    }
                    enemyTurn();
                }
            }
        });

        playerAbilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isPlayerTurn){
                    isPlayerTurn = false;
                    // Coming soon because there aren't Abilities
                    enemyTurn();
                }
            }
        });

        playerDefendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isPlayerTurn){
                    isPlayerTurn = false;
                    enemyTurn();
                }
            }
        });

        playerUseItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isPlayerTurn){
                    isPlayerTurn = false;

                    enemyTurn();
                }
            }
        });

        playerRunAwayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isPlayerTurn){
                    isPlayerTurn = false;
                    int chance = (int)(Math.random()*100);
                    if (chance < 100*player.getLvl()/(enemy.getLvl()+1)){
                        dialogWindow.close();
                        dialogWindow = new DialogWindow("Вам удалось сбежать");
                        close();
                    } else {
                        dialogWindow.close();
                        dialogWindow = new DialogWindow("Вам не удалось сбежать");
                        enemyTurn();
                    }
                } else {
                    enemyTurn();
                }
            }
        });


        playerActions.add(playerAttackButton, BorderLayout.LINE_START);
        playerActions.add(playerAbilityButton, BorderLayout.AFTER_LAST_LINE);
        playerActions.add(playerDefendButton, BorderLayout.LINE_START);
        playerActions.add(playerUseItemButton, BorderLayout.AFTER_LAST_LINE);
        playerActions.add(playerRunAwayButton, BorderLayout.LINE_START);

        playerPanel.add(playerConsole, BorderLayout.WEST);
        playerPanel.add(playerActions, BorderLayout.EAST);

        panel.add(playerPanel, BorderLayout.AFTER_LAST_LINE);
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    private void writeToEnemyStatusConsole(String text){
        try{
            enemyConsoleStatus.writeToConsole(text);
        } catch (InterruptedException ex){

        }
    }

    private void writeToEnemyActionConsole(String text){
        try{
            enemyConsoleActions.writeToConsole(text);
        } catch (InterruptedException ex){

        }
    }

    private void writeToPlayerConsole(String text){
        try{
            playerConsole.writeToConsole(text);
        } catch (InterruptedException ex){

        }
    }

    private void clearStatusConsole(){
        enemyConsoleStatus.clear();
    }

    private void clearActionConsole(){
        enemyConsoleActions.clear();
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void enemyTurn(){
        isPlayerTurn = true;
        int damage = (int)(enemy.getStats().strength*(200 - (player.getStats().speed - enemy.getStats().speed) - (player.getStats().speed - enemy.getStats().speed) - (player.getStats().speed - enemy.getStats().speed))/200);
        player.setHp(player.getHp()-damage);
        writeToPlayerConsole(player.getName() + " получил " + Integer.toString(damage) + " единиц урона");
        writeToEnemyActionConsole(enemy.getName() + " нанес " + Integer.toString(damage) + " единиц урона");
        if (player.getHp() > 0){
            writeToPlayerConsole("Осталось жизней: " + player.getHp());
        } else {
            writeToPlayerConsole(player.getName() + " повержен!");
            getReward();
        }
    }

    private void getReward(){

        field.setIsVisible(true);

        close();
        int rewardMoney = (int)(((enemy.getLvl() - player.getLvl()+3)*70)*Math.random());
        if (rewardMoney <= 0){
            rewardMoney = (int)(Math.random()*170);
        }
        int rewardExp = (int)(((enemy.getLvl() - player.getLvl()+5)*20)*Math.random());
        if (rewardExp <= 0){
            rewardExp = (int)(Math.random()*60);
        }

        dialogWindow.close();
        dialogWindow = new DialogWindow(
                "Поздравляю, вы победили!\n" +
                        "Награда:\n" +
                        "Деньги: " + Integer.toString(rewardMoney) + "\n" +
                        "Опыт: " + Integer.toString(rewardExp));
        player.addMoney(rewardMoney);
        player.addExp(rewardExp);
    }
}
