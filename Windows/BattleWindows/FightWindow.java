package Windows.BattleWindows;

import Abilities.Ability;
import Abilities.AbilityTarget;
import Abilities.AbilityType;
import Abilities.Active.AbilityActive;
import Abilities.Buffs.Buff;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Creatures.LiveCreature;
import Creatures.Player;
import Creatures.StatsEnum;
import Items.Armors.Ring;
import Items.Grade;
import Items.Item;
import Items.Material;
import Items.Rarity;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;
import Quests.CollectItemQuest;
import Quests.KillQuest;
import Quests.Quest;
import Things.ChestLike.Corpse;
import Windows.FieldWindow;
import Windows.SupportWindows.DialogWindow;
import Windows.SupportWindows.SupportComponents.Console;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class FightWindow extends JFrame implements Serializable {

    private Player player;
    private LiveCreature enemy;
    private FieldWindow field;

    private JProgressBar playerHp;
    private JProgressBar enemyHp;
    private boolean battleInProgress;

    private int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-20;
    private int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private int countMoves = 1;

    private Console enemyConsoleActions = new Console();
    private Console enemyConsoleStatus = new Console();
    private Console playerConsole = new Console();
    private DialogWindow dialogWindow = new DialogWindow("");

    private JPanel panel = new JPanel(new BorderLayout());
    private PlayerAbilityWindow playerAbilityWindow;
    private PlayerFightItemWindow playerFightItemWindow;

    public FightWindow(Player player, LiveCreature enemy, FieldWindow field) {
        super("Бой");
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        this.player = player;
        this.enemy = enemy;
        this.field = field;

        playerHp = new JProgressBar(0, player.getMaxHp());
        playerHp.setBackground(new Color(255,0,0));
        playerHp.setForeground(new Color(0,255,0));
        playerHp.setValue((int)player.getHp());
        playerHp.setStringPainted(true);
        playerHp.setString(Double.toString(player.getHp()) + "/" + Integer.toString(player.getMaxHp()));

        enemyHp = new JProgressBar(0, (int)enemy.getHp());
        enemyHp.setBackground(new Color(255,0,0));
        enemyHp.setForeground(new Color(0,255,0));
        enemyHp.setValue((int)player.getHp());
        enemyHp.setStringPainted(true);
        enemyHp.setString(Double.toString(enemy.getHp()));

        dialogWindow.setVisible(false);
        field.setIsVisible(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel(new BorderLayout());

        JPanel enemyPanel = new JPanel(new BorderLayout());

        enemyConsoleActions = new Console();
        enemyConsoleActions.setSpeed(0);
        enemyConsoleActions.setSizeArea(width/2,height/2-80);

        enemyConsoleStatus = new Console();
        enemyConsoleStatus.setSizeArea(width/2,height/2-80);
        enemyConsoleStatus.setSpeed(0);

        enemyPanel.add(enemyHp, BorderLayout.NORTH);
        enemyPanel.add(enemyConsoleActions, BorderLayout.WEST);
        enemyPanel.add(enemyConsoleStatus, BorderLayout.EAST);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        enemyPanel.setBorder(blackline);

        panel.add(enemyPanel, BorderLayout.LINE_START);


        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBorder(blackline);

        playerConsole = new Console();
        playerConsole.setSpeed(0);
        playerConsole.setSizeArea(width/2,height/2);

        JPanel playerActions = new JPanel();
        BoxLayout boxLayout = new BoxLayout(playerActions, BoxLayout.Y_AXIS);
        playerActions.setLayout(boxLayout);

        JButton playerAttackButton = new JButton("Атаковать");
        playerAttackButton.setBackground(new Color(0,255,0));
        Dimension d = new Dimension(width/2,height/10);
        playerAttackButton.setMinimumSize(d);
        playerAttackButton.setPreferredSize(d);
        playerAttackButton.setMaximumSize(d);

        JButton playerAbilityButton = new JButton("Способность");
        playerAbilityButton.setBackground(new Color(0,255,0));
        playerAbilityButton.setMinimumSize(d);
        playerAbilityButton.setPreferredSize(d);
        playerAbilityButton.setMaximumSize(d);

        JButton playerDefendButton = new JButton("Защищаться");
        playerDefendButton.setBackground(new Color(255,0,0));
        playerDefendButton.setMinimumSize(d);
        playerDefendButton.setPreferredSize(d);
        playerDefendButton.setMaximumSize(d);

        JButton playerUseItemButton = new JButton("Предметы");
        playerUseItemButton.setBackground(new Color(0,255,0));
        playerUseItemButton.setMinimumSize(d);
        playerUseItemButton.setPreferredSize(d);
        playerUseItemButton.setMaximumSize(d);

        JButton playerRunAwayButton = new JButton("Побег");
        playerRunAwayButton.setBackground(new Color(0,255,0));
        playerRunAwayButton.setMinimumSize(d);
        playerRunAwayButton.setPreferredSize(d);
        playerRunAwayButton.setMaximumSize(d);


        writeToEnemyActionConsole("Окно действий противника");
        writeToEnemyStatusConsole("Окно статуса противника");
        writeToPlayerConsole("Окно персонажа");
        if(player.getStats().getSpeed() >= enemy.getStats().getSpeed()){
            writeToPlayerConsole("Ваш ход");
        } else {
            writeToPlayerConsole("Ход противника");
            enemyTurn();
        }


        playerAttackButton.addActionListener(e -> {
            attack(player, enemy);
            enemyTurn();
        });

        playerAbilityButton.addActionListener(e -> {
            if (playerAbilityWindow == null){
                playerAbilityWindow = new PlayerAbilityWindow(player, enemy, FightWindow.this);
            } else {
                playerAbilityWindow.close();
                playerAbilityWindow = null;
            }
        });

        playerDefendButton.addActionListener(e -> enemyTurn());

        playerUseItemButton.addActionListener(e -> {
            if (playerFightItemWindow == null){
                playerFightItemWindow = new PlayerFightItemWindow(player, enemy, FightWindow.this);
            } else {
                playerFightItemWindow.close();
                playerFightItemWindow = null;
            }
        });

        playerRunAwayButton.addActionListener(e -> {
            int chance = (int)(Math.random()*(100-player.getStats().getLuck()/2));
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
        });


        playerActions.add(playerAttackButton, BorderLayout.LINE_START);
        playerActions.add(playerAbilityButton, BorderLayout.AFTER_LAST_LINE);
        playerActions.add(playerDefendButton, BorderLayout.LINE_START);
        playerActions.add(playerUseItemButton, BorderLayout.AFTER_LAST_LINE);
        playerActions.add(playerRunAwayButton, BorderLayout.LINE_START);

        playerPanel.add(playerConsole, BorderLayout.WEST);
        playerPanel.add(playerActions, BorderLayout.EAST);
        playerPanel.add(playerHp, BorderLayout.SOUTH);

        panel.add(playerPanel, BorderLayout.AFTER_LAST_LINE);
        getContentPane().add(panel);
        pack();
        setVisible(true);
        Thread printHp = new Thread(() -> printHp());
        printHp.start();
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

    void enemyTurn(){

        if(countMoves <= 0) {
            int chance = (int)(Math.ceil(Math.random()*100));
            ArrayList<Ability> abilities = enemy.getAbilitiesByType(AbilityType.ACTIVE);
            if(abilities.size() != 0 && chance < 30){
                attackBySpell(enemy, player, abilities.get((int)(abilities.size()*Math.random())));
            } else {
                attack(enemy, player);
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
        Thread thread = new Thread(() -> {
            if(player.getHp() <= 0){
                loss();
            }

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

                if(item.getClass().toString().contains("Ring")){
                    ((Ring)item).setStat(StatsEnum.values()[(int)(Math.random()*StatsEnum.values().length)]);
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

            player.addMoney(rewardMoney);
            player.addExp(rewardExp);

            field.setIsVisible(true);
            Corpse corpse = new Corpse(enemy.getX(), enemy.getY());
            for(Item item : dropItems){
                corpse.addItemToInventory(item);
            }
            field.getCurrentMap().setElementByCoordinates(enemy.getX(), enemy.getY(), corpse);
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
            if (playerFightItemWindow != null){
                playerFightItemWindow.close();
            }
            close();
        });
        thread.run();
    }

    private void loss(){
        if (playerAbilityWindow != null){
            playerAbilityWindow.close();
        }
        if(playerFightItemWindow != null){
            playerFightItemWindow.close();
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

    private void attack(LiveCreature attacker, LiveCreature enemy){
        int chanceToAvoid = (int)Math.ceil(Math.random()*100 - Math.pow(Math.E, -4.0*enemy.getLvl()/enemy.getStats().getLuck()));
        if (enemy.hasAbility(new Evasion()) && chanceToAvoid <= enemy.getAbility(new Evasion()).getChance()){
            if(attacker instanceof Player){
                writeToPlayerConsole(attacker.getName() + " промахнулся");
                writeToEnemyActionConsole(enemy.getName() + " увернулся");
            } else {
                writeToEnemyActionConsole(attacker.getName() + " промахнулся");
                writeToPlayerConsole(enemy.getName() + " увернулся");
            }
        } else {

            for(Weapon weapon : attacker.getEquipment().getWeaponList()){
                if(weapon != null){
                    weapon.setBonusDamage(1);
                    for(WeaponType weaponType : weapon.getWeaponType()){
                        switch (weaponType){
                            case ONEHANDED:{
                                if(attacker.getStats().getOne_handed_weapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getOne_handed_weapon()/150.0);
                                }
                            }
                            break;
                            case TWOHANDED:{
                                if(attacker.getStats().getTwo_handed_weapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getOne_handed_weapon()/150.0);
                                }
                            } break;
                            case LONGRANGE:{
                                if(attacker.getStats().getLong_range_weapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getOne_handed_weapon()/150.0);
                                }
                            } break;
                            case POLE:{
                                if(attacker.getStats().getPole_weapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getOne_handed_weapon()/150.0);
                                }
                            } break;
                            case CHOPPING:{
                                if(attacker.getStats().getChopping_weapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getOne_handed_weapon()/150.0);
                                }
                            } break;
                        }
                    }
                }
            }

            double damage = (int)((attacker.getStats().getStrength() + attacker.getEquipment().getWeaponDamage())*(Math.min(1, Math.max(0, (200 - (enemy.getStats().getStrength()-attacker.getStats().getStrength()))/200 + (200 - (enemy.getStats().getStrength()-attacker.getStats().getStrength()))/200 + (200 - (enemy.getStats().getStrength()-attacker.getStats().getStrength()))/200))/3 + 1));

            int chanceToCrit = (int)Math.ceil(Math.random()*100 - Math.pow(Math.E, -4.0*attacker.getLvl()/attacker.getStats().getLuck()));
            if(attacker.hasAbility(new CriticalStrike()) && chanceToCrit <= attacker.getAbility(new CriticalStrike()).getChance()){
                if(attacker instanceof Player)
                    writeToPlayerConsole("Критический удар(x"+ Double.toString(attacker.getAbility(new CriticalStrike()).getPower()/100.0) + ")!");
                else
                    writeToEnemyStatusConsole(  "Критический удар(x"+ Double.toString(attacker.getAbility(new CriticalStrike()).getPower()/100.0) + ")!");
                damage *= attacker.getAbility(new CriticalStrike()).getPower()/100.0;
            }

            attacker.setCurrentDamage(damage);

            if (attacker.getBuffs() != null){
                for (Buff buff : attacker.getBuffs()){
                    buff.use(attacker);
                }
            }
            damage = attacker.getCurrentDamage();

            damage = Math.round(damage*100.0)/100.0;

            enemy.setHp(Math.round((enemy.getHp()-damage)*100.0)/100.0);

            for(Weapon weapon : attacker.getEquipment().getWeaponList()){
                if(weapon != null){
                    weapon.weaponSkill(enemy, FightWindow.this);
                }
            }
            if (attacker instanceof Player){
                writeToPlayerConsole(attacker.getName() + " нанес " + Double.toString(damage) + " единиц урона");
                writeToEnemyStatusConsole(enemy.getName() + " получил " + Double.toString(damage) + " единиц урона");
            } else {
                writeToEnemyStatusConsole(attacker.getName() + " нанес " + Double.toString(damage) + " единиц урона");
                writeToPlayerConsole(enemy.getName() + " получил " + Double.toString(damage) + " единиц урона");
            }
        }
    }

    public void attackBySpell(LiveCreature attacker, LiveCreature enemy, Ability ability){
        if(attacker instanceof Player){
            writeToPlayerConsole(attacker.getName() + " использовал " + ability.getName());
        } else {
            writeToEnemyActionConsole(attacker.getName() + " использовал " + ability.getName());
        }
        if (ability.getAbilityType().contains(AbilityType.BUFF)){
            if(ability.getAbilityType().contains(AbilityType.ACTIVE) && !attacker.getEquipment().staffEquip()){
                ((AbilityActive)ability).chargeFee(attacker);
            }
            Buff buff = ((AbilityActive) ability).getBuff();
            buff.upgrade(attacker);
            if (((AbilityActive)ability).getAbilityTarget() == AbilityTarget.PLAYER){
                if(attacker instanceof Player){
                    writeToPlayerConsole("На " + attacker.getName() + " наложен эффект " + ((AbilityActive) ability).getBuff().getName());
                } else {
                    writeToEnemyStatusConsole("На " + attacker.getName() + " наложен эффект " + ((AbilityActive) ability).getBuff().getName());
                }
                attacker.addBuffs(buff);
            } else if (((AbilityActive)ability).getAbilityTarget() == AbilityTarget.ENEMY){
                if(attacker instanceof Player){
                    writeToPlayerConsole("На " + enemy.getName() + " наложен эффект " + ((AbilityActive) ability).getBuff().getName());
                } else {
                    writeToEnemyStatusConsole("На " + enemy.getName() + " наложен эффект " + ((AbilityActive) ability).getBuff().getName());
                }
                enemy.addBuffs(buff);
            }
        } else if (((AbilityActive)ability).getAbilityTarget() == AbilityTarget.ENEMY){
            ((AbilityActive)ability).use(enemy);
        }
        if(attacker instanceof Player){
            enemyTurn();
        }
    }

    public JProgressBar getPlayerHp() {
        return playerHp;
    }

    public JProgressBar getEnemyHp() {
        return enemyHp;
    }

    public Player getPlayer() {
        return player;
    }

    public LiveCreature getEnemy() {
        return enemy;
    }

    private void printHp(){
        while(true){
            if(player.getHp() < 0){
                loss();
                break;
            }
            if(enemy.getHp() <= 0){
                getReward();
                break;
            }
            playerHp.setValue((int)player.getHp());
            enemyHp.setValue((int)enemy.getHp());
            playerHp.setString(Double.toString(player.getHp()) + "/" + Integer.toString(player.getMaxHp()));
            enemyHp.setString(Double.toString(enemy.getHp()));
        }
    }
}
