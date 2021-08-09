package window;

import creature.GodCreature;
import location.Cell;

public interface ControlItem {
    GodCreature control(int x, int y, int width, int height, Cell[][] cells);
}
