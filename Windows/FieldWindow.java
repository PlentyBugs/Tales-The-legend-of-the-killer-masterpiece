package Windows;

import Creatures.GodCreature;
import Creatures.LiveCreature;
import Creatures.Player;
import Locations.Map;
import Things.Chest;
import Things.Grass;
import Things.HealBlock;
import Windows.SupportWindows.SupportComponents.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class FieldWindow extends JFrame implements Serializable {
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
    private JTabbedPane menu = new JTabbedPane();
    private Map currentMap;
    private static final long serialVersionUID = -5963455665311017981L;

    private Console console;

    public FieldWindow(String name, int vision, Player player, GodCreature[][] information, Map map) {
        super(name);

        this.player = player;

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

                boolean isLiveCreature = information[i][j].getClass().toString().split("\\.")[0].split(" ")[1].equals("Creatures");
                boolean isHealBlock = information[i][j].getClass().toString().split("\\.")[1].split(" ")[0].equals("HealBlock");
                boolean isDoorToUpperLevel = information[i][j].getClass().toString().split("\\.")[1].split(" ")[0].equals("DoorToUpperLevelLocation");
                boolean isChest = information[i][j].getClass().toString().split("\\.")[1].split(" ")[0].equals("Chest");

                GodCreature liveCreature = information[i][j];

                if (information[i][j].getIsPlayer()){
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // he he
                        }
                    });
                } else if (isHealBlock){
                    button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        player.setFieldWindow(FieldWindow.this);
                        currentMap.setElementByCoordinates(liveCreature.getX(), liveCreature.getY(), new Grass(liveCreature.getX(), liveCreature.getY()));
                        ((HealBlock)liveCreature).heal(player);
                        int healBlockY = (int)(Math.random()*(currentMap.getMapHeight()-1));
                        int healBlockX = (int)(Math.random()*(currentMap.getMapWidth()-1));
                        currentMap.setElementByCoordinates(healBlockX, healBlockY, new HealBlock(healBlockX, healBlockY));
                        drawMap();
                    }
                });
                } else if (isDoorToUpperLevel) {
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            player.setFieldWindow(FieldWindow.this);
                            currentMap = new Map(player, player.getLvl()*50, player.getLvl()*60);
                            player.setX(0);
                            player.setY(0);
                            drawMap();
                        }
                    });
                } else if (isChest) {
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
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
                        }
                    });
                } else {
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (isStep){
                                player.setFieldWindow(FieldWindow.this);
                                player.setX(X);
                                player.setY(Y);
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
                        }
                    });
                }

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


        selectedTab = menu.getSelectedComponent();
        menu = new JTabbedPane();

        menu.setMinimumSize(new Dimension(x - (int)(x/realVision)-380, y));
        menu.setMaximumSize(new Dimension(x - (int)(x/realVision)-380, y));
        menu.setPreferredSize(new Dimension(x - (int)(x/realVision)-380, y));
        updateTabs(menu);

        contentPanel.add(menu);
        mainConstraints.gridx = 1;
        mainPanel.add(contentPanel, mainConstraints);
    }

    private void updateTabs(JTabbedPane menu){
        menu.removeAll();

        JPanel inventory = player.getInventoryWindow().getPanel();
        JPanel upgrade = player.getUpStatsWindow().getPanel();
        JPanel equipment = player.getEquipmentWindow().getPanel();
        JPanel info = player.getPlayerInfoWindow().getPanel();
        JPanel abilities = player.getPlayerAbilityWindow().getPanel();
        JPanel quests = player.getPlayerQuestWindow().getPanel();
        JPanel save = new JPanel();

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
        if(selectedTab != null){
            try{
                menu.setSelectedComponent(selectedTab);
            }catch (IllegalArgumentException e){}
        }
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
}
