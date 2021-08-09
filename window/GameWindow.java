package window;

import ai.NPCController;
import creature.Player;
import location.Map;
import utils.KeyBinder;
import window.battle.LossWindow;
import window.menu.AbstractMenu;
import window.menu.MapMenu;
import window.support.component.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serial;
import java.io.Serializable;

public class GameWindow extends AbstractMenu implements Serializable, WindowInterface {
    @Serial
    private static final long serialVersionUID = -5963455665311017981L;
    private final Player player;
    private final FieldPanel fieldPanel;
    private final JPanel subPanel = new JPanel(new BorderLayout());
    private final PlayerMenu menu;
    private Map currentMap;
    private final NPCController npcController;
    private final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private final MultiWindow multiWindow;
    private final MapMenu mapMenu;

    private final Console console;

    public GameWindow(Map map, MultiWindow multiWindow) {
        this.multiWindow = multiWindow;
        bindKeys();
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        setLayout(new BorderLayout());

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown( ComponentEvent e ) {
                GameWindow.this.requestFocusInWindow();
            }
        });

        this.player = map.getPlayer();
        fieldPanel = new FieldPanel(map);
        menu = new PlayerMenu(player);
        console = new Console();

        int subPanelWidth = WIDTH - HEIGHT;
        int consoleHeight = (int) (SCREEN_HEIGHT / 6.5);
        console.setSizeArea(subPanelWidth, consoleHeight);
        fieldPanel.setPreferredSize(new Dimension(SCREEN_HEIGHT, SCREEN_HEIGHT));

        Dimension subPanelSize = new Dimension(subPanelWidth, SCREEN_HEIGHT);
        subPanel.setSize(subPanelSize);
        subPanel.setPreferredSize(subPanelSize);
        subPanel.setMaximumSize(subPanelSize);
        subPanel.setMinimumSize(subPanelSize);
        subPanel.add(console, BorderLayout.SOUTH);

        currentMap = map;

        npcController = new NPCController();
        npcController.start();

        mapMenu = new MapMenu(multiWindow, player);
        mapMenu.setMap(map);
        multiWindow.newWindow(mapMenu, Screen.MAP);

        Dimension menuSize = new Dimension(subPanelWidth, SCREEN_HEIGHT - consoleHeight);
        menu.setMinimumSize(menuSize);
        menu.setMaximumSize(menuSize);
        menu.setPreferredSize(menuSize);
        menu.drawCurrentTab();

        subPanel.add(menu, BorderLayout.NORTH);

        KeyBinder.bindPlayerMenu(this, player, menu);

        drawMap();
    }

    public void writeToConsole(String text) {
        console.writeToConsole(text);
    }

    @Override
    public void drawMap() {
        if (!isVisible()) return;
//        npcController.clear();
//        npcController.setWindowInterface(this);
//        player.removeBrokenItems();
        player.countPassiveBuffs();
        player.checkQuests();
        player.countEquipmentBuffs();
        // todo: replace
        if(player.getHp() <= 0){
            setVisible(false);
            new LossWindow();
        }
        fieldPanel.updateUI();

        menu.drawCurrentTab();

        add(fieldPanel, BorderLayout.WEST);
        add(subPanel, BorderLayout.EAST);
    }

    public Console getConsole() {
        return console;
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
        mapMenu.setMap(map);
    }

    @Override
    public WindowInterface getWindow() {
        return this;
    }

    @Override
    public MultiWindow getMultiWindow() {
        return multiWindow;
    }

    public void returnKeyControl() {
        requestFocusInWindow();
    }
}
