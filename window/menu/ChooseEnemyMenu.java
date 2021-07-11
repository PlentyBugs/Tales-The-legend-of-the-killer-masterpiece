package window.menu;

import window.MultiWindow;
import window.Screen;
import window.battle.ChooseEnemyWindow;

public class ChooseEnemyMenu extends Menu {
    private final MultiWindow multiWindow;

    public ChooseEnemyMenu(MultiWindow multiWindow) {
        super();
        this.multiWindow = multiWindow;
    }

    public void setCmw(ChooseEnemyWindow cmw) {
        removeAll();
        add(cmw);
    }

    protected void close(Screen screen) {}
}
