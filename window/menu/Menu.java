package window.menu;

import utils.Constants;
import window.player.UnfocusedButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends AbstractMenu {

    private static BufferedImage background;

    public Menu() {
        Dimension fullscreen = Constants.getFULLSCREEN();
        setPreferredSize(fullscreen);
        setMinimumSize(fullscreen);
        setMaximumSize(fullscreen);
        try {
            background = ImageIO.read(new File("./texture/img/mainMenu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void printInterface(String title, UnfocusedButton ... buttonList) {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        JLabel titleLabel = new JLabel("<html><h1><strong><i>" + title + "</i></strong></h1><hr></html>");
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        for (UnfocusedButton button : buttonList) {
            customizeButton(button);
            buttons.add(button, gbc);
        }

        gbc.weighty = 1;
        add(buttons, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
    }
}
