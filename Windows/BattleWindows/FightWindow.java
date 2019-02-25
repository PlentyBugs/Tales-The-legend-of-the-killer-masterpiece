package Windows.BattleWindows;

import Abilities.Buffs.Buff;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Items.*;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;
import Creatures.LiveCreature;
import Creatures.Player;
import Quests.CollectItemQuest;
import Quests.KillQuest;
import Quests.Quest;
import Things.Corpse;
import Windows.FieldWindow;
import Windows.SupportWindows.SupportComponents.Console;
import Windows.SupportWindows.DialogWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class FightWindow extends JFrame implements Serializable {

    private Player player;
    private LiveCreature enemy;
    private FieldWindow field;

    private int width = 720;
    private int height = 480;
    private int countMoves = 1;

    private Console enemyConsoleActions = new Console();
    private Console enemyConsoleStatus = new Console();
    private Console playerConsole = new Console();
    private DialogWindow dialogWindow = new DialogWindow("");

    private JPanel panel = new JPanel(new BorderLayout());
    private PlayerAbilityWindow playerAbilityWindow;

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
        } else {
            writeToPlayerConsole("Ход противника");
            enemyTurn();
        }


        playerAttackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double damage = (int)((player.getStats().strength + player.getEquipment().getWeaponDamage())*(Math.min(1, Math.max(0, (200 - (enemy.getStats().strength-player.getStats().strength))/200 + (200 - (enemy.getStats().strength-player.getStats().strength))/200 + (200 - (enemy.getStats().strength-player.getStats().strength))/200))/3 + 1));

                for(Weapon weapon : player.getEquipment().getWeaponList()){
                    if(weapon != null){
                        for(WeaponType weaponType : weapon.getWeaponType()){
                            switch (weaponType){
                                case ONEHANDED:{
                                    if(player.getStats().one_handed_weapon != 0) {
                                        damage *= 1 + player.getStats().one_handed_weapon/150.0;
                                    }
                                }
                                    break;
                                case TWOHANDED:{
                                    if(player.getStats().two_handed_weapon != 0) {
                                        damage *= 1 + player.getStats().two_handed_weapon/150.0;
                                    }
                                } break;
                                case LONGRANGE:{
                                    if(player.getStats().long_range_weapon != 0) {
                                        damage *= 1 + player.getStats().long_range_weapon/150.0;
                                    }
                                } break;
                                case POLE:{
                                    if(player.getStats().pole_weapon != 0) {
                                        damage *= 1 + player.getStats().pole_weapon/150.0;
                                    }
                                } break;
                                case CHOPPING:{
                                    if(player.getStats().chopping_weapon != 0) {
                                        damage *= 1 + player.getStats().chopping_weapon/150.0;
                                    }
                                } break;
                            }
                        }
                    }
                }

                int chance = (int)Math.ceil(Math.random()*100 - player.getStats().luck/5);
                if(player.hasAbility(new CriticalStrike()) && chance <= player.getAbility(new CriticalStrike()).getChance()){
                    writeToPlayerConsole("Критический удар(x"+ Double.toString(player.getAbility(new CriticalStrike()).getPower()/100.0) + ")!");
                    writeToEnemyStatusConsole(  "Критический удар(x"+ Double.toString(player.getAbility(new CriticalStrike()).getPower()/100.0) + ")!");
                    damage *= player.getAbility(new CriticalStrike()).getPower()/100.0;
                }

                player.setCurrentDamage(damage);

                if (player.getBuffs() != null){
                    for (Buff buff : player.getBuffs()){
                        buff.use(player);
                    }
                }
                damage = player.getCurrentDamage();

                damage = Math.round(damage*100.0)/100.0;
                enemy.setHp(Math.round((enemy.getHp()-damage)*100.0)/100.0);
                writeToEnemyStatusConsole(enemy.getName() + " получил " + Double.toString(damage) + " единиц урона");
                writeToPlayerConsole("Вы нанесли " + Double.toString(damage) + " единиц урона");
                if (enemy.getHp() > 0){
                    writeToEnemyStatusConsole("Осталось жизней: " + enemy.getHp());
                } else {
                    writeToEnemyStatusConsole(enemy.getName() + " повержен!");
                }
                enemyTurn();
            }
        });

        playerAbilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playerAbilityWindow == null){
                    playerAbilityWindow = new PlayerAbilityWindow(player, enemy, FightWindow.this);
                } else {
                    playerAbilityWindow.close();
                    playerAbilityWindow = null;
                }
            }
        });

        playerDefendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enemyTurn();
            }
        });

        playerUseItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enemyTurn();
            }
        });

        playerRunAwayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int chance = (int)(Math.random()*(100-player.getStats().luck/2));
                if (chance < 100*player.getLvl()/(enemy.getLvl()+1)){
                    dialogWindow.close();
                    dialogWindow = new DialogWindow("Вам удалось сбежать");

                    field.setIsVisible(true);
                    field.drawMap();

                    close();
                } else {
                    dialogWindow.close();
                    dialogWindow = new DialogWindow("Вам не удалось сбежать");
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

    public void writeToEnemyStatusConsole(String text){
        enemyConsoleStatus.writeToConsole(text);
    }

    public void writeToEnemyActionConsole(String text){
        enemyConsoleActions.writeToConsole(text);
    }

    public void writeToPlayerConsole(String text){
        playerConsole.writeToConsole(text);
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

        if (enemy.getHp() <= 0){
            getReward();
        }
        if(countMoves <= 0){

            int chance = (int)Math.ceil(Math.random()*100 - player.getStats().luck/5);
            if (player.hasAbility(new Evasion()) && chance <= player.getAbility(new Evasion()).getChance()){
                writeToEnemyActionConsole(enemy.getName() + " промахнулся");
                writeToPlayerConsole("Вы увернулись");
            } else {
                double damage = enemy.getStats().strength*Math.min(1, Math.max(0, (200 - (player.getStats().strength-enemy.getStats().strength))/200 + (200 - (player.getStats().strength-enemy.getStats().strength))/200 + (200 - (player.getStats().strength-enemy.getStats().strength))/200));
                damage = Math.round((player.absorbDamage(damage))*100.0)/100.0;

                enemy.setCurrentDamage(damage);

                if (enemy.getBuffs() != null){
                    for (Buff buff : enemy.getBuffs()){
                        buff.use(enemy);
                    }
                }

                damage = enemy.getCurrentDamage();

                player.setHp(Math.round((player.getHp()-damage)*100.0)/100.0);
                writeToPlayerConsole(player.getName() + " получил " + Double.toString(damage) + " единиц урона");
                writeToEnemyActionConsole(enemy.getName() + " нанес " + Double.toString(damage) + " единиц урона");
                if (player.getHp() > 0){
                    writeToPlayerConsole("Осталось жизней: " + player.getHp());
                } else {
                    writeToPlayerConsole(player.getName() + " повержен!");
                    loss();
                }
            }
            for(Weapon weapon : player.getEquipment().getWeaponList()){
                if (weapon != null){
                    if(weapon.getWeaponType().contains(WeaponType.LONGRANGE)){
                        countMoves = 2;
                    } else {
                        countMoves = 1;
                    }
                }
            }
        }
        countMoves --;

        if (enemy.getBuffs() != null){
            for (Buff buff : enemy.getBuffs()){
                buff.setStepCount(buff.getStepCount() - 1);
                if (buff.getStepCount() <= 0){
                    enemy.removeBuff(buff);
                    continue;
                }
                writeToEnemyStatusConsole("Осталось ходов(" + buff.getName() + "): " + buff.getStepCount());
            }
        }
        if (player.getBuffs() != null){
            for (Buff buff : player.getBuffs()){
                buff.setStepCount(buff.getStepCount() - 1);
                if (buff.getStepCount() <= 0){
                    player.removeBuff(buff);
                    continue;
                }
                writeToPlayerConsole("Осталось ходов(" + buff.getName() + "): " + buff.getStepCount());
            }
        }

    }

    private void getReward(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                field.setIsVisible(true);
                field.getCurrentMap().setElementByCoordinates(enemy.getX(), enemy.getY(), new Corpse(enemy.getX(), enemy.getY()));
                field.drawMap();

                if (player.getQuests() != null){
                    for (Quest quest : player.getQuests()){
                        if(quest.getClass().toString().contains("Kill") && enemy.getClass().toString().contains(((KillQuest)quest).getEnemyToKill().getClass().toString())){
                            ((KillQuest)quest).setEnemyCountToKillCurrent(((KillQuest)quest).getEnemyCountToKillCurrent()+1);
                        }
                        if(quest.getClass().toString().contains("Collect")){
                            for (Item item : enemy.getUniqueDropItems()){
                                if (item.getClass().toString().equals(((CollectItemQuest)quest).getItem().getClass().toString())){
                                    player.addItemToInventory(((CollectItemQuest)quest).getItem());
                                    break;
                                }
                            }
                        }
                        if(quest.check()){
                            quest.getReward(player);
                            player.removeQuest(quest);
                        }
                    }
                }


                if (playerAbilityWindow != null){
                    playerAbilityWindow.close();
                }
                close();
                int rewardMoney = (int)(((enemy.getLvl() - player.getLvl()+3)*70)*Math.random() + 7*player.getLvl()*enemy.getLvl());
                if (rewardMoney <= 0){
                    rewardMoney = (int)(Math.random()*170);
                }
                int rewardExp = (int)(((enemy.getLvl() - player.getLvl()+5)*20)*Math.random() + 4*player.getLvl()*enemy.getLvl() + 10*enemy.getLvl() + (int)Math.pow(enemy.getLvl(), 2.5));
                if (rewardExp <= 0){
                    rewardExp = (int)(Math.random()*60);
                }
                int countItemsDrop = (int)Math.ceil(Math.random()*(enemy.getUniqueDropItems().length + 1) - 1);
                ArrayList<Item> dropItems = new ArrayList<>();
                for (int i = 0; i < countItemsDrop; i++){
                    Item item = null;
                    try {
                        item = (Item)enemy.getUniqueDropItems()[(int)(Math.random()*enemy.getUniqueDropItems().length-1)].clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }

                    int chanceDropItem = (int)Math.ceil(Math.random()*1000);

                    if (chanceDropItem < enemy.getLvl()*20){
                        item.setGrade(Grade.MAGIC);
                    }
                    if(chanceDropItem < -90 + enemy.getLvl()*15){
                        item.setGrade(Grade.CURSE);
                    }
                    if(chanceDropItem < -60 + enemy.getLvl()*5){
                        item.setGrade(Grade.ARTIFACT);
                    }
                    if(chanceDropItem < -60 + enemy.getLvl()*3){
                        item.setGrade(Grade.HEROIC);
                    }
                    if(chanceDropItem < -60 + enemy.getLvl()){
                        item.setGrade(Grade.ABOVETHEGODS);
                    }

                    chanceDropItem = (int)Math.ceil(Math.random()*1000);

                    if (chanceDropItem < enemy.getLvl()*20){
                        item.setRarity(Rarity.UNCOMMON);
                    }
                    if(chanceDropItem < -50 + enemy.getLvl()*10){
                        item.setRarity(Rarity.RARE);
                    }
                    if(chanceDropItem < -40 + enemy.getLvl()*5){
                        item.setRarity(Rarity.MYSTICAL);
                    }
                    if(chanceDropItem < -300 + enemy.getLvl()*10){
                        item.setRarity(Rarity.LEGENDARY);
                    }
                    if(chanceDropItem < -1000 + enemy.getLvl()*20){
                        item.setRarity(Rarity.DRAGON);
                    }
                    if(chanceDropItem < -210 + enemy.getLvl()*3){
                        item.setRarity(Rarity.DIVINE);
                    }

                    chanceDropItem = (int)Math.ceil(Math.random()*1000);

                    if (item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1].equals("Sword")){
                        if (chanceDropItem < enemy.getLvl()*12){
                            item.setMaterial(Material.IRON);
                        }
                        if (chanceDropItem < -50 + enemy.getLvl()*10){
                            item.setMaterial(Material.BRONZE);
                        }
                        if (chanceDropItem < -132 + enemy.getLvl()*12){
                            item.setMaterial(Material.STEEL);
                        }
                        if (chanceDropItem < -147 + enemy.getLvl()*7){
                            item.setMaterial(Material.MYTHRIL);
                        }
                        if (chanceDropItem < -183 + enemy.getLvl()*6){
                            item.setMaterial(Material.ADAMANTINE);
                        }
                        if (chanceDropItem < -126 + enemy.getLvl()*3){
                            item.setMaterial(Material.ELVENMYTHRIL);
                        }
                        if (chanceDropItem < -96 + enemy.getLvl()*2){
                            item.setMaterial(Material.CRYSTAL);
                        }
                        if (chanceDropItem < -112 + enemy.getLvl()*2){
                            item.setMaterial(Material.DEEP);
                        }
                        if (chanceDropItem < -243 + enemy.getLvl()*3){
                            item.setMaterial(Material.GODSHEART);
                        }
                        if (chanceDropItem < -500 + enemy.getLvl()*5){
                            item.setMaterial(Material.ABSOLUTEZERO);
                        }
                    } else if (item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1].equals("Torso") || item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1].equals("Helmet")){

                        if (chanceDropItem < enemy.getLvl()*12){
                            item.setMaterial(Material.STUDDEDLEATHER);
                        }
                        if (chanceDropItem < -72 + enemy.getLvl()*12){
                            item.setMaterial(Material.CHAIN);
                        }
                        if (chanceDropItem < -175 + enemy.getLvl()*15){
                            item.setMaterial(Material.COPPER);
                        }
                        if (chanceDropItem < -196 + enemy.getLvl()*12){
                            item.setMaterial(Material.IRON);
                        }
                        if (chanceDropItem < -279 + enemy.getLvl()*9){
                            item.setMaterial(Material.BRONZE);
                        }
                        if (chanceDropItem < -320 + enemy.getLvl()*8){
                            item.setMaterial(Material.STEEL);
                        }
                        if (chanceDropItem < -320 + enemy.getLvl()*7){
                            item.setMaterial(Material.MYTHRIL);
                        }
                        if (chanceDropItem < -212 + enemy.getLvl()*4){
                            item.setMaterial(Material.ADAMANTINE);
                        }
                        if (chanceDropItem < -170 + enemy.getLvl()*3){
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

                    dropItems.add(item);
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
                    if(player.getIsInventoryOpen()){
                        player.getInventoryWindow().drawInventory();
                    }
                    rewardItemMessage += item.getName() + "\n";
                }
                if(!rewardItemMessage.equals("")){
                    dialogWindow = new DialogWindow(
                            "Выпавшие вещи\n" +
                                    rewardItemMessage);
                }
                player.addMoney(rewardMoney);
                player.addExp(rewardExp);
            }
        });
        thread.run();
    }

    public void loss(){
        if (playerAbilityWindow != null){
            playerAbilityWindow.close();
        }
        close();
        LossWindow loss = new LossWindow();
    }

    public int getCountMoves() {
        return countMoves;
    }

    public void setCountMoves(int countMoves) {
        this.countMoves = countMoves;
    }
}
