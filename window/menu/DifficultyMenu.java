package window.menu;

import creature.Difficulty;
import window.MultiWindow;
import window.Screen;
import window.player.UnfocusedButton;

import java.util.ArrayList;
import java.util.List;

public class DifficultyMenu extends Menu {

    public DifficultyMenu(MultiWindow mainWindow) {
        super();

        UnfocusedButton back = new UnfocusedButton("← Back");
        back.addActionListener(e -> mainWindow.switchScreen(Screen.MAIN_MENU));
        List<UnfocusedButton> buttonList = new ArrayList<>();
        Difficulty[] difficulties = Difficulty.values();
        for (Difficulty difficulty : difficulties) {
            UnfocusedButton button = new UnfocusedButton(difficulty.name());
            button.addActionListener(e -> {
                mainWindow.newGame(difficulty);
                mainWindow.switchScreen(Screen.GAME);
            });
            buttonList.add(button);
        }
        buttonList.add(back);
        printInterface("Выберите сложность", buttonList.toArray(new UnfocusedButton[0]));
    }
}
