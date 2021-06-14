package Windows;

import utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Menu extends JPanel {

    private final int WIDTH = Constants.getFULLSCREEN().width;
    private final int HEIGHT = Constants.getFULLSCREEN().height;
    private final Dimension buttonSize = new Dimension(WIDTH / 4, HEIGHT / 20);
    private final Font font = new Font("Osaka", Font.PLAIN,HEIGHT / 40);

    public Menu() {
        Dimension fullscreen = Constants.getFULLSCREEN();
        setPreferredSize(fullscreen);
        setMinimumSize(fullscreen);
        setMaximumSize(fullscreen);
    }

    protected void printInterface(String title, JButton ... buttonList) {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel("<html><h1><strong><i>" + title + "</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        for (JButton button : buttonList) {
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setPreferredSize(buttonSize);
            button.setFont(font);
            buttons.add(button, gbc);
        }

        gbc.weighty = 1;
        add(buttons, gbc);
    }
}
