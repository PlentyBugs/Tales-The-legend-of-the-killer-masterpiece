package Windows;

import AI.NPCController;
import Creatures.Player;
import Locations.Map;
import Windows.BattleWindows.LossWindow;
import Windows.SupportWindows.SupportComponents.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
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
    public void drawMap() {

//        npcController.clear();
//        npcController.setWindowInterface(this);
//        player.removeBrokenItems();
//        setVisible(!player.getInFight());
        player.countPassiveBuffs();
        player.checkQuests();
        player.countEquipmentBuffs();
        if(player.getHp() <= 0){
            setVisible(false);
            new LossWindow();
        }
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
        fieldPanel.setMap(map);
    }

    @Override
    public WindowInterface getWindow() {
        return this;
    }
}
