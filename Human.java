package JGame;

import java.util.Scanner;

public class Human extends LiveCreature {
    protected String name = "JGame.Human";
    protected String location = "Пустота";

    Stats stats = new Stats();

    protected int upPointCount = 0;
    public int hp = 100;

    protected String race = "Человек";

    public Human(int x, int y, String name){



        stats.strength = 5;
        stats.speed = 5;
        stats.agility = 5;
        stats.intelligence = 5;
        stats.luck = 5;
        stats.eloquence = 5;
        stats.blacksmith = 5;
        stats.alchemy = 5;
        stats.one_handed_weapon = 5;
        stats.two_handed_weapon = 5;
        stats.pole_weapon = 5;
        stats.chopping_weapon = 5;
        stats.long_range_weapon = 5;

        stats.knowledge = 0;
        stats.energy = 0;

        stats.militarism = 0;
        stats.pacifism = 0;

        this.name = name;
        this.x = x;
        this.y = y;
        isStep = true;
    }

    public void getStatusPosition(){
        System.out.println("Позиция: x = "+x+", y = " + y);
    }

    public void getStatusLocation(){
        System.out.println("Локация: " + location);
    }

    public void addUpPoints(int count){
        upPointCount += count;
    }

    public void setName(String name){
        this.name = name;
    }
}
