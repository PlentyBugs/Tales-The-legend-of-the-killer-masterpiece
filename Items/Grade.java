package Items;

import java.awt.Color;

public enum Grade {
    COMMON(new Color(0, 0, 0)),
    MAGIC(new Color(128, 255, 80)),
    CURSE(new Color(1, 155, 24)),
    ARTIFACT(new Color(255, 0, 18)),
    HEROIC(new Color(255, 96, 0)),
    ABOVE_THE_GODS(new Color(255, 0, 197));

    public final Color color;

    Grade(Color color) {
        this.color = color;
    }
}
