package JGame;

import java.io.IOException;
import java.util.Scanner;

public class Narrator {

    Difficulty difficulty = Difficulty.STOPIT;
    Window window;

    Narrator(Difficulty difficulty, Window window){
        this.difficulty = difficulty;
        this.window = window;
    }

    Scanner input = new Scanner(System.in);

    int skill_points_count;

    public void theBeginning(Player player) throws InterruptedException, IOException {

        switch (difficulty){
            case EASY: skill_points_count = 20; break;
            case NORMAL: skill_points_count = 15; break;
            case HARD: skill_points_count = 10; break;
            case VERYHARD: skill_points_count = 5; break;
            case NIGHTMARE: skill_points_count = 5; break;
            case STOPIT: skill_points_count = 0; break;
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
        monolog("Назови себя, дитя:", "Неизвестный");
        monolog("Имя: ");

        ChooseNameWindow chooseNameWindow = new ChooseNameWindow();
        player.setName("");
        while (!chooseNameWindow.getCheck()){
            player.setName(chooseNameWindow.getName());
        }
        chooseNameWindow.close();

        if (player.getName().equals("Безымянный")){
            monolog("Ну и ну, решил остаться безымянным? Пусть так тебя и будут звать!", "Неизвестный");
        }

        if (skill_points_count > 0){
            monolog("Ладно, пожалуй нам стоит задать твой вектор развития, я дам тебе " + skill_points_count + " очков прокачки", "Неизвестный");
            monolog("Распредели их как следует:", "Неизвестный");
            player.upPointCount = skill_points_count;
            player.setUpStatsWindowIsVisible(true);
            while (player.upPointCount > 0){
                window.console.getConsole("2efghsd6fbuh3bsfud5sbafu4ysadbdvabsfyuob1ds4518dv1a46v1ds1v6as").append("");
            }
            player.setUpStatsWindowIsVisible(false);
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
