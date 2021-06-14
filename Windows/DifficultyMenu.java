package Windows;

import Creatures.Difficulty;
import Windows.PlayerWindows.UnfocusedButton;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DifficultyMenu extends Menu {

    public DifficultyMenu(MultiWindow mainWindow) {
        super();

        JButton back = new JButton("← Back");
        back.addActionListener(e -> mainWindow.switchScreen(Screen.MAIN_MENU));
        List<JButton> buttonList = new ArrayList<>();
        Difficulty[] difficulties = Difficulty.values();
        for (Difficulty difficulty : difficulties) {
            JButton button = new UnfocusedButton(difficulty.name());
            button.addActionListener(e -> {
                mainWindow.newGame(difficulty);
                mainWindow.switchScreen(Screen.GAME);
            });
            buttonList.add(button);
        }
        buttonList.add(back);
        printInterface("Выберите сложность", buttonList.toArray(new JButton[0]));
    }
}
