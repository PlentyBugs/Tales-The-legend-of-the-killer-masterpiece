package Windows;

import Abilities.Passive.LittleFool;
import Creatures.GodCreature;
import Creatures.LiveCreature;
import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Locations.Map;
import Things.AlchemyThings.IngredientThing;
import Things.ChestLike.Chest;
import Things.Craft.CraftTable;
import Things.Grass;
import Things.HealBlock;
import Windows.SupportWindows.SupportComponents.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class FieldWindow extends JFrame implements Serializable, KeyListener {
    private int x, y;
    private int counter = -1;
    private int realVision;
    private int vision;
    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints mainConstraints = new GridBagConstraints();
    private JPanel contentPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints contentConstraints = new GridBagConstraints();
    private Component selectedTab;
    private JTabbedPane menu = new JTabbedPane();;
    private JPanel inventory;
    private JPanel upgrade;
    private JPanel equipment;
    private JPanel info;
    private JPanel abilities;
    private JPanel quests;
    private JPanel save;
    private Map currentMap;
    private static final long serialVersionUID = -5963455665311017981L;

    private Console console;

    public FieldWindow(String name, int vision, Player player, GodCreature[][] information, Map map) {
        super(name);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.player = player;

        inventory = player.getInventoryWindow().getPanel();
        upgrade = player.getUpStatsWindow().getPanel();
        equipment = player.getEquipmentWindow().getPanel();
        info = player.getPlayerInfoWindow().getPanel();
        abilities = player.getPlayerAbilityWindow().getPanel();
        quests = player.getPlayerQuestWindow().getPanel();
        save = new JPanel();

        console = new Console();

        this.vision = vision;
        x = 1024;
        y = 720;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        console.setSizeArea((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-40, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/6.5));

        currentMap = map;

        drawMap();
    }

    public void drawField(GodCreature[][] information){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        realVision = player.getVision()*2+1;
        int width = (int)(x*0.95)/realVision;
        int height = (int)(y*0.7)/realVision;

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2, 2, 2, 2);

        for (int i = 0; i < realVision; i++){
            for (int j = 0; j < realVision; j++) {
                constraints.gridx = j;
                constraints.gridy = i;
                boolean isStep = information[i][j].getIsStep();
                int X = information[i][j].getX();
                int Y = information[i][j].getY();
                String info = information[i][j].getName();
                JButton button = new JButton(info);
                button.setBackground(information[i][j].getColor());
                button.setPreferredSize(new Dimension(width, height));
                button.setLocation((width+5)*j + 8,(height+5)*i + 5);
                button.setFont(new Font("TimesRoman", Font.BOLD, (int)(80/Math.pow(realVision, 0.9))));

                button.setPreferredSize(new Dimension(x/realVision,(int)(0.7*y/realVision)));
                button.setMinimumSize(new Dimension(x/realVision,(int)(0.7*y/realVision)));
                button.setMaximumSize(new Dimension(x/realVision,(int)(0.7*y/realVision)));

                boolean isLiveCreature = information[i][j].getClass().toString().contains("Creatures");
                boolean isAlchemyThings = information[i][j].getClass().toString().contains("AlchemyThings");
                boolean isHealBlock = information[i][j].getClass().toString().contains("HealBlock");
                boolean isDoorToUpperLevel = information[i][j].getClass().toString().contains("DoorToUpperLevelLocation");
                boolean isChest = information[i][j].getClass().toString().contains("Chest");
                boolean isCraft = information[i][j].getClass().toString().contains("Craft");

                GodCreature liveCreature = information[i][j];
                if(player.hasAbility(new LittleFool()) && liveCreature.getClass().toString().contains("Tree")){
                    isStep = true;
                }
                if (information[i][j].getIsPlayer()){
                    button.addActionListener(e -> drawAllPlayerWindow());
                } else if (isHealBlock){
                    button.addActionListener(e -> {
                        drawAllPlayerWindow();
                        player.setFieldWindow(FieldWindow.this);
                        currentMap.setElementByCoordinates(liveCreature.getX(), liveCreature.getY(), new Grass(liveCreature.getX(), liveCreature.getY()));
                        ((HealBlock)liveCreature).heal(player);
                        int healBlockY = (int)(Math.random()*(currentMap.getMapHeight()-1));
                        int healBlockX = (int)(Math.random()*(currentMap.getMapWidth()-1));
                        currentMap.setElementByCoordinates(healBlockX, healBlockY, new HealBlock(healBlockX, healBlockY));
                        drawMap();
                    });
                } else if (isDoorToUpperLevel) {
                    button.addActionListener(e -> {
                        drawAllPlayerWindow();
                        player.setFieldWindow(FieldWindow.this);
                        currentMap = new Map(player, player.getLvl()*10, player.getLvl()*10);
                        player.setX(0);
                        player.setY(0);
                        drawMap();
                    });
                } else if(isCraft){
                    button.addActionListener(e -> {
                        ((CraftTable)liveCreature).setPlayer(player);
                        if(!((CraftTable)liveCreature).getCraftTableWindowOpen()){
                            ((CraftTable)liveCreature).setCraftTableWindow(true);
                            ((CraftTable)liveCreature).setCraftTableWindowOpen(true);
                        } else {
                            ((CraftTable)liveCreature).setCraftTableWindow(false);
                            ((CraftTable)liveCreature).setCraftTableWindowOpen(false);
                        }
                    });
                }else if (isChest) {
                    button.addActionListener(e -> {
                        drawAllPlayerWindow();
                        player.setFieldWindow(FieldWindow.this);
                        if(!((Chest)liveCreature).getIsInventoryChestOpen()){
                            ((Chest)liveCreature).setPlayer(player);
                            ((Chest)liveCreature).setInventoryWindow();
                            ((Chest)liveCreature).setInventoryWindowChestIsVisible(true);
                            ((Chest)liveCreature).setInventoryChestOpen(true);
                        } else {
                            ((Chest)liveCreature).getInventoryWindow().close();
                            ((Chest)liveCreature).setInventoryWindowChestIsVisible(false);
                            ((Chest)liveCreature).setInventoryChestOpen(false);
                        }
                    });
                } else {
                    int finalI = i;
                    int finalJ = j;
                    boolean finalIsStep = isStep;
                    button.addActionListener(e -> {
                        drawAllPlayerWindow();
                        if (finalIsStep){
                            player.setFieldWindow(FieldWindow.this);
                            player.setX(X);
                            player.setY(Y);
                            if(isAlchemyThings){
                                player.addItemToInventory((Ingredient)((IngredientThing)information[finalI][finalJ]).getIngredient());
                                GodCreature godCreature = (GodCreature) ((IngredientThing)information[finalI][finalJ]).getParent();
                                godCreature.setX(X);
                                godCreature.setY(Y);
                                currentMap.setElementByCoordinates(X, Y, godCreature);
                            }
                            drawMap();
                        } else if (isLiveCreature){
                            player.setFieldWindow(FieldWindow.this);
                            if (((LiveCreature)liveCreature).getTalkative()){
                                ((LiveCreature)liveCreature).setConversationWindowPlayer(player);
                                if(!((LiveCreature)liveCreature).getIsConversationWindowOpen()){
                                    ((LiveCreature)liveCreature).setConversationWindowIsVisible(true);
                                    ((LiveCreature)liveCreature).setConversationWindowOpen(true);
                                } else {
                                    ((LiveCreature)liveCreature).setConversationWindowIsVisible(false);
                                    ((LiveCreature)liveCreature).setConversationWindowOpen(false);
                                }
                            } else {
                                player.setFieldWindow(FieldWindow.this);
                                if(((LiveCreature)liveCreature).getHp() == 0){
                                    ((LiveCreature)liveCreature).countStatsAfterBorn();
                                }
                                if (liveCreature.getChooseEnemyWindow() == null){
                                    liveCreature.setChooseEnemyWindow(player, FieldWindow.this, (LiveCreature) liveCreature);
                                }
                                if(!liveCreature.getIsChooseEnemyWindowOpen()){
                                    liveCreature.setChooseEnemyWindowIsVisible(true);
                                    liveCreature.setChooseEnemyWindowOpen(true);
                                } else {
                                    liveCreature.setChooseEnemyWindowIsVisible(false);
                                    liveCreature.setChooseEnemyWindowOpen(false);
                                }
                            }
                        }
                    });
                }
                panel.setFocusable(true);
                panel.add(button, constraints);
            }
        }
    }

    public void writeToConsole(String text) throws InterruptedException {
        console.writeToConsole(text);
    }

    public void drawMap(){
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
        realVision = player.getVision()*2+1;
    }

    private void fillContentPanel(){
        mainPanel.remove(contentPanel);

        contentPanel = new JPanel(new GridBagLayout());
        contentConstraints = new GridBagConstraints();
        contentConstraints.anchor = GridBagConstraints.WEST;
        contentConstraints.insets = new Insets(0, 5, 0, 5);
        contentConstraints.gridx = 0;
        contentConstraints.gridy = 0;

        contentPanel.setMinimumSize(new Dimension(x - (int)(x/realVision)-380, y));
        contentPanel.setMaximumSize(new Dimension(x - (int)(x/realVision)-380, y));
        contentPanel.setPreferredSize(new Dimension(x - (int)(x/realVision)-380, y));

        updateTabs();

        contentPanel.add(menu);
        mainConstraints.gridx = 1;
        mainPanel.add(contentPanel, mainConstraints);
    }

    private void updateTabs(){
        int i;
        for(i = 0; i < menu.getComponents().length; i++){
            if(menu.getSelectedComponent() == menu.getComponent(i)){
                break;
            }
        }
        menu.removeAll();

        menu.setMinimumSize(new Dimension(x - (int)(x/realVision)-404, y));
        menu.setMaximumSize(new Dimension(x - (int)(x/realVision)-404, y));
        menu.setPreferredSize(new Dimension(x - (int)(x/realVision)-404, y));

        inventory = player.getInventoryWindow().getPanel();
        upgrade = player.getUpStatsWindow().getPanel();
        equipment = player.getEquipmentWindow().getPanel();
        info = player.getPlayerInfoWindow().getPanel();
        abilities = player.getPlayerAbilityWindow().getPanel();
        quests = player.getPlayerQuestWindow().getPanel();
        save = player.getSavePanel().getPanel();

        menu.addTab("Инвентарь", inventory);
        menu.setMnemonicAt(0, KeyEvent.VK_I);
        menu.addTab("Прокачка", upgrade);
        menu.setMnemonicAt(1, KeyEvent.VK_C);
        menu.addTab("Экипировка", equipment);
        menu.setMnemonicAt(2, KeyEvent.VK_E);
        menu.addTab("Информация", info);
        menu.setMnemonicAt(3, KeyEvent.VK_O);
        menu.addTab("Умения", abilities);
        menu.setMnemonicAt(4, KeyEvent.VK_K);
        menu.addTab("Квесты", quests);
        menu.setMnemonicAt(5, KeyEvent.VK_Q);
        menu.addTab("Сохранить", save);
        menu.setMnemonicAt(6, KeyEvent.VK_F5);
        menu.setSelectedComponent(menu.getComponentAt(i));

    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
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
        player.getSavePanel().drawWindow();
    }

    public void keyPressed(KeyEvent event){
        if(event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A){
            System.out.println(currentMap.getMap(player.getX(),player.getY())[3][2].getClass());
        } else if(event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D){
            System.out.println(currentMap.getMap(player.getX(),player.getY())[3][4].getClass());
        } else if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W){
            System.out.println(currentMap.getMap(player.getX(),player.getY())[2][3].getClass());
        } else if(event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_S){
            System.out.println(currentMap.getMap(player.getX(),player.getY())[4][3].getClass());
        }
    }

    public void keyReleased(KeyEvent event){}

    public void keyTyped(KeyEvent event){}
}
