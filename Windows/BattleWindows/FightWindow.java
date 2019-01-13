package Windows.BattleWindows;

import Items.*;
import LiveCreatures.LiveCreature;
import LiveCreatures.Player;
import Things.Corpse;
import Windows.Console;
import Windows.DialogWindow;
import Windows.FieldWindow;
import Windows.LossWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
                    if(!isPlayerTurn){
                        enemyTurn();
                    }
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
                        isPlayerTurn = true;

                        field.setIsVisible(true);
                        field.drawMap();

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
            loss();
        }
    }

    private void getReward(){
        isPlayerTurn = true;

        field.setIsVisible(true);
        field.getCurrentMap().setElementByCoordinates(enemy.getX(), enemy.getY(), new Corpse(enemy.getX(), enemy.getY()));
        field.drawMap();

        close();
        int rewardMoney = (int)(((enemy.getLvl() - player.getLvl()+3)*70)*Math.random());
        if (rewardMoney <= 0){
            rewardMoney = (int)(Math.random()*170);
        }
        int rewardExp = (int)(((enemy.getLvl() - player.getLvl()+5)*20)*Math.random());
        if (rewardExp <= 0){
            rewardExp = (int)(Math.random()*60);
        }
        int countItemsDrop = (int)Math.ceil(Math.random()*enemy.getUniqueDropItems().length);
        ArrayList<Item> dropItems = new ArrayList<>();
        for (int i = 0; i < countItemsDrop; i++){
            Item item = enemy.getUniqueDropItems()[(int)(Math.random()*enemy.getUniqueDropItems().length-1)];
            int chanceDropItem = (int)Math.ceil(Math.random()*1000);

            if (chanceDropItem < 200 + enemy.getLvl()*15){
                item.setGrade(Grade.MAGIC);
            }
            if(chanceDropItem < 100 + enemy.getLvl()*10){
                item.setGrade(Grade.CURSE);
            }
            if(chanceDropItem < enemy.getLvl()*5){
                item.setGrade(Grade.ARTIFACT);
            }
            if(chanceDropItem < -50 + enemy.getLvl()*2){
                item.setGrade(Grade.HEROIC);
            }
            if(chanceDropItem < -60 + enemy.getLvl()){
                item.setGrade(Grade.ABOVETHEGODS);
            }

            chanceDropItem = (int)Math.ceil(Math.random()*1000);

            if (chanceDropItem < 200 + enemy.getLvl()*15){
                item.setRarity(Rarity.UNCOMMON);
            }
            if(chanceDropItem < 100 + enemy.getLvl()*10){
                item.setRarity(Rarity.RARE);
            }
            if(chanceDropItem < -20 + enemy.getLvl()*5){
                item.setRarity(Rarity.MYSTICAL);
            }
            if(chanceDropItem < -10 + enemy.getLvl()){
                item.setRarity(Rarity.LEGENDARY);
            }
            if(chanceDropItem < -90 + enemy.getLvl()){
                item.setRarity(Rarity.DRAGON);
            }
            if(chanceDropItem < -130 + enemy.getLvl()){
                item.setRarity(Rarity.DIVINE);
            }

            chanceDropItem = (int)Math.ceil(Math.random()*1000);

            if (item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1].equals("Sword")){
                if (chanceDropItem < 300 + enemy.getLvl()*12){
                    item.setMaterial(Material.IRON);
                }
                if (chanceDropItem < 120 + enemy.getLvl()*12){
                    item.setMaterial(Material.BRONZE);
                }
                if (chanceDropItem < -20 + enemy.getLvl()*15){
                    item.setMaterial(Material.STEEL);
                }
                if (chanceDropItem < -140 + enemy.getLvl()*7){
                    item.setMaterial(Material.MYTHRIL);
                }
                if (chanceDropItem < -50 + enemy.getLvl()*2){
                    item.setMaterial(Material.ADAMANTINE);
                }
                if (chanceDropItem < -100 + enemy.getLvl()*3){
                    item.setMaterial(Material.ELVENMYTHRIL);
                }
                if (chanceDropItem < -80 + enemy.getLvl()*2){
                    item.setMaterial(Material.CRYSTAL);
                }
                if (chanceDropItem < -100 + enemy.getLvl()*2){
                    item.setMaterial(Material.DEEP);
                }
                if (chanceDropItem < -70 + enemy.getLvl()){
                    item.setMaterial(Material.GODSHEART);
                }
                if (chanceDropItem < -500 + enemy.getLvl()*5){
                    item.setMaterial(Material.ABSOLUTEZERO);
                }
            } else if (item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1].equals("Torso") || item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1].equals("Helmet")){

                if (chanceDropItem < 300 + enemy.getLvl()*12){
                    item.setMaterial(Material.STUDDEDLEATHER);
                }
                if (chanceDropItem < 50 + enemy.getLvl()*12){
                    item.setMaterial(Material.CHAIN);
                }
                if (chanceDropItem < -100 + enemy.getLvl()*15){
                    item.setMaterial(Material.COPPER);
                }
                if (chanceDropItem < 50 + enemy.getLvl()*12){
                    item.setMaterial(Material.IRON);
                }
                if (chanceDropItem < -90 + enemy.getLvl()*9){
                    item.setMaterial(Material.BRONZE);
                }
                if (chanceDropItem < -160 + enemy.getLvl()*8){
                    item.setMaterial(Material.STEEL);
                }
                if (chanceDropItem < -210 + enemy.getLvl()*7){
                    item.setMaterial(Material.MYTHRIL);
                }
                if (chanceDropItem < -80 + enemy.getLvl()*2){
                    item.setMaterial(Material.ADAMANTINE);
                }
                if (chanceDropItem < -50 + enemy.getLvl()*3){
                    item.setMaterial(Material.ELVENMYTHRIL);
                }
                if (chanceDropItem < -130 + enemy.getLvl()*2){
                    item.setMaterial(Material.CRYSTAL);
                }
                if (chanceDropItem < -180 + enemy.getLvl()*2){
                    item.setMaterial(Material.DEEP);
                }
                if (chanceDropItem < -100 + enemy.getLvl()){
                    item.setMaterial(Material.GODSHEART);
                }
                if (chanceDropItem < -500 + enemy.getLvl()*4){
                    item.setMaterial(Material.ABSOLUTEZERO);
                }
            }

            item.countProperty();

            dropItems.add(enemy.getUniqueDropItems()[(int)(Math.random()*enemy.getUniqueDropItems().length-1)]);
        }

        dialogWindow.close();
        dialogWindow = new DialogWindow(
                "Поздравляю, вы победили!\n" +
                        "Награда:\n" +
                        "Деньги: " + Integer.toString(rewardMoney) + "\n" +
                        "Опыт: " + Integer.toString(rewardExp));

        String rewardItemMessage = "";

        for (Item item : dropItems){
            player.addItemToInventory(item);
            rewardItemMessage += item.getName() + "\n";
        }

        dialogWindow = new DialogWindow(
                "Выпавшие вещи\n" +
                        rewardItemMessage);
        player.addMoney(rewardMoney);
        player.addExp(rewardExp);
    }

    public void loss(){
        close();
        LossWindow loss = new LossWindow();
    }
}
