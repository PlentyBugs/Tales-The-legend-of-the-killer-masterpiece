package Windows;

import AI.NPCController;
import Abilities.Passive.LittleFool;
import Creatures.GodCreature;
import Creatures.LiveCreature;
import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Items.BlackSmith.Resource.Resource;
import Items.Tools.Pickaxe;
import Locations.Cave.Cave;
import Locations.Dungeon.Dungeon;
import Locations.Map;
import Things.AlchemyThings.IngredientThing;
import Things.ChestLike.Chest;
import Things.Craft.CraftTable;
import Things.Doors.CaveDoor;
import Things.Doors.Door;
import Things.Doors.DoorToUpperLevelLocation;
import Things.Grass;
import Things.HealBlock;
import Things.Ore;
import Things.Stone;
import Windows.BattleWindows.GodCreatureButton;
import Windows.BattleWindows.LossWindow;
import Windows.SupportWindows.SupportComponents.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class FieldWindow extends JFrame implements Serializable, KeyListener {
    private int x, y;
    private int realVision;
    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints mainConstraints = new GridBagConstraints();
    private JPanel contentPanel = new JPanel(new GridBagLayout());
    private JTabbedPane menu = new JTabbedPane();
    private JPanel inventory;
    private JPanel upgrade;
    private JPanel equipment;
    private JPanel info;
    private JPanel abilities;
    private JPanel quests;
    private JPanel disease;
    private Map currentMap;
    private NPCController npcController;
    private static final long serialVersionUID = -5963455665311017981L;

    private Console console;

    public FieldWindow(String name, Map map) {
        super(name);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        this.player = map.getPlayer();

        inventory = player.getInventoryWindow().getPanel();
        upgrade = player.getUpStatsWindow().getPanel();
        equipment = player.getEquipmentWindow().getPanel();
        info = player.getPlayerInfoWindow().getPanel();
        abilities = player.getPlayerAbilityWindow().getPanel();
        quests = player.getPlayerQuestWindow().getPanel();
        disease = player.getDiseasesWindow().getPanel();

        console = new Console();

        x = 1024;
        y = 720;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        console.setSizeArea((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-40, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/6.5));

        currentMap = map;

        npcController = new NPCController();
        npcController.start();

        drawMap();
    }

    private void drawField(GodCreature[][] information){
        npcController.clear();
        npcController.setFieldWindow(this);

        realVision = player.getVision()*2+1;
        int width = (int)(x*0.95)/realVision;
        int height = (int)(y*0.7)/realVision;

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 0, 0, 0);

        player.removeBrokenItems();

        for (int i = 0; i < realVision; i++){
            for (int j = 0; j < realVision; j++) {
                constraints.gridx = j;
                constraints.gridy = i;
                GodCreatureButton button = new GodCreatureButton(information[i][j]);
                button.setText(information[i][j].getName());
                button.setBounds(0,0,100,50);
                button.setBackground(information[i][j].getColor());
                button.setLocation((width+5)*j + 8,(height+5)*i + 5);
                button.setFont(new Font("TimesRoman", Font.BOLD, (int)(80/Math.pow(realVision, 0.9))));
                if(information[i][j].getColor().getRed() < 70 && information[i][j].getColor().getBlue() < 70 && information[i][j].getColor().getGreen() < 70){
                    button.setForeground(new Color(255 - information[i][j].getColor().getRed(), 255 - information[i][j].getColor().getGreen(), 255 - information[i][j].getColor().getBlue()));
                }

                button.setPreferredSize(new Dimension(x/realVision,(int)(0.7*y/realVision)));
                button.setMinimumSize(new Dimension(x/realVision,(int)(0.7*y/realVision)));
                button.setMaximumSize(new Dimension(x/realVision,(int)(0.7*y/realVision)));

                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                control(information[i][j], button);

                panel.add(button, constraints);
            }
        }
    }

    public void writeToConsole(String text) {
        console.writeToConsole(text);
    }

    public void drawMap(boolean controlledByController){
        getContentPane().removeAll();
        mainPanel = new JPanel(new GridBagLayout());
        mainConstraints = new GridBagConstraints();
        mainConstraints.anchor = GridBagConstraints.WEST;
        mainConstraints.insets = new Insets(0, 5, 0, 5);
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;

        panel = new JPanel(new GridBagLayout());
        drawField(currentMap.getMap(player.getX(),player.getY()));
        mainPanel.add(panel, mainConstraints);

        fillContentPanel();

        getContentPane().add(mainPanel, BorderLayout.NORTH);
        getContentPane().add(console, BorderLayout.SOUTH);
        setVisible(!player.getInFight());

        player.countPassiveBuffs();
        player.checkQuests();
        player.countEquipmentBuffs();
        realVision = player.getVision()*2+1;
        if(player.getHp() <= 0){
            setVisible(false);
            new LossWindow();
        }
    }

    public void drawMap(){
        player.setLocation(currentMap.getLocationName());
        getContentPane().removeAll();
        mainPanel = new JPanel(new GridBagLayout());
        mainConstraints = new GridBagConstraints();
        mainConstraints.anchor = GridBagConstraints.WEST;
        mainConstraints.insets = new Insets(0, 5, 0, 5);
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;

        panel = new JPanel(new GridBagLayout());
        drawField(currentMap.getMap(player.getX(),player.getY()));
        mainPanel.add(panel, mainConstraints);

        fillContentPanel();

        getContentPane().add(mainPanel, BorderLayout.NORTH);
        getContentPane().add(console, BorderLayout.SOUTH);
        setVisible(true);

        player.countPassiveBuffs();
        player.checkQuests();
        player.countEquipmentBuffs();
        realVision = player.getVision()*2+1;
        if(player.getHp() <= 0){
            setVisible(false);
            new LossWindow();
        }
    }

    private void fillContentPanel(){
        mainPanel.remove(contentPanel);

        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints contentConstraints = new GridBagConstraints();
        contentConstraints.anchor = GridBagConstraints.WEST;
        contentConstraints.insets = new Insets(0, 5, 0, 5);
        contentConstraints.gridx = 0;
        contentConstraints.gridy = 0;

        contentPanel.setMinimumSize(new Dimension(x - (x/realVision) -380, y));
        contentPanel.setMaximumSize(new Dimension(x - (x/realVision) -380, y));
        contentPanel.setPreferredSize(new Dimension(x - (x/realVision) -380, y));

        updateTabs();

        contentPanel.add(menu);
        mainConstraints.gridx = 1;
        mainPanel.add(contentPanel, mainConstraints);
    }

    private void updateTabs(){
        inventory = player.getInventoryWindow().getPanel();
        upgrade = player.getUpStatsWindow().getPanel();
        equipment = player.getEquipmentWindow().getPanel();
        info = player.getPlayerInfoWindow().getPanel();
        abilities = player.getPlayerAbilityWindow().getPanel();
        quests = player.getPlayerQuestWindow().getPanel();
        disease = player.getDiseasesWindow().getPanel();

        if(menu.getComponents().length != 0){
            try{
                menu.setComponentAt(0, inventory);
                menu.setComponentAt(1, upgrade);
                menu.setComponentAt(2, equipment);
                menu.setComponentAt(3, info);
                menu.setComponentAt(4, abilities);
                menu.setComponentAt(5, quests);
                menu.setComponentAt(6, disease);
            } catch (Exception ex){
                menu.removeAll();
                menu.addTab("Инвентарь", inventory);
                menu.addTab("Прокачка", upgrade);
                menu.addTab("Экипировка", equipment);
                menu.addTab("Информация", info);
                menu.addTab("Умения", abilities);
                menu.addTab("Квесты", quests);
                menu.addTab("Болезни", disease);
            }
        } else {
            menu.setMinimumSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - x), y));
            menu.setMaximumSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - x), y));
            menu.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - x), y));
            menu.setMinimumSize(new Dimension(x - (x/realVision) -404, y));
            menu.setMaximumSize(new Dimension(x - (x/realVision) -404, y));
            menu.setPreferredSize(new Dimension(x - (x/realVision) -404, y));
            
            menu.addTab("Инвентарь", inventory);
            menu.addTab("Прокачка", upgrade);
            menu.addTab("Экипировка", equipment);
            menu.addTab("Информация", info);
            menu.addTab("Умения", abilities);
            menu.addTab("Квесты", quests);
            menu.addTab("Болезни", disease);
        }

    }

    public Console getConsole() {
        return console;
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        setVisible(b);
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public Player getPlayer() {
        return player;
    }

    public void drawAllPlayerWindow(){
        player.getInventoryWindow().drawInventory();
        player.getUpStatsWindow().drawWindow();
        player.getEquipmentWindow().drawEquipment();
        player.getPlayerInfoWindow().drawInfo();
        player.getPlayerAbilityWindow().drawWindow();
        player.getPlayerQuestWindow().drawWindow();
        player.getDiseasesWindow().drawWindow();
        drawMap();
    }

    public void keyPressed(KeyEvent event){
        if(event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A){
            if(player != null && currentMap.getMapLowerObjects()[player.getY()][player.getX()-1] != null){
                if(currentMap.getMapUpperObjects()[player.getY()][player.getX()-1] != null)
                    step(currentMap.getMapUpperObjects()[player.getY()][player.getX()-1]);
                else
                    step(currentMap.getMapLowerObjects()[player.getY()][player.getX()-1]);
                drawMap();
            }
        } else if(event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D){
            if(currentMap.getMapUpperObjects()[player.getY()][player.getX()+1] != null)
                step(currentMap.getMapUpperObjects()[player.getY()][player.getX()+1]);
            else
            step(currentMap.getMapLowerObjects()[player.getY()][player.getX()+1]);
            drawMap();
        } else if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W){
            if(currentMap.getMapUpperObjects()[player.getY()-1][player.getX()] != null)
                step(currentMap.getMapUpperObjects()[player.getY()-1][player.getX()]);
            else
            step(currentMap.getMapLowerObjects()[player.getY()-1][player.getX()]);
            drawMap();
        } else if(event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_S){
            if(currentMap.getMapUpperObjects()[player.getY()+1][player.getX()] != null)
                step(currentMap.getMapUpperObjects()[player.getY()+1][player.getX()]);
            else
            step(currentMap.getMapLowerObjects()[player.getY()+1][player.getX()]);
            drawMap();
        }
    }

    public void keyReleased(KeyEvent event){}

    public void keyTyped(KeyEvent event){}

    public NPCController getNpcController() {
        return npcController;
    }

    private void control(GodCreature creature, GodCreatureButton button) {

        boolean isStep = creature.getIsStep();
        int X = creature.getX();
        int Y = creature.getY();

        boolean isLiveCreature = creature instanceof LiveCreature;
        boolean isAlchemyThings = creature.getClass().toString().contains("AlchemyThings");
        boolean isOre = creature instanceof Ore;
        boolean isHealBlock = creature instanceof HealBlock;
        boolean isDoorToUpperLevel = creature instanceof Door;
        boolean isChest = creature instanceof Chest;
        boolean isCraft = creature instanceof CraftTable;

        if(!(creature instanceof Player) && creature instanceof LiveCreature){
            npcController.addNPC((LiveCreature)creature);
        }

        if(player.hasAbility(new LittleFool()) && creature.getClass().toString().contains("Tree")){
            isStep = true;
        }
        if (creature.getIsPlayer()){
            button.addActionListener(e -> drawAllPlayerWindow());
        } else if (isHealBlock){
            button.addActionListener((ActionListener & Serializable)e -> {
                player.setFieldWindow(FieldWindow.this);
                currentMap.setElementByCoordinates(creature.getX(), creature.getY(), new Grass(creature.getX(), creature.getY()));
                ((HealBlock) creature).heal(player);
                int healBlockY = (int)(Math.random()*(currentMap.getMapHeight()-1));
                int healBlockX = (int)(Math.random()*(currentMap.getMapWidth()-1));
                currentMap.setElementByCoordinates(healBlockX, healBlockY, new HealBlock(healBlockX, healBlockY));
                drawMap();
                drawAllPlayerWindow();
            });
        } else if (isDoorToUpperLevel) {
            button.addActionListener((ActionListener & Serializable)e -> {
                //todo add function to the Door class to check the key
                if(!((Door)creature).getIsLocked() || (((Door)creature).getIsLocked() && player.hasItem(((Door)creature).getKey()))){
                    if(((Door)creature).getIsLocked()){
                        player.removeItem(((Door)creature).getKey());
                        ((Door)creature).setIsLocked(false);
                    }
                    currentMap.setPlayerX(player.getX());
                    currentMap.setPlayerY(player.getY());
                    ((Door)creature).setIn(currentMap);
                    if(creature instanceof DoorToUpperLevelLocation){
                        ((Door)creature).setGeneration(() -> {
                            Map map;
                            if(((Door)creature).getOut() == null){
                                map = new Map();

                                Dungeon dungeon = new Dungeon(player);
                                GodCreature[][][] zxc = dungeon.getMap();
                                map.setMapLowerObjects(zxc[0]);
                                map.setMapUpperObjects(zxc[1]);
                                map.setMapHeight();
                                map.setMapWidth();
                                map.setLocationName(dungeon.getLocationName());
                                ((Door)creature).setOut(map);
                                player.setX(dungeon.getPlayerXSafety());
                                player.setY(dungeon.getPlayerYSafety());
                                DoorToUpperLevelLocation door = new DoorToUpperLevelLocation();
                                door.setOut(currentMap);
                                door.setIsLocked(false);
                                map.setElementByCoordinates(player.getX(), player.getY(), door);
                            } else {
                                map = ((Door)creature).getOut();
                                player.setX(map.getPlayerX());
                                player.setY(map.getPlayerY());
                            }
                            map.setPlayer(player);
                            return map;
                        });
                    } else if(creature instanceof CaveDoor){
                        ((Door)creature).setGeneration(() -> {
                            Map map;
                            if(((Door)creature).getOut() == null){
                                map = new Map();
                                Cave cave = new Cave();

                                map.setMapLowerObjects(cave.getCave());
                                map.setMapHeight();
                                map.setMapWidth();
                                ((Door)creature).setOut(map);

                                player.setX(cave.getPlayerSafeX());
                                player.setY(cave.getPlayerSafeY());

                                CaveDoor door = new CaveDoor();
                                door.setOut(currentMap);
                                door.setIsLocked(false);
                                map.setElementByCoordinates(player.getX(), player.getY(), door);
                            } else {
                                map = ((Door)creature).getOut();
                                player.setX(map.getPlayerX());
                                player.setY(map.getPlayerY());
                            }
                            map.setPlayer(player);
                            return map;
                        });
                    }
                    currentMap = ((Door)creature).generate();
                    System.gc();
                    player.setFieldWindow(FieldWindow.this);
                    drawMap();
                }
                drawAllPlayerWindow();
            });
        } else if(isCraft){
            button.addActionListener((ActionListener & Serializable)e -> {
                ((CraftTable) creature).setPlayer(player);
                if(!((CraftTable) creature).getCraftTableWindowOpen()){
                    ((CraftTable) creature).setCraftTableWindow(true);
                    ((CraftTable) creature).setCraftTableWindowOpen(true);
                } else {
                    ((CraftTable) creature).setCraftTableWindow(false);
                    ((CraftTable) creature).setCraftTableWindowOpen(false);
                }
            });
        }else if (isChest) {
            button.addActionListener((ActionListener & Serializable)e -> {
                player.setFieldWindow(FieldWindow.this);
                if(!((Chest) creature).getIsInventoryChestOpen()){
                    ((Chest) creature).setPlayer(player);
                    ((Chest) creature).setInventoryWindow();
                    ((Chest) creature).setInventoryWindowChestIsVisible(true);
                    ((Chest) creature).setInventoryChestOpen(true);
                } else {
                    ((Chest) creature).getInventoryWindow().close();
                    ((Chest) creature).setInventoryWindowChestIsVisible(false);
                    ((Chest) creature).setInventoryChestOpen(false);
                }
                drawAllPlayerWindow();
            });
        } else {
            boolean finalIsStep = isStep;
            button.addActionListener((ActionListener & Serializable) e -> {
                if (finalIsStep) {
                    player.setFieldWindow(FieldWindow.this);
                    player.setX(X);
                    player.setY(Y);
                    if (isAlchemyThings) {
                        player.addItemToInventory((Ingredient) ((IngredientThing) creature).getIngredient());
                        GodCreature godCreature = (GodCreature) ((IngredientThing) creature).getParent();
                        godCreature.setX(X);
                        godCreature.setY(Y);
                        currentMap.setElementByCoordinates(X, Y, godCreature);
                    }
                    if (isOre && player.hasItem(new Pickaxe())) {
                        player.addItemToInventory((Resource) ((Ore) creature).getResource());
                        GodCreature godCreature = new Stone();
                        godCreature.setX(X);
                        godCreature.setY(Y);
                        currentMap.setElementByCoordinates(X, Y, godCreature);
                    }
                    drawMap();
                } else if (isLiveCreature) {
                    player.setFieldWindow(FieldWindow.this);
                    if (((LiveCreature) creature).getTalkative()) {
                        ((LiveCreature) creature).setConversationWindowPlayer(player);
                        if (!((LiveCreature) creature).getIsConversationWindowOpen()) {
                            ((LiveCreature) creature).setConversationWindowIsVisible(true);
                            ((LiveCreature) creature).setConversationWindowOpen(true);
                        } else {
                            ((LiveCreature) creature).setConversationWindowIsVisible(false);
                            ((LiveCreature) creature).setConversationWindowOpen(false);
                        }
                    } else {
                        player.setFieldWindow(FieldWindow.this);
                        if (((LiveCreature) creature).getHp() == 0) {
                            ((LiveCreature) creature).countStatsAfterBorn();
                        }
                        if (creature.getChooseEnemyWindow() == null) {
                            creature.setChooseEnemyWindow(player, FieldWindow.this, (LiveCreature) creature);
                        }
                        if (!creature.getIsChooseEnemyWindowOpen()) {
                            creature.setChooseEnemyWindowIsVisible(true);
                            creature.setChooseEnemyWindowOpen(true);
                        } else {
                            creature.setChooseEnemyWindowIsVisible(false);
                            creature.setChooseEnemyWindowOpen(false);
                        }
                    }
                }
                drawAllPlayerWindow();
            });
        }
    }
    private void step(GodCreature creature) {

        boolean isStep = creature.getIsStep();
        Step step;
        int X = creature.getX();
        int Y = creature.getY();

        boolean isLiveCreature = creature instanceof LiveCreature;
        boolean isAlchemyThings = creature.getClass().toString().contains("AlchemyThings");
        boolean isOre = creature instanceof Ore;
        boolean isHealBlock = creature instanceof HealBlock;
        boolean isDoorToUpperLevel = creature instanceof Door;
        boolean isChest = creature instanceof Chest;
        boolean isCraft = creature instanceof CraftTable;

        if(!(creature instanceof Player) && creature instanceof LiveCreature){
            npcController.addNPC((LiveCreature)creature);
        }

        if(player.hasAbility(new LittleFool()) && creature.getClass().toString().contains("Tree")){
            isStep = true;
        }
        if (isHealBlock){
            step = (Step & Serializable)() -> {
                player.setFieldWindow(FieldWindow.this);
                currentMap.setElementByCoordinates(creature.getX(), creature.getY(), new Grass(creature.getX(), creature.getY()));
                ((HealBlock) creature).heal(player);
                int healBlockY = (int)(Math.random()*(currentMap.getMapHeight()-1));
                int healBlockX = (int)(Math.random()*(currentMap.getMapWidth()-1));
                currentMap.setElementByCoordinates(healBlockX, healBlockY, new HealBlock(healBlockX, healBlockY));
                drawMap();
                drawAllPlayerWindow();
            };
        } else if (isDoorToUpperLevel) {
            step = (Step & Serializable)() -> {
                //todo add function to the Door class to check the key
                if(!((Door)creature).getIsLocked() || (((Door)creature).getIsLocked() && player.hasItem(((Door)creature).getKey()))){
                    if(((Door)creature).getIsLocked()){
                        player.removeItem(((Door)creature).getKey());
                        ((Door)creature).setIsLocked(false);
                    }
                    currentMap.setPlayerX(player.getX());
                    currentMap.setPlayerY(player.getY());
                    ((Door)creature).setIn(currentMap);
                    if(creature instanceof DoorToUpperLevelLocation){
                        ((Door)creature).setGeneration(() -> {
                            Map map;
                            if(((Door)creature).getOut() == null){
                                map = new Map();

                                Dungeon dungeon = new Dungeon(player);
                                GodCreature[][][] zxc = dungeon.getMap();
                                map.setMapLowerObjects(zxc[0]);
                                map.setMapUpperObjects(zxc[1]);
                                map.setMapHeight();
                                map.setMapWidth();
                                map.setLocationName(dungeon.getLocationName());
                                ((Door)creature).setOut(map);
                                player.setX(dungeon.getPlayerXSafety());
                                player.setY(dungeon.getPlayerYSafety());
                                DoorToUpperLevelLocation door = new DoorToUpperLevelLocation();
                                door.setOut(currentMap);
                                door.setIsLocked(false);
                                map.setElementByCoordinates(player.getX(), player.getY(), door);
                            } else {
                                map = ((Door)creature).getOut();
                                player.setX(map.getPlayerX());
                                player.setY(map.getPlayerY());
                            }
                            map.setPlayer(player);
                            return map;
                        });
                    } else if(creature instanceof CaveDoor){
                        ((Door)creature).setGeneration(() -> {
                            Map map;
                            if(((Door)creature).getOut() == null){
                                map = new Map();
                                Cave cave = new Cave();

                                map.setMapLowerObjects(cave.getCave());
                                map.setMapHeight();
                                map.setMapWidth();
                                ((Door)creature).setOut(map);

                                player.setX(cave.getPlayerSafeX());
                                player.setY(cave.getPlayerSafeY());

                                CaveDoor door = new CaveDoor();
                                door.setOut(currentMap);
                                door.setIsLocked(false);
                                map.setElementByCoordinates(player.getX(), player.getY(), door);
                            } else {
                                map = ((Door)creature).getOut();
                                player.setX(map.getPlayerX());
                                player.setY(map.getPlayerY());
                            }
                            map.setPlayer(player);
                            return map;
                        });
                    }
                    currentMap = ((Door)creature).generate();
                    System.gc();
                    player.setFieldWindow(FieldWindow.this);
                    drawMap();
                }
                drawAllPlayerWindow();
            };
        } else if(isCraft){
            step = (Step & Serializable)() -> {
                ((CraftTable) creature).setPlayer(player);
                if(!((CraftTable) creature).getCraftTableWindowOpen()){
                    ((CraftTable) creature).setCraftTableWindow(true);
                    ((CraftTable) creature).setCraftTableWindowOpen(true);
                } else {
                    ((CraftTable) creature).setCraftTableWindow(false);
                    ((CraftTable) creature).setCraftTableWindowOpen(false);
                }
            };
        }else if (isChest) {
            step = (Step & Serializable)() -> {
                player.setFieldWindow(FieldWindow.this);
                if(!((Chest) creature).getIsInventoryChestOpen()){
                    ((Chest) creature).setPlayer(player);
                    ((Chest) creature).setInventoryWindow();
                    ((Chest) creature).setInventoryWindowChestIsVisible(true);
                    ((Chest) creature).setInventoryChestOpen(true);
                } else {
                    ((Chest) creature).getInventoryWindow().close();
                    ((Chest) creature).setInventoryWindowChestIsVisible(false);
                    ((Chest) creature).setInventoryChestOpen(false);
                }
                drawAllPlayerWindow();
            };
        } else {
            boolean finalIsStep = isStep;
            step = (Step & Serializable)() -> {
                if (finalIsStep) {
                    player.setFieldWindow(FieldWindow.this);
                    player.setX(X);
                    player.setY(Y);
                    if (isAlchemyThings) {
                        player.addItemToInventory((Ingredient) ((IngredientThing) creature).getIngredient());
                        GodCreature godCreature = (GodCreature) ((IngredientThing) creature).getParent();
                        godCreature.setX(X);
                        godCreature.setY(Y);
                        currentMap.setElementByCoordinates(X, Y, godCreature);
                    }
                    if (isOre && player.hasItem(new Pickaxe())) {
                        player.addItemToInventory((Resource) ((Ore) creature).getResource());
                        GodCreature godCreature = new Stone();
                        godCreature.setX(X);
                        godCreature.setY(Y);
                        currentMap.setElementByCoordinates(X, Y, godCreature);
                    }
                    drawMap();
                } else if (isLiveCreature) {
                    player.setFieldWindow(FieldWindow.this);
                    if (((LiveCreature) creature).getTalkative()) {
                        ((LiveCreature) creature).setConversationWindowPlayer(player);
                        if (!((LiveCreature) creature).getIsConversationWindowOpen()) {
                            ((LiveCreature) creature).setConversationWindowIsVisible(true);
                            ((LiveCreature) creature).setConversationWindowOpen(true);
                        } else {
                            ((LiveCreature) creature).setConversationWindowIsVisible(false);
                            ((LiveCreature) creature).setConversationWindowOpen(false);
                        }
                    } else {
                        player.setFieldWindow(FieldWindow.this);
                        if (((LiveCreature) creature).getHp() == 0) {
                            ((LiveCreature) creature).countStatsAfterBorn();
                        }
                        if (creature.getChooseEnemyWindow() == null) {
                            creature.setChooseEnemyWindow(player, FieldWindow.this, (LiveCreature) creature);
                        }
                        if (!creature.getIsChooseEnemyWindowOpen()) {
                            creature.setChooseEnemyWindowIsVisible(true);
                            creature.setChooseEnemyWindowOpen(true);
                        } else {
                            creature.setChooseEnemyWindowIsVisible(false);
                            creature.setChooseEnemyWindowOpen(false);
                        }
                    }
                }
                drawAllPlayerWindow();
            };
        }
        step.step();
    }
}
