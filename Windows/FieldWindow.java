package Windows;

import AI.NPCController;
import Creatures.Player;
import Locations.Map;
import Windows.SupportWindows.SupportComponents.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.io.Serializable;
import java.util.stream.IntStream;

public class FieldWindow extends JFrame implements Serializable, WindowInterface {
    private final int y;
    private final Player player;
    private final FieldPanel fieldPanel;
    private final JPanel subPanel = new JPanel(new BorderLayout());
    private final JTabbedPane menu = new JTabbedPane();
    private JPanel contentPanel = new JPanel(new GridBagLayout());
    private JPanel inventory;
    private JPanel upgrade;
    private JPanel equipment;
    private JPanel info;
    private JPanel abilities;
    private JPanel quests;
    private JPanel disease;
    private Map currentMap;
    private final NPCController npcController;
    @Serial
    private static final long serialVersionUID = -5963455665311017981L;
    private final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private final Console console;

    public FieldWindow(String name, Map map) {
        super(name);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        this.player = map.getPlayer();
        fieldPanel = new FieldPanel(map);

        inventory = player.getInventoryWindow().getPanel();
        upgrade = player.getUpStatsWindow().getPanel();
        equipment = player.getEquipmentWindow().getPanel();
        info = player.getPlayerInfoWindow().getPanel();
        abilities = player.getPlayerAbilityWindow().getPanel();
        quests = player.getPlayerQuestWindow().getPanel();
        disease = player.getDiseasesWindow().getPanel();

        console = new Console();

        y = 720;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        console.setSizeArea((int) (SCREEN_WIDTH * 0.4), (int)(SCREEN_HEIGHT / 6.5));

        Dimension subPanelSize = new Dimension((int) (SCREEN_WIDTH * 0.4), SCREEN_HEIGHT);
        subPanel.setSize(subPanelSize);
        subPanel.setPreferredSize(subPanelSize);
        subPanel.setMaximumSize(subPanelSize);
        subPanel.setMinimumSize(subPanelSize);
        subPanel.add(console, BorderLayout.SOUTH);

        currentMap = map;

        npcController = new NPCController();
        npcController.start();

        drawMap();
    }

    public void writeToConsole(String text) {
        console.writeToConsole(text);
    }

    @Override
    public void drawMap(){

//        npcController.clear();
//        npcController.setWindowInterface(this);
//        player.removeBrokenItems();
//        setVisible(!player.getInFight());
//        player.countPassiveBuffs();
//        player.checkQuests();
//        player.countEquipmentBuffs();
//        if(player.getHp() <= 0){
//            setVisible(false);
//            new LossWindow();
//        }
        fieldPanel.updateUI();
        fieldPanel.setPreferredSize(new Dimension((int) (SCREEN_WIDTH * 0.6), SCREEN_HEIGHT));

        fillContentPanel();

        getContentPane().add(fieldPanel, BorderLayout.WEST);
        getContentPane().add(subPanel, BorderLayout.EAST);
        setVisible(true);
    }

    private void fillContentPanel(){
        subPanel.remove(contentPanel);

        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints contentConstraints = new GridBagConstraints();
        contentConstraints.anchor = GridBagConstraints.WEST;
        contentConstraints.insets = new Insets(0, 5, 0, 5);
        contentConstraints.gridx = 0;
        contentConstraints.gridy = 0;

        Dimension minimumSize = new Dimension(600, y);
        contentPanel.setMinimumSize(minimumSize);
        contentPanel.setMaximumSize(minimumSize);
        contentPanel.setPreferredSize(minimumSize);

        updateTabs();

        contentPanel.add(menu);
        subPanel.add(contentPanel, BorderLayout.NORTH);
    }

