package window.player;

import javax.swing.*;
import java.awt.*;

public class UnfocusedButton extends JButton {
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    public UnfocusedButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        setFocusable(false);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {}

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    public void setSize(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }

    public void setSize(double width, double height) {
        setSize((int) (width), (int) (height));
    }
}
