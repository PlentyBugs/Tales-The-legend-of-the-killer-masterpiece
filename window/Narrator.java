package window;

import abilities.auras.Vision;
import abilities.passive.professions.Steal;
import creature.Difficulty;
import creature.Player;
import window.support.ChooseNameWindow;

import java.util.Scanner;

public class Narrator {

    private final Difficulty difficulty;
    private final GameWindow gameWindow;

    public Narrator(Difficulty difficulty, GameWindow gameWindow){
        this.difficulty = difficulty;
        this.gameWindow = gameWindow;
    }

    Scanner input = new Scanner(System.in);

    int skill_points_count;

    public void theBeginning(Player player) {

        switch (difficulty) {
            case EASY -> skill_points_count = 50;
            case NORMAL -> skill_points_count = 45;
            case HARD -> skill_points_count = 20;
            case VERY_HARD -> skill_points_count = 15;
            case NIGHTMARE -> skill_points_count = 5;
            case STOP_IT -> skill_points_count = 0;
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
            player.setLevelPoints(50);
            player.setUpPointCount(6000);
            player.setName("cheater");
            player.setHp(20000000);
            player.setMaxHp(20000000);
        }

        if(player.getName().equals("IMN0TaDR1Nk3RiMAnA1c43m1st")){
            player.setLevelPoints(30);
            player.getStats().setStrength(1);
            player.getStats().setSpeed(0);
            player.getStats().setAgility(0);
            player.getStats().setIntelligence(0);
            player.getStats().setLuck(0);
            player.getStats().setEloquence(0);
            player.getStats().setBlacksmith(0);
            player.getStats().setAlchemy(250);
            player.getStats().setOneHandedWeapon(0);
            player.getStats().setTwoHandedWeapon(0);
            player.getStats().setPoleWeapon(0);
            player.getStats().setChoppingWeapon(0);
            player.getStats().setLongRangeWeapon(0);
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
            player.getStats().setOneHandedWeapon(0);
            player.getStats().setTwoHandedWeapon(0);
            player.getStats().setPoleWeapon(0);
            player.getStats().setChoppingWeapon(0);
            player.getStats().setLongRangeWeapon(0);

            player.setLevelPoints(10);
            player.setUpPointCount(10);
            player.setName("cheater");
            player.setHp(5000);
            player.setMaxHp(100);
        }

        if(player.getName().equals("WenToThER0CkinGCha1r")){

            player.getStats().setStrength(200);
            player.getStats().setTwoHandedWeapon(200);

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

            player.getStats().setLongRangeWeapon(150);
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
                gameWindow.getConsole().getConsole("2efghsd6fbuh3bsfud5sbafu4ysadbdvabsfyuob1ds4518dv1a46v1ds1v6as").append("");
            }
        } else {
            monolog("У тебя будет очень тяжелая судьба", "Неизвестный");
        }
        monolog("В итоге вот твои характеристики", "Неизвестный");
        player.getStatusStats();
    }

    private void monolog(String text) {
        gameWindow.writeToConsole(text);
    }

    private void monolog(String text, String dialogName){
        monolog(dialogName + ": " + text);
    }
}
