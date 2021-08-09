package window;

import creature.GodCreature;
import creature.Player;
import location.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class FieldPanel extends JPanel {

    private final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private Map map;

    public FieldPanel(Map map) {
        this.map = map;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Player player = map.getPlayer();
        int realVision = player.getVision() * 2 + 1;
        int x = player.getX();
        int y = player.getY();
        GodCreature[][] localMap = map.getMap(x, y);

        player.removeBrokenItems();

        for (int i = 0; i < realVision; i++){
            for (int j = 0; j < realVision; j++) {
                GodCreature godCreature = localMap[i][j];
                Color fillColor = godCreature.getColor();
                boolean isBounded = godCreature.isBounded();

                double size = SCREEN_HEIGHT / realVision;
                double xx = size * j;
                double yy = size * i;

                Rectangle2D.Double field = new Rectangle2D.Double(
                        xx,
                        yy,
                        size,
                        size
                );

                g2.setColor(fillColor);

                g2.fill(field);
                if (isBounded) {
                    g2.setColor(Color.black);
                    g2.draw(field);
                }

                if (realVision < 20) {
                    g2.setColor(Color.black);
                    if(fillColor.getRed() < 70 && fillColor.getBlue() < 70 && fillColor.getGreen() < 70){
                        Color foreground = new Color(255 - fillColor.getRed(), 255 - fillColor.getGreen(), 255 - fillColor.getBlue());
                        g2.setColor(foreground);
                    }
                    Font font = new Font("TimesRoman", Font.BOLD, (int) (80 / Math.pow(realVision, 0.9)));
                    FontMetrics metrics = g.getFontMetrics(font);
                    String name = godCreature.getName();
                    int stringWidth = metrics.stringWidth(name);
                    while (stringWidth > size && name.contains(" ")) {
                        name = name.replaceFirst(" ", "\n");
                        stringWidth = metrics.stringWidth(name);
                    }
                    g2.setFont(font);
                    String[] split = name.split("\n");
                    int length = split.length;
                    int currStringHeight = metrics.getHeight() * (length / 2 + 1);
                    for (String line : split) {
                        int xxx = (int) ((field.x) + (size - metrics.stringWidth(line)) / 2);
                        int yyy = (int) ((field.y) + (size - currStringHeight) / 2 + metrics.getAscent());
                        currStringHeight -= metrics.getHeight() + metrics.getAscent();
                        g.drawString(line, xxx, yyy);
                    }
                }
            }
        }
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