    private void updateTabs(){
        inventory = player.getInventoryWindow().getPanel();
        upgrade = player.getUpStatsWindow().getPanel();
        equipment = player.getEquipmentWindow().getPanel();
        info = player.getPlayerInfoWindow().getPanel();
        abilities = player.getPlayerAbilityWindow().getPanel();
        quests = player.getPlayerQuestWindow().getPanel();
        disease = player.getDiseasesWindow().getPanel();
        List<JPanel> tabs = List.of(
                inventory,
                upgrade,
                equipment,
                info,
                abilities,
                quests,
                disease
        );
        List<String> tabNames = List.of(
                "Инвентарь",
                "Прокачка",
                "Экипировка",
                "Информация",
                "Умения",
                "Квесты",
                "Болезни"
        );

        if(menu.getComponents().length != 0){
            try{
                IntStream
                        .range(0, tabs.size())
                        .forEach(i -> menu.setComponentAt(i, tabs.get(i)));
            } catch (Exception ex){
                menu.removeAll();
                IntStream
                        .range(0, tabs.size())
                        .forEach(i -> menu.addTab(tabNames.get(i), tabs.get(i)));
            }
        } else {
            Dimension minimumSize = new Dimension(600, y);
            menu.setMinimumSize(minimumSize);
            menu.setMaximumSize(minimumSize);
            menu.setPreferredSize(minimumSize);

            IntStream
                    .range(0, tabs.size())
                    .forEach(i -> menu.addTab(tabNames.get(i), tabs.get(i)));
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

    public NPCController getNpcController() {
        return npcController;
    }

    @Override
    public Map getMap() {
        return currentMap;
    }

    @Override
    public void setMap(Map map) {
        currentMap = map;
    }

    @Override
    public WindowInterface getWindow() {
        return this;
    }

    //    private void control(GodCreature creature, GodCreatureButton button) {
//
//        boolean isStep = creature.getIsStep();
//        int X = creature.getX();
//        int Y = creature.getY();
//
//        boolean isLiveCreature = creature instanceof LiveCreature;
//        boolean isAlchemyThings = creature.getClass().toString().contains("AlchemyThings");
//        boolean isOre = creature instanceof Ore;
//        boolean isHealBlock = creature instanceof HealBlock;
//        boolean isDoorToUpperLevel = creature instanceof Door;
//        boolean isChest = creature instanceof Chest;
//        boolean isCraft = creature instanceof CraftTable;
//
//        if(!(creature instanceof Player) && creature instanceof LiveCreature){
//            npcController.addNPC((LiveCreature)creature);
//        }
//
//        if(player.hasAbility(new LittleFool()) && creature.getClass().toString().contains("Tree")){
//            isStep = true;
//        }
//        if (creature.getIsPlayer()){
//            button.addActionListener(e -> drawAllPlayerWindow());
//        } else if (isHealBlock){
//            button.addActionListener((ActionListener & Serializable)e -> {
//                player.setWindowInterface(FieldWindow.this);
//                currentMap.setElementByCoordinates(creature.getX(), creature.getY(), new Grass(creature.getX(), creature.getY()));
//                ((HealBlock) creature).heal(player);
//                int healBlockY = (int)(Math.random()*(currentMap.getMapHeight()-1));
//                int healBlockX = (int)(Math.random()*(currentMap.getMapWidth()-1));
//                currentMap.setElementByCoordinates(healBlockX, healBlockY, new HealBlock(healBlockX, healBlockY));
//                drawMap();
//                drawAllPlayerWindow();
//            });
//        } else if (isDoorToUpperLevel) {
//            button.addActionListener((ActionListener & Serializable)e -> {
//                //todo add function to the Door class to check the key
//                if(!((Door)creature).getIsLocked() || (((Door)creature).getIsLocked() && player.hasItem(((Door)creature).getKey()))){
//                    if(((Door)creature).getIsLocked()){
//                        player.removeItem(((Door)creature).getKey());
//                        ((Door)creature).setIsLocked(false);
//                    }
//                    currentMap.setPlayerX(player.getX());
//                    currentMap.setPlayerY(player.getY());
//                    ((Door)creature).setIn(currentMap);
//                    if(creature instanceof DoorToUpperLevelLocation){
//                        ((Door)creature).setGeneration(() -> {
//                            Map map;
//                            if(((Door)creature).getOut() == null){
//                                map = new Map();
//
//                                Dungeon dungeon = new Dungeon(player);
//                                GodCreature[][][] zxc = dungeon.getMap();
//                                map.setMapLowerObjects(zxc[0]);
//                                map.setMapUpperObjects(zxc[1]);
//                                map.setMapHeight();
//                                map.setMapWidth();
//                                map.setLocationName(dungeon.getLocationName());
//                                ((Door)creature).setOut(map);
//                                player.setX(dungeon.getPlayerXSafety());
//                                player.setY(dungeon.getPlayerYSafety());
//                                DoorToUpperLevelLocation door = new DoorToUpperLevelLocation();
//                                door.setOut(currentMap);
//                                door.setIsLocked(false);
//                                map.setElementByCoordinates(player.getX(), player.getY(), door);
//                            } else {
//                                map = ((Door)creature).getOut();
//                                player.setX(map.getPlayerX());
//                                player.setY(map.getPlayerY());
//                            }
//                            map.setPlayer(player);
//                            return map;
//                        });
//                    } else if(creature instanceof CaveDoor){
//                        ((Door)creature).setGeneration(() -> {
//                            Map map;
//                            if(((Door)creature).getOut() == null){
//                                map = new Map();
//                                Cave cave = new Cave();
//
//                                map.setMapLowerObjects(cave.getCave());
//                                map.setMapHeight();
//                                map.setMapWidth();
//                                ((Door)creature).setOut(map);
//
//                                player.setX(cave.getPlayerSafeX());
//                                player.setY(cave.getPlayerSafeY());
//
//                                CaveDoor door = new CaveDoor();
//                                door.setOut(currentMap);
//                                door.setIsLocked(false);
//                                map.setElementByCoordinates(player.getX(), player.getY(), door);
//                            } else {
//                                map = ((Door)creature).getOut();
//                                player.setX(map.getPlayerX());
//                                player.setY(map.getPlayerY());
//                            }
//                            map.setPlayer(player);
//                            return map;
//                        });
//                    }
//                    currentMap = ((Door) creature).generate();
//                    fieldPanel.setMap(currentMap);
//                    player.setWindowInterface(FieldWindow.this);
//                    drawMap();
//                }
//                drawAllPlayerWindow();
//            });
//        } else if(isCraft){
//            button.addActionListener((ActionListener & Serializable)e -> {
//                ((CraftTable) creature).setPlayer(player);
//                if(!((CraftTable) creature).getCraftTableWindowOpen()){
//                    ((CraftTable) creature).setCraftTableWindow(true);
//                    ((CraftTable) creature).setCraftTableWindowOpen(true);
//                } else {
//                    ((CraftTable) creature).setCraftTableWindow(false);
//                    ((CraftTable) creature).setCraftTableWindowOpen(false);
//                }
//            });
//        }else if (isChest) {
//            button.addActionListener((ActionListener & Serializable)e -> {
//                player.setWindowInterface(FieldWindow.this);
//                if(!((Chest) creature).getIsInventoryChestOpen()){
//                    ((Chest) creature).setPlayer(player);
//                    ((Chest) creature).setInventoryWindow();
//                    ((Chest) creature).setInventoryWindowChestIsVisible(true);
//                    ((Chest) creature).setInventoryChestOpen(true);
//                } else {
//                    ((Chest) creature).getInventoryWindow().close();
//                    ((Chest) creature).setInventoryWindowChestIsVisible(false);
//                    ((Chest) creature).setInventoryChestOpen(false);
//                }
//                drawAllPlayerWindow();
//            });
//        } else {
//            boolean finalIsStep = isStep;
//            button.addActionListener((ActionListener & Serializable) e -> {
//                if (finalIsStep) {
//                    player.setWindowInterface(FieldWindow.this);
//                    player.setX(X);
//                    player.setY(Y);
//                    if (isAlchemyThings) {
//                        player.addItemToInventory((Ingredient) ((IngredientThing) creature).getIngredient());
//                        GodCreature godCreature = (GodCreature) ((IngredientThing) creature).getParent();
//                        godCreature.setX(X);
//                        godCreature.setY(Y);
//                        currentMap.setElementByCoordinates(X, Y, godCreature);
//                    }
//                    if (isOre && player.hasItem(new Pickaxe())) {
//                        player.addItemToInventory((Resource) ((Ore) creature).getResource());
//                        GodCreature godCreature = new Stone();
//                        godCreature.setX(X);
//                        godCreature.setY(Y);
//                        currentMap.setElementByCoordinates(X, Y, godCreature);
//                    }
//                    drawMap();
//                } else if (isLiveCreature) {
//                    player.setWindowInterface(FieldWindow.this);
//                    if (((LiveCreature) creature).getTalkative()) {
//                        ((LiveCreature) creature).setConversationWindowPlayer(player);
//                        if (!((LiveCreature) creature).getIsConversationWindowOpen()) {
//                            ((LiveCreature) creature).setConversationWindowIsVisible(true);
//                            ((LiveCreature) creature).setConversationWindowOpen(true);
//                        } else {
//                            ((LiveCreature) creature).setConversationWindowIsVisible(false);
//                            ((LiveCreature) creature).setConversationWindowOpen(false);
//                        }
//                    } else {
//                        player.setWindowInterface(FieldWindow.this);
//                        if (((LiveCreature) creature).getHp() == 0) {
//                            ((LiveCreature) creature).countStatsAfterBorn();
//                        }
//                        if (creature.getChooseEnemyWindow() == null) {
//                            creature.setChooseEnemyWindow(player, FieldWindow.this, (LiveCreature) creature);
//                        }
//                        if (!creature.getIsChooseEnemyWindowOpen()) {
//                            creature.setChooseEnemyWindowIsVisible(true);
//                            creature.setChooseEnemyWindowOpen(true);
//                        } else {
//                            creature.setChooseEnemyWindowIsVisible(false);
//                            creature.setChooseEnemyWindowOpen(false);
//                        }
//                    }
//                }
//                drawAllPlayerWindow();
//            });
//        }
//    }
}
