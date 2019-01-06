package JGame;

import java.io.IOException;
import java.util.Scanner;

public class Narrator {

    String difficulty = "";
    Window window;

    Narrator(String difficulty, Window window){
        this.difficulty = difficulty;
        this.window = window;
    }

    Scanner input = new Scanner(System.in);

    int skill_points_count;

    public void theBeginning(Player player) throws InterruptedException, IOException {

        if(difficulty.equals("easy")){
            skill_points_count = 20;
        } else if(difficulty.equals("normal")){
            skill_points_count = 15;
        } else if(difficulty.equals("hard")){
            skill_points_count = 10;
        } else if(difficulty.equals("very hard")){
            skill_points_count = 5;
        } else if(difficulty.equals("nightmare")){
            skill_points_count = 5;
        } else if(difficulty.equals("stop it")){
            skill_points_count = 0;
        }

        for (int i = 1; i < 4; i++) {
            monolog("...");
        }
        monolog("Темно");
        monolog("Вдали что-то видно");
        monolog("Свет, наверно, я могу назвать это так");
        monolog("Кто я и где я нахожусь?");
        monolog("Неизвестная личность начала передавать поток мыслей в вашу голову:");
        monolog("Скоро ты появишься в дивном мире, где ты сможешь достичь необычайных высот", "Неизвестный");
        monolog("Скажи мне своё имя, дитя:", "Неизвестный");
        monolog("Имя: ");

        ChooseNameWindow chooseNameWindow = new ChooseNameWindow();
        player.name = "";
        while (!chooseNameWindow.getCheck()){
            player.name = chooseNameWindow.getName();
        }
        chooseNameWindow.close();

        if (player.name.equals("Безымянный")){
            monolog("Ну и ну, решил остаться безымянным? Пусть так тебя и будут звать!", "Неизвестный");
        }

        if (skill_points_count > 0){
            monolog("Ладно, пожалуй нам стоит задать твой вектор развития, я дам тебе " + skill_points_count + " очков прокачки", "Неизвестный");
            monolog("Распредели их как следует:", "Неизвестный");
            player.upPointCount = skill_points_count;
            player.StatUp(input);
        } else {
            monolog("У тебя будет очень тяжелая судьба", "Неизвестный");
        }
        monolog("В итоге вот твои характеристики", "Неизвестный");
        player.getStatusStats();
    }

    private void monolog(String text) throws InterruptedException{
        window.writeToConsole(text);
    }
    private void monolog(String text, String dialog_name) throws InterruptedException{
        monolog(dialog_name + ": " + text);
    }
}
