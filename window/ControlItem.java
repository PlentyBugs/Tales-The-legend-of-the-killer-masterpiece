package window;

import creature.GodCreature;

public interface ControlItem {
    GodCreature control(
            int x, int y, int width, int height,
            GodCreature[][] lower,
            GodCreature[][] upper
    );
}
