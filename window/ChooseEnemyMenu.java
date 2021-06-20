package window;

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
}
