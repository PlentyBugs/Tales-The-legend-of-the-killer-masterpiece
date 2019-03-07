import Abilities.Auras.Vision;
import Abilities.Passive.Professions.Steal;
import Creatures.Difficulty;
import Creatures.Player;
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
            player.setUpPointCount(6000);
            player.setName("cheater");
            player.setHp(20000000);
            player.setMaxHp(20000000);
        }

        if(player.getName().equals("IMN0TaDR1Nk3RiMAnA1c43m1st")){
            player.setLevelpoints(30);
            player.getStats().setStrength(1);
            player.getStats().setSpeed(0);
            player.getStats().setAgility(0);
            player.getStats().setIntelligence(0);
            player.getStats().setLuck(0);
            player.getStats().setEloquence(0);
            player.getStats().setBlacksmith(0);
            player.getStats().setAlchemy(250);
            player.getStats().setOne_handed_weapon(0);
            player.getStats().setTwo_handed_weapon(0);
            player.getStats().setPole_weapon(0);
            player.getStats().setChopping_weapon(0);
            player.getStats().setLong_range_weapon(0);
            player.setName("cheater");
            player.setHp(1);
            player.setMaxHp(1);
        }

        if(player.getName().equals("urMomGay512")){

            player.getStats().setStrength(1);
            player.getStats().setSpeed(0);
            player.getStats().setAgility(0);
            player.getStats().setIntelligence(0);
            player.getStats().setLuck(250);
            player.getStats().setEloquence(0);
            player.getStats().setBlacksmith(0);
            player.getStats().setAlchemy(0);
            player.getStats().setOne_handed_weapon(0);
            player.getStats().setTwo_handed_weapon(0);
            player.getStats().setPole_weapon(0);
            player.getStats().setChopping_weapon(0);
            player.getStats().setLong_range_weapon(0);

            player.setLevelpoints(10);
            player.setUpPointCount(10);
            player.setName("cheater");
            player.setHp(5000);
            player.setMaxHp(100);
        }

        if(player.getName().equals("WenToThER0CkinGCha1r")){

            player.getStats().setStrength(200);
            player.getStats().setTwo_handed_weapon(200);

            player.setName("cheater");
            player.setHp(100);
            player.setMaxHp(100);
        }

        if(player.getName().equals("LiKeG0D")){

            player.getStats().setStrength(10000);
            player.getStats().setLuck(10000);

            player.setName("cheater");
            player.setHp(100);
            player.setMaxHp(100);
        }

        if(player.getName().equals("h0pelESSN3$S$33kУr")){

            player.getStats().setLong_range_weapon(150);
            player.addAbility(new Vision(6));

            player.setName("cheater");
            player.setHp(500);
            player.setMaxHp(500);
        }

        if(player.getName().equals("Th3ftIsJust1f13d")){

            player.getStats().setTheft(200);
            player.addAbility(new Steal());

            player.setName("cheater");
            player.setHp(500);
            player.setMaxHp(500);
        }

        if (skill_points_count > 0){
            monolog("Ладно, пожалуй нам стоит задать твой вектор развития, я дам тебе " + skill_points_count + " очков прокачки", "Неизвестный");
            monolog("Распредели их как следует:", "Неизвестный");
            player.addUpPoints(skill_points_count);
            while (player.getUpPointCount() > 0){
                fieldWindow.getConsole().getConsole("2efghsd6fbuh3bsfud5sbafu4ysadbdvabsfyuob1ds4518dv1a46v1ds1v6as").append("");
            }
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
