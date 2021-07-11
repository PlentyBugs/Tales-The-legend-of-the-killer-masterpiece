package utils;

import window.Controllable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public abstract class KeyBinder {

    public static void bindEscape(Controllable controllable, Runnable close) {
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        controllable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape,   "escape");
        controllable.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close.run();
            }
        });
    }
}
