import Abilities.Auras.Vision;
import LiveCreatures.Difficulty;
import LiveCreatures.Player;
import Windows.FieldWindow;
import Windows.SupportWindows.ChooseNameWindow;

import java.io.IOException;
import java.util.Scanner;

public class Narrator {

    Difficulty difficulty = Difficulty.STOPIT;
    FieldWindow fieldWindow;

    Narrator(Difficulty difficulty, FieldWindow fieldWindow){
        this.difficulty = difficulty;
        this.fieldWindow = fieldWindow;
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

        if(player.getName().equals("TesteratorGun1479")){
            player.setLevelpoints(50);
            player.setUpPointCount(60);
            player.setName("cheater");
            player.setHp(2000);
            player.setMaxHp(2000);
        }

        if(player.getName().equals("urMomGay512")){

            player.getStats().strength = 1;
            player.getStats().speed = 0;
            player.getStats().agility = 0;
            player.getStats().intelligence = 0;
            player.getStats().luck = 250;
            player.getStats().eloquence = 0;
            player.getStats().blacksmith = 0;
            player.getStats().alchemy = 0;
            player.getStats().one_handed_weapon = 0;
            player.getStats().two_handed_weapon = 0;
            player.getStats().pole_weapon = 0;
            player.getStats().chopping_weapon = 0;
            player.getStats().long_range_weapon = 0;

            player.setLevelpoints(10);
            player.setUpPointCount(10);
            player.setName("cheater");
            player.setHp(5000);
            player.setMaxHp(100);
        }

        if(player.getName().equals("WenToThER0CkinGCha1r")){

            player.getStats().strength = 200;
            player.getStats().two_handed_weapon = 200;

            player.setName("cheater");
            player.setHp(100);
            player.setMaxHp(100);
        }

        if(player.getName().equals("LiKeG0D")){

            player.getStats().strength = 10000;
            player.getStats().luck = 10000;

            player.setName("cheater");
            player.setHp(100);
            player.setMaxHp(100);
        }

        if(player.getName().equals("h0pelESSN3$S$33kУr")){

            player.getStats().long_range_weapon = 150;
            player.addAbility(new Vision(6));

            player.setName("cheater");
            player.setHp(500);
            player.setMaxHp(500);
        }

        if (skill_points_count > 0){
            monolog("Ладно, пожалуй нам стоит задать твой вектор развития, я дам тебе " + skill_points_count + " очков прокачки", "Неизвестный");
            monolog("Распредели их как следует:", "Неизвестный");
            player.addUpPoints(skill_points_count);
            player.setUpStatsWindowIsVisible(true);
            while (player.getUpPointCount() > 0){
                fieldWindow.getConsole().getConsole("2efghsd6fbuh3bsfud5sbafu4ysadbdvabsfyuob1ds4518dv1a46v1ds1v6as").append("");
            }
            player.setUpStatsWindowIsVisible(false);
        } else {
            monolog("У тебя будет очень тяжелая судьба", "Неизвестный");
        }
        monolog("В итоге вот твои характеристики", "Неизвестный");
        player.getStatusStats();
    }

    private void monolog(String text) throws InterruptedException{
        fieldWindow.writeToConsole(text);
    }
    private void monolog(String text, String dialog_name) throws InterruptedException{
        monolog(dialog_name + ": " + text);
    }
}
