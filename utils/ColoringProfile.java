package utils;

import Items.Item;

import java.awt.*;

public interface ColoringProfile {

    default Color getColorByGrade(Item item) {
        return switch (item.getGrade()) {
            case COMMON -> new Color(0, 0, 0);
            case MAGIC -> new Color(67, 162, 255);
            case CURSE -> new Color(1, 155, 24);
            case ARTIFACT -> new Color(255, 0, 18);
            case HEROIC -> new Color(255, 96, 0);
            case ABOVE_THE_GODS -> new Color(255, 0, 197);
        };
    }

    default Color getColorByRarity(Item item) {
        Color colorBackground;
        colorBackground = switch (item.getRarity()) {
            case COMMON -> new Color(255, 255, 255, 100);
            case UNCOMMON -> new Color(0, 115, 255, 100);
            case RARE -> new Color(12, 0, 255, 100);
            case MYSTICAL -> new Color(255, 0, 119, 100);
            case LEGENDARY -> new Color(255, 232, 0, 100);
            case DRAGON -> new Color(255, 9, 0, 100);
            case DIVINE -> new Color(255, 169, 0, 100);
        };
        return colorBackground;
    }
}
