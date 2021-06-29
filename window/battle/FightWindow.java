package window.battle;

import abilities.Ability;
import abilities.AbilityTarget;
import abilities.AbilityType;
import abilities.active.ActiveAbility;
import abilities.buffs.Buff;
import abilities.enchants.Enchant;
import abilities.enchants.EnchantType;
import abilities.enchants.armor.HigherPath;
import abilities.enchants.armor.SpikeArmor;
import abilities.enchants.weapon.KornelCurse;
import abilities.enchants.weapon.Vampirism;
import creature.LiveCreature;
import creature.Player;
import creature.StatsEnum;
import creature.aggressive.boss.Boss;
import item.Grade;
import item.Item;
import item.Material;
import item.Rarity;
import item.alchemy.ingredient.Ingredient;
import item.armor.Armor;
import item.armor.Helmet;
import item.armor.Ring;
import item.armor.Torso;
import item.weapon.Weapon;
import item.weapon.WeaponType;
import quest.CollectItemQuest;
import quest.KillQuest;
import quest.Quest;
import support.AbilityProperty;
import thing.chest.Chest;
import thing.chest.Corpse;
import window.MultiWindow;
import window.Screen;
import window.WindowInterface;
import window.player.UnfocusedButton;
import window.support.component.Console;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class FightWindow extends JPanel implements Serializable {

    private final Player player;
    private LiveCreature enemy;
    private final WindowInterface field;

    private final JProgressBar playerHp;
    private final JProgressBar enemyHp;
    private final JProgressBar loyalty;
    private boolean battleInProgress;

    private int countMoves = 1;

    private final Console enemyConsoleActions;
    private final Console enemyConsoleStatus;
    private final Console playerConsole;

    private PlayerAbilityWindow playerAbilityWindow;
    private PlayerFightItemWindow playerFightItemWindow;
    private PlayerDiplomacyWindow playerDiplomacyWindow;

    private final MultiWindow multiWindow;

    public FightWindow(Player player, LiveCreature enemy, WindowInterface field, MultiWindow multiWindow) {
        this.multiWindow = multiWindow;

        this.player = player;
        this.enemy = enemy;
        this.field = field;
        player.setInFight(true);

        enemy.addCreatureToLoyalty(player);
        player.addCreatureToLoyalty(enemy);

        playerHp = new JProgressBar(0, player.getMaxHp());
        playerHp.setBackground(new Color(255,0,0));
        playerHp.setForeground(new Color(0,255,0));
        playerHp.setValue((int)player.getHp());
        playerHp.setStringPainted(true);
        playerHp.setString(player.getHp() + "/" + player.getMaxHp());


        loyalty = new JProgressBar(-100, 100);
        loyalty.setBackground(new Color(255,0,0));
        loyalty.setForeground(new Color(0,255,0));
        loyalty.setValue((int)player.getHp());
        loyalty.setStringPainted(true);
        loyalty.setString("Отношения");
        loyalty.setValue(enemy.getLoyaltyByIndex(player));

        enemyHp = new JProgressBar(0, (int)enemy.getHp());
        enemyHp.setBackground(new Color(255,0,0));
        enemyHp.setForeground(new Color(0,255,0));
        enemyHp.setValue((int)player.getHp());
        enemyHp.setStringPainted(true);
        enemyHp.setString(Double.toString(enemy.getHp()));

        JPanel panel = new JPanel(new BorderLayout());

        JPanel enemyPanel = new JPanel(new BorderLayout());

        enemyConsoleActions = new Console();
        enemyConsoleActions.setSpeed(0);
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20;
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        enemyConsoleActions.setSizeArea(width /2, height /2-80);

        enemyConsoleStatus = new Console();
        enemyConsoleStatus.setSizeArea(width /2, height /2-80);
        enemyConsoleStatus.setSpeed(0);

        JPanel enemyBars = new JPanel(new BorderLayout());

        enemyBars.add(enemyHp, BorderLayout.NORTH);
        enemyBars.add(loyalty, BorderLayout.SOUTH);
        enemyPanel.add(enemyBars, BorderLayout.NORTH);
        enemyPanel.add(enemyConsoleActions, BorderLayout.WEST);
        enemyPanel.add(enemyConsoleStatus, BorderLayout.EAST);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        enemyPanel.setBorder(blackline);

        panel.add(enemyPanel, BorderLayout.LINE_START);


        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBorder(blackline);

        playerConsole = new Console();
        playerConsole.setSpeed(0);
        playerConsole.setSizeArea(width /2, height /2);

        JPanel playerActions = new JPanel();
        BoxLayout boxLayout = new BoxLayout(playerActions, BoxLayout.Y_AXIS);
        playerActions.setLayout(boxLayout);

        JButton playerAttackButton = new UnfocusedButton("Атаковать");
        playerAttackButton.setBackground(new Color(0,255,0));
        Dimension d = new Dimension(width /2, height /10);
        playerAttackButton.setMinimumSize(d);
        playerAttackButton.setPreferredSize(d);
        playerAttackButton.setMaximumSize(d);

        JButton playerAbilityButton = new UnfocusedButton("Способность");
        playerAbilityButton.setBackground(new Color(0,255,0));
        playerAbilityButton.setMinimumSize(d);
        playerAbilityButton.setPreferredSize(d);
        playerAbilityButton.setMaximumSize(d);

        JButton playerDiplomacyButton = new UnfocusedButton("Дипломатия");
        playerDiplomacyButton.setBackground(new Color(255,255,0));
        playerDiplomacyButton.setMinimumSize(d);
        playerDiplomacyButton.setPreferredSize(d);
        playerDiplomacyButton.setMaximumSize(d);

        JButton playerUseItemButton = new UnfocusedButton("Предметы");
        playerUseItemButton.setBackground(new Color(0,255,0));
        playerUseItemButton.setMinimumSize(d);
        playerUseItemButton.setPreferredSize(d);
        playerUseItemButton.setMaximumSize(d);

        JButton playerRunAwayButton = new UnfocusedButton("Побег");
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


        playerAttackButton.addActionListener((ActionListener & Serializable)e -> {
            attack(player, enemy);
            enemyTurn();
        });

        playerAbilityButton.addActionListener((ActionListener & Serializable)e -> {
            if (playerAbilityWindow == null){
                playerAbilityWindow = new PlayerAbilityWindow(player, enemy, FightWindow.this);
            } else {
                playerAbilityWindow.close();
                playerAbilityWindow = null;
            }
        });

        playerDiplomacyButton.addActionListener((ActionListener & Serializable)e -> {
            if (playerDiplomacyWindow == null){
                playerDiplomacyWindow = new PlayerDiplomacyWindow(player, enemy, FightWindow.this);
            } else {
                playerDiplomacyWindow.close();
                playerDiplomacyWindow = null;
            }
        });

        playerUseItemButton.addActionListener((ActionListener & Serializable)e -> {
            if (playerFightItemWindow == null){
                playerFightItemWindow = new PlayerFightItemWindow(player, enemy, FightWindow.this);
            } else {
                playerFightItemWindow.close();
                playerFightItemWindow = null;
            }
        });

        playerRunAwayButton.addActionListener((ActionListener & Serializable) e -> {
            int chance = (int)(Math.random()*(100-player.getStats().getLuck()/2));
            if (chance < 100*player.getLvl()/(enemy.getLvl()+1)){
                field.setIsVisible(true);
                field.drawMap();

                close(Screen.GAME);
            } else {
                writeToPlayerConsole("Вам не удалось сбежать!");
                enemyTurn();
            }
        });


        playerActions.add(playerAttackButton, BorderLayout.LINE_START);
        playerActions.add(playerAbilityButton, BorderLayout.AFTER_LAST_LINE);
        playerActions.add(playerDiplomacyButton, BorderLayout.LINE_START);
        playerActions.add(playerUseItemButton, BorderLayout.AFTER_LAST_LINE);
        playerActions.add(playerRunAwayButton, BorderLayout.LINE_START);

        playerPanel.add(playerConsole, BorderLayout.WEST);
        playerPanel.add(playerActions, BorderLayout.EAST);
        playerPanel.add(playerHp, BorderLayout.SOUTH);

        panel.add(playerPanel, BorderLayout.AFTER_LAST_LINE);
        add(panel);
        setVisible(true);
        field.setIsVisible(false);
        printHp();
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

    void enemyTurn() {

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
        printHp();
    }

    private void getReward(){
        Thread thread = new Thread((Runnable & Serializable)() -> {
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
            if(enemy instanceof Boss boss){
                dropItems.addAll(Arrays.asList(boss.getDropItems()));
            }
            for (int i = 0; i < countItemsDrop; i++){
                Item item = null;
                try {
                    item = (Item)enemy.getUniqueDropItems()[(int)(Math.random()*enemy.getUniqueDropItems().length)].clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                if(item instanceof Ingredient){
                    dropItems.add(item);
                    continue;
                }

                if(item instanceof Ring ring){
                    ring.setStat(StatsEnum.values()[(int)(Math.random() * StatsEnum.values().length)]);
                } else {
                    int chanceEnchant = (int)Math.ceil(Math.random()*100);
                    if(chanceEnchant < 3){
                        if(item instanceof Armor){
                            Enchant[] armorEnchants = new Enchant[]{new SpikeArmor(item), new HigherPath(item)};
                            item.addEnchant(armorEnchants[(int)(Math.random()*armorEnchants.length)]);
                        } else if(item instanceof Weapon){
                            Enchant[] weaponEnchants = new Enchant[]{new Vampirism(item), new KornelCurse(item)};
                            item.addEnchant(weaponEnchants[(int)(Math.random()*weaponEnchants.length)]);
                        }
                    }
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
                    item.setGrade(Grade.ABOVE_THE_GODS);
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

                if (item instanceof Weapon){
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
                } else if (item instanceof Torso || item instanceof Helmet){

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

            for(Item item : enemy.getInventory()){
                int chanceDropItem = (int)Math.ceil(Math.random()*100);
                if(chanceDropItem < 5){
                    dropItems.add(item);
                }
            }

            player.addMoney(rewardMoney);
            player.addExp(rewardExp);

            field.setIsVisible(true);
            Chest chest = null;
            if(enemy.getHp() <= 0)
                chest = new Corpse(enemy.getX(), enemy.getY());
            if(enemy.getLoyaltyByIndex(player) == 100)
                chest = new Chest(enemy.getX(), enemy.getY());

            for(Item item : dropItems){
                if(item != null && chest != null)
                    chest.addItemToInventory(item);
            }
            field.getMap().setElementByCoordinates(enemy.getX(), enemy.getY(), chest);
            field.getMap().setElementByCoordinatesUpper(enemy.getX(), enemy.getY(), null);

            if (player.getQuests() != null){
                for (Quest quest : player.getQuests()){
                    if(quest instanceof KillQuest killQuest && enemy.getLastProperty() == killQuest.getEnemyToKill().getLastProperty()){
                        killQuest.setEnemyCountToKillCurrent(killQuest.getEnemyCountToKillCurrent() + 1);
                    }
                    if(quest instanceof CollectItemQuest collect){
                        for (Item item : enemy.getUniqueDropItems()){
                            if (item.getLastProperty() == collect.getItem().getLastProperty()){
                                player.addItemToInventory(collect.getItem());
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
            if(playerDiplomacyWindow != null){
                playerDiplomacyWindow.close();
            }
            field.getNpcController().setWaiting(false);
            enemy = null;
            field.drawMap();

            close(Screen.GAME);
        });
        thread.start();
    }

    private void loss() {
        if (playerAbilityWindow != null){
            playerAbilityWindow.close();
        }
        if(playerFightItemWindow != null){
            playerFightItemWindow.close();
        }
        if(playerDiplomacyWindow != null){
            playerDiplomacyWindow.close();
        }
        close(Screen.LOSS);
    }

    public int getCountMoves() {
        return countMoves;
    }

    public void setCountMoves(int countMoves) {
        this.countMoves = countMoves;
    }

    private void attack(LiveCreature attacker, LiveCreature enemy){
        int chanceToAvoid = (int)Math.ceil(Math.random()*100 - Math.pow(Math.E, -4.0*enemy.getLvl()/enemy.getStats().getLuck()));
        if (enemy.hasAbility(AbilityProperty.EVASION) && chanceToAvoid <= enemy.getAbility(AbilityProperty.EVASION).getChance()){
            if(attacker instanceof Player){
                writeToPlayerConsole(attacker.getName() + " промахнулся");
                writeToEnemyActionConsole(enemy.getName() + " увернулся");
            } else {
                writeToEnemyActionConsole(attacker.getName() + " промахнулся");
                writeToPlayerConsole(enemy.getName() + " увернулся");
            }
        } else {

            attacker.useRacePower(enemy);

            for(Item item : attacker.getEquipment().getListOfEquipment()){
                if(item != null)
                    for(Enchant enchant : item.getEnchants()){
                        if(enchant.getEnchantType() == EnchantType.SELFUSE) {
                            enchant.use(attacker);
                        }
                        if(enchant.getEnchantType() == EnchantType.ATTACK) {
                            enchant.use(enemy);
                        }
                    }
            }
            for(Weapon weapon : attacker.getEquipment().getWeaponList()){
                if(weapon != null){
                    weapon.setBonusDamage(1);
                    for(WeaponType weaponType : weapon.getWeaponType()){
                        switch (weaponType) {
                            case ONE_HANDED -> {
                                if (attacker.getStats().getOneHandedWeapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getOneHandedWeapon() / 150.0);
                                }
                            }
                            case TWO_HANDED -> {
                                if (attacker.getStats().getTwoHandedWeapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getTwoHandedWeapon() / 150.0);
                                }
                            }
                            case LONG_RANGE -> {
                                if (attacker.getStats().getLongRangeWeapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getLongRangeWeapon() / 150.0);
                                }
                            }
                            case POLE -> {
                                if (attacker.getStats().getPoleWeapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getPoleWeapon() / 150.0);
                                }
                            }
                            case CHOPPING -> {
                                if (attacker.getStats().getChoppingWeapon() != 0) {
                                    weapon.addBonusDamage(attacker.getStats().getChoppingWeapon() / 150.0);
                                }
                            }
                        }
                    }
                }
            }

            double damage = (int)((attacker.getStats().getStrength() + attacker.getEquipment().getWeaponDamage())*(Math.min(1, Math.max(0, (200 - (enemy.getStats().getStrength()-attacker.getStats().getStrength()))/200 + (200 - (enemy.getStats().getAgility()-attacker.getStats().getAgility()))/200 + (200 - (enemy.getStats().getSpeed()-attacker.getStats().getSpeed()))/200))/3 + 1));

            int chanceToCrit = (int)Math.ceil(Math.random()*100 - Math.pow(Math.E, -4.0*attacker.getLvl()/attacker.getStats().getLuck()));
            Ability criticalStrike = attacker.getAbility(AbilityProperty.CRITICAL_STRIKE);
            if(attacker.hasAbility(AbilityProperty.CRITICAL_STRIKE) && chanceToCrit <= criticalStrike.getChance()){
                if(attacker instanceof Player)
                    writeToPlayerConsole("Критический удар(x"+ criticalStrike.getPower() / 100.0 + ")!");
                else
                    writeToEnemyStatusConsole(  "Критический удар(x"+ criticalStrike.getPower() / 100.0 + ")!");
                damage *= criticalStrike.getPower()/100.0;
            }

            attacker.setCurrentDamage(damage);

            if (attacker.getBuffs() != null){
                for (Buff buff : attacker.getBuffs()){
                    buff.use(attacker);
                }
            }
            damage = attacker.getCurrentDamage();

//            damage = damage*((100-attacker.getLoyaltyByIndex(enemy))/100.0);

            double decQ = damage;
            damage = Math.round(enemy.absorbDamage(damage)*100.0)/100.0;
            decQ = Math.max(0, (decQ - damage)/1000);

            for(Item item : enemy.getEquipment().getListOfEquipment()){
                if(item != null)
                    for(Enchant enchant : item.getEnchants()){
                        enchant.setDamage(damage);
                        if(enchant.getEnchantType() == EnchantType.COUNTERATTACK){
                            enchant.use(attacker);
                        }
                        if(enchant.getEnchantType() == EnchantType.DEFEND){
                            enchant.use(enemy);
                        }
                    }
            }

            enemy.setHp(Math.round((enemy.getHp()-damage)*100.0)/100.0);


            for(Weapon weapon : attacker.getEquipment().getWeaponList())
                if(weapon != null)
                    weapon.decreaseQuality(decQ);

            for(Armor Armor : enemy.getEquipment().getArmor())
                if(Armor != null)
                    Armor.decreaseQuality(decQ);

            enemy.addLoyaltyToCreature(attacker, -(int)(enemy.getHp()/damage));

            for(Weapon weapon : attacker.getEquipment().getWeaponList()){
                if(weapon != null){
                    weapon.weaponSkill(enemy, FightWindow.this, attacker);
                }
            }
            if (attacker instanceof Player){
                writeToPlayerConsole(attacker.getName() + " нанес " + damage + " единиц урона");
                writeToEnemyStatusConsole(enemy.getName() + " получил " + damage + " единиц урона");
            } else {
                writeToEnemyStatusConsole(attacker.getName() + " нанес " + damage + " единиц урона");
                writeToPlayerConsole(enemy.getName() + " получил " + damage + " единиц урона");
            }
        }

        printHp();
    }

    public void attackBySpell(LiveCreature attacker, LiveCreature enemy, Ability ability){
        if(attacker instanceof Player){
            writeToPlayerConsole(attacker.getName() + " использовал " + ability.getName() + "(" + ability.getPower() + ")");
        } else {
            writeToEnemyActionConsole(attacker.getName() + " использовал " + ability.getName() + "(" + ability.getPower() + ")");
        }
        if (ability.getAbilityType().contains(AbilityType.BUFF)){
            if(ability.getAbilityType().contains(AbilityType.ACTIVE) && !attacker.getEquipment().staffEquip()){
                ((ActiveAbility)ability).chargeFee(attacker);
            }
            Buff buff = ((ActiveAbility) ability).getBuff();
            buff.upgrade(attacker);
            if (((ActiveAbility)ability).getAbilityTarget() == AbilityTarget.PLAYER){
                if(attacker instanceof Player){
                    writeToPlayerConsole("На " + attacker.getName() + " наложен эффект " + ((ActiveAbility) ability).getBuff().getName());
                } else {
                    writeToEnemyStatusConsole("На " + attacker.getName() + " наложен эффект " + ((ActiveAbility) ability).getBuff().getName());
                }
                attacker.addBuffs(buff);
            } else if (((ActiveAbility)ability).getAbilityTarget() == AbilityTarget.ENEMY){
                if(attacker instanceof Player){
                    writeToPlayerConsole("На " + enemy.getName() + " наложен эффект " + ((ActiveAbility) ability).getBuff().getName());
                } else {
                    writeToEnemyStatusConsole("На " + enemy.getName() + " наложен эффект " + ((ActiveAbility) ability).getBuff().getName());
                }
                enemy.addBuffs(buff);
            }
        } else if (((ActiveAbility)ability).getAbilityTarget() == AbilityTarget.ENEMY){
            ((ActiveAbility)ability).use(enemy);
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
        if(player.getHp() < 0){
            loss();
        } else if(enemy.getHp() <= 0){
            getReward();
        } else if(enemy.getLoyaltyByIndex(player) == 100){
            getReward();
        } else {
            playerHp.setValue((int)player.getHp());
            enemyHp.setValue((int)enemy.getHp());
            loyalty.setValue(enemy.getLoyaltyByIndex(player));
            playerHp.setString(player.getHp() + "/" + player.getMaxHp());
            enemyHp.setString(Double.toString(enemy.getHp()));
        }
    }

    private void close(Screen screen) {
        multiWindow.removeWindow(this);
        multiWindow.switchScreen(screen);
        field.returnKeyControl();
    }
}
