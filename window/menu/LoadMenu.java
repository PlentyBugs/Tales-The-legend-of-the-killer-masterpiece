package window.menu;

import window.MultiWindow;
import window.Screen;
import window.player.UnfocusedButton;

public class LoadMenu extends Menu {

    public LoadMenu(MultiWindow mainWindow) {
        super();
        UnfocusedButton back = new UnfocusedButton("Back");
        back.addActionListener(e -> mainWindow.switchScreen(Screen.MAIN_MENU));
        printInterface("Выберите слот загрузки", back);
    }
}
