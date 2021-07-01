package window.menu;

import utils.Constants;
import window.support.component.ScrollBar;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractMenu extends JPanel {
    protected final int WIDTH = Constants.getFULLSCREEN().width;
    protected final int HEIGHT = Constants.getFULLSCREEN().height;
    protected final Dimension BUTTON_SIZE = new Dimension(WIDTH / 4, HEIGHT / 20);
    protected final Font FONT_BIG = new Font("Osaka", Font.PLAIN,HEIGHT / 40);
    protected final Font FONT_MEDIUM = new Font("Osaka", Font.PLAIN,HEIGHT / 50);
    protected final Color STYLED_COLOR = new Color(220, 137, 70, 255);
    protected final Color STYLED_COLOR_LIGHT = new Color(255, 150, 77, 255);
    protected final Color STYLED_COLOR_DARK = new Color(220, 126, 24, 255);

    protected void customizeButton(JButton button) {
        button.setMinimumSize(BUTTON_SIZE);
        button.setMaximumSize(BUTTON_SIZE);
        button.setPreferredSize(BUTTON_SIZE);
        button.setFont(FONT_BIG);
        button.setForeground(Color.WHITE);
        button.setBackground(STYLED_COLOR);
    }

    protected void customizeScroll(JScrollPane scroll) {
        JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
        verticalScrollBar.setUI(new ScrollBar(STYLED_COLOR_DARK, STYLED_COLOR, STYLED_COLOR_LIGHT));
        verticalScrollBar.setPreferredSize(new Dimension(6, 0));
        verticalScrollBar.setBackground(STYLED_COLOR_LIGHT);
    }
}
