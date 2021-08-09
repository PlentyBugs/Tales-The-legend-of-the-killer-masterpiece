package window.menu;

import creature.GodCreature;
import creature.Player;
import location.Cell;
import location.Map;
import utils.KeyBinder;
import window.MultiWindow;
import window.Screen;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MapMenu extends AbstractMenu {
    private Map map;
    private int height;
    private int width;
    private final Player player;
    private final int MIN_SIZE = 8;
    private final double HEIGHT = super.HEIGHT;

    public MapMenu(MultiWindow multiWindow, Player player) {
        this.player = player;
        Runnable returnToGame = () -> multiWindow.switchScreen(Screen.GAME);
        KeyBinder.bindEscape(this, returnToGame);
        KeyBinder.bindKey(KeyBinder.MAP, this, returnToGame);
        setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cell[][] cells = map.getCells();
        Graphics2D g2 = (Graphics2D) g;
        double cellSize = Math.max(MIN_SIZE, HEIGHT / cells.length);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = cells[i][j];
                Color fillColor = Color.BLACK;
                if (cell.isVisited()) {
                    GodCreature upperObject = cell.getUpperObject();
                    if (upperObject != null) {
                        fillColor = upperObject.getColor();
                    } else {
                        fillColor = cell.getLowerObject().getColor();
                    }
                }
                colorCell(j, i, cellSize, fillColor, g2);
            }
        }

        int x = player.getX();
        int y = player.getY();
        int heightBound = height - 1;
        int widthBound = width - 1;

        Color color = player.getColor();

        colorCell(x, y, cellSize, color, g2);
        if (x > 0 && y > 0) {
            colorCell(x - 1, y - 1, cellSize, color, g2);
        }
        if (x > 0 && y < heightBound) {
            colorCell(x - 1, y + 1, cellSize, color, g2);
        }
        if (x < widthBound && y > 0) {
            colorCell(x + 1, y - 1, cellSize, color, g2);
        }
        if (x < widthBound && y < heightBound) {
            colorCell(x + 1, y + 1, cellSize, color, g2);
        }
    }

    private void colorCell(int x, int y, double cellSize, Color color, Graphics2D g2) {
        double xx = cellSize * x;
        double yy = cellSize * y;

        Rectangle2D.Double field = new Rectangle2D.Double(
                xx,
                yy,
                cellSize,
                cellSize
        );

        g2.setColor(color);

        g2.fill(field);
    }

    public void setMap(Map map) {
        this.map = map;;
        height = map.getMapHeight();
        width = map.getMapWidth();
    }
}
