package window.menu;

import window.MultiWindow;
import window.Screen;
import window.player.UnfocusedButton;

public class LossMenu extends Menu {

    public LossMenu(MultiWindow multiWindow) {
        super();
        UnfocusedButton returnToMM = new UnfocusedButton("Главное меню");
        returnToMM.addActionListener(e -> {
            multiWindow.switchScreen(Screen.MAIN_MENU);
            multiWindow.gameOver();
        });
        printInterface("Вы проиграли!", returnToMM);
    }

    protected void close(Screen screen) {}
}
