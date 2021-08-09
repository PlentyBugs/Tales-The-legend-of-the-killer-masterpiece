package item;

import java.awt.Color;

public enum Rarity {
    COMMON(new Color(255, 255, 255, 100), "+0"),
    UNCOMMON(new Color(0, 115, 255, 100), "+1"),
    RARE(new Color(12, 0, 255, 100), "+2"),
    MYSTICAL(new Color(255, 0, 119, 100), "+3"),
    LEGENDARY(new Color(255, 232, 0, 100), "+4"),
    DRAGON(new Color(255, 9, 0, 100), "+5"),
    DIVINE(new Color(255, 169, 0, 100), "+6");

    public final Color color;
    public final String rarity;

    Rarity(Color color, String rarity) {
        this.color = color;
        this.rarity = rarity;
    }
}
