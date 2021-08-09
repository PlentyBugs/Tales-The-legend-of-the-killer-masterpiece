package utils;

import creature.Player;
import window.Controllable;
import window.PlayerMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public abstract class KeyBinder {

    public static int ESCAPE = KeyEvent.VK_ESCAPE;
    public static int MAP = KeyEvent.VK_M;
    public static int INVENTORY = KeyEvent.VK_I;
    public static int UPGRADE = KeyEvent.VK_U;
    public static int EQUIPMENT = KeyEvent.VK_E;
    public static int INFO = KeyEvent.VK_J;
    public static int SKILLS = KeyEvent.VK_K;
    public static int QUESTS = KeyEvent.VK_Q;
    public static int DISEASES = KeyEvent.VK_L;

    public static void bindEscape(Controllable controllable, Runnable close) {
        KeyStroke escape = KeyStroke.getKeyStroke(KeyBinder.ESCAPE, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape,   "escape");
        controllable.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close.run();
            }
        });
    }

    public static void bindPlayerMenu(Controllable controllable, Player player, PlayerMenu playerMenu) {
        KeyStroke inventory = KeyStroke.getKeyStroke(INVENTORY, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(inventory,   "inventory");
        controllable.getActionMap().put("inventory", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMenu.setCurrentTab(player.getInventoryWindow());
                playerMenu.drawCurrentTab();
            }
        });
        KeyStroke upgrade = KeyStroke.getKeyStroke(UPGRADE, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upgrade,   "upgrade");
        controllable.getActionMap().put("upgrade", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMenu.setCurrentTab(player.getUpStatsWindow());
                playerMenu.drawCurrentTab();
            }
        });
        KeyStroke equipment = KeyStroke.getKeyStroke(EQUIPMENT, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(equipment,   "equipment");
        controllable.getActionMap().put("equipment", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMenu.setCurrentTab(player.getEquipmentWindow());
                playerMenu.drawCurrentTab();
            }
        });
        KeyStroke info = KeyStroke.getKeyStroke(INFO, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(info,   "info");
        controllable.getActionMap().put("info", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMenu.setCurrentTab(player.getPlayerInfoWindow());
                playerMenu.drawCurrentTab();
            }
        });
        KeyStroke skills = KeyStroke.getKeyStroke(SKILLS, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(skills,   "skills");
        controllable.getActionMap().put("skills", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMenu.setCurrentTab(player.getPlayerAbilityWindow());
                playerMenu.drawCurrentTab();
            }
        });
        KeyStroke quests = KeyStroke.getKeyStroke(QUESTS, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(quests,   "quests");
        controllable.getActionMap().put("quests", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMenu.setCurrentTab(player.getPlayerQuestWindow());
                playerMenu.drawCurrentTab();
            }
        });
        KeyStroke diseases = KeyStroke.getKeyStroke(DISEASES, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(diseases,   "diseases");
        controllable.getActionMap().put("diseases", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMenu.setCurrentTab(player.getDiseasesWindow());
                playerMenu.drawCurrentTab();
            }
        });
    }

    public static void bindKey(int keyCode, Controllable controllable, Runnable action) {
        KeyStroke key = KeyStroke.getKeyStroke(keyCode, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, "map");
        controllable.getActionMap().put("map", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }
}
