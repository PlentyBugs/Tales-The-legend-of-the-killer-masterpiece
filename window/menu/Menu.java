package window.menu;

import utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel {

    private static BufferedImage background;
    protected final int WIDTH = Constants.getFULLSCREEN().width;
    protected final int HEIGHT = Constants.getFULLSCREEN().height;
    protected final Dimension BUTTON_SIZE = new Dimension(WIDTH / 4, HEIGHT / 20);
    protected final Font FONT = new Font("Osaka", Font.PLAIN,HEIGHT / 40);

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

    protected void printInterface(String title, JButton ... buttonList) {
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
        for (JButton button : buttonList) {
            customizeButton(button);
            buttons.add(button, gbc);
        }

        gbc.weighty = 1;
        add(buttons, gbc);
    }

    protected void customizeButton(JButton button) {
        button.setMinimumSize(BUTTON_SIZE);
        button.setMaximumSize(BUTTON_SIZE);
        button.setPreferredSize(BUTTON_SIZE);
        button.setFont(FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(220, 137, 70, 255));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
    }
}
