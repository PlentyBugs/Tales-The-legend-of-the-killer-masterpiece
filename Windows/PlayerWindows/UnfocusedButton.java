package Windows.PlayerWindows;

import javax.swing.*;

public class UnfocusedButton extends JButton {
    public UnfocusedButton(String text) {
        super(text);
        setFocusable(false);
    }
}
