package utils;

import window.Controllable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public abstract class KeyBinder {

    public static int ESCAPE = KeyEvent.VK_ESCAPE;
    public static int MAP = KeyEvent.VK_M;

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
