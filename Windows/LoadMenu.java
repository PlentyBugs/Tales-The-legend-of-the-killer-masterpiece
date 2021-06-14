package Windows;

import javax.swing.*;

public class LoadMenu extends Menu {

    public LoadMenu(MultiWindow mainWindow) {
        super();
        JButton back = new JButton("Back");
        back.addActionListener(e -> mainWindow.switchScreen(Screen.MAIN_MENU));
        printInterface("Выберите слот загрузки", back);
    }
}
