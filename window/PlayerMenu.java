package window;

import creature.Player;
import utils.Pair;
import window.menu.AbstractMenu;
import window.player.PlayerPanel;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class PlayerMenu extends AbstractMenu {

    private PlayerPanel currentTab;
    private final JPanel panel;

    public PlayerMenu(Player player) {
        setLayout(new BorderLayout(0, 0));
        Box tabs = Box.createHorizontalBox();
        currentTab = player.getInventoryWindow();
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        final int playerMenuHeight = (int) (HEIGHT - HEIGHT / 6.5);
        final int width = WIDTH - HEIGHT;
        final int buttonWidth = width / 7;
        final int buttonHeight = HEIGHT / 40;
        Dimension actionPanelSize = new Dimension(width, playerMenuHeight - buttonHeight);
        panel.setPreferredSize(actionPanelSize);
        panel.setMaximumSize(actionPanelSize);
        panel.setMinimumSize(actionPanelSize);
        Stream.of(
                new Pair<String, PlayerPanel>("Inventory", player.getInventoryWindow()),
                new Pair<String, PlayerPanel>("Upgrade", player.getUpStatsWindow()),
                new Pair<String, PlayerPanel>("Equipment", player.getEquipmentWindow()),
                new Pair<String, PlayerPanel>("Info", player.getPlayerInfoWindow()),
                new Pair<String, PlayerPanel>("Skills", player.getPlayerAbilityWindow()),
                new Pair<String, PlayerPanel>("Quests", player.getPlayerQuestWindow()),
                new Pair<String, PlayerPanel>("Diseases", player.getDiseasesWindow())
        ).forEachOrdered(tab -> {
            UnfocusedButton tabButton = new UnfocusedButton(tab.first());
            customizeButton(tabButton);
            tabButton.setFont(FONT_SMALL);
            Dimension BUTTON_SIZE = new Dimension(buttonWidth, buttonHeight);
            tabButton.setMinimumSize(BUTTON_SIZE);
            tabButton.setMaximumSize(BUTTON_SIZE);
            tabButton.setPreferredSize(BUTTON_SIZE);
            tabButton.addActionListener(e -> {
                currentTab = tab.second();
                drawCurrentTab();
            });
            tabs.add(tabButton);
        });
        add(tabs, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void drawCurrentTab() {
        panel.removeAll();
        JPanel comp = currentTab.drawWindow();
        panel.add(comp);
        panel.revalidate();
        panel.repaint();
        revalidate();
        repaint();
    }

    public void setCurrentTab(PlayerPanel currentTab) {
        this.currentTab = currentTab;
    }
}
