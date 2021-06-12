package Items;

import java.awt.Color;

public enum Rarity {
    COMMON(new Color(255, 255, 255, 100)),
    UNCOMMON(new Color(0, 115, 255, 100)),
    RARE(new Color(12, 0, 255, 100)),
    MYSTICAL(new Color(255, 0, 119, 100)),
    LEGENDARY(new Color(255, 232, 0, 100)),
    DRAGON(new Color(255, 9, 0, 100)),
    DIVINE(new Color(255, 169, 0, 100));

    public final Color color;

    Rarity(Color color) {
        this.color = color;
    }
}
