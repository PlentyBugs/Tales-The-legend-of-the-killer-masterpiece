package item;

import java.awt.Color;

public enum Grade {
    COMMON(new Color(0, 0, 0), "0"),
    MAGIC(new Color(128, 255, 80), "1"),
    CURSE(new Color(1, 155, 24), "2"),
    ARTIFACT(new Color(255, 0, 18), "3"),
    HEROIC(new Color(255, 96, 0), "4"),
    ABOVE_THE_GODS(new Color(255, 0, 197), "5");

    public final Color color;
    public final String grade;

    Grade(Color color, String grade) {
        this.color = color;
        this.grade = grade;
    }
}
