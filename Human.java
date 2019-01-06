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
    }
    public void Status(){
        getStatusPosition();
        getStatusLocation();
        getStatusStats();
    }

    public void getStatusPosition(){
        System.out.println("Позиция: x = "+x+", y = " + y);
    }

    public void getStatusLocation(){
        System.out.println("Локация: " + location);
    }

    public void getStatusStats(){
        System.out.println("Статы:");
        System.out.println("\tСила: " + stats.strength);
        System.out.println("\tЛовкость: " + stats.agility);
        System.out.println("\tСкорость: " + stats.speed);
        System.out.println("\tИнтеллект: " + stats.intelligence);
        System.out.println("\tУдача: " + stats.luck);
        System.out.println("\tКрасноречие: " + stats.eloquence);
        System.out.println("\tКузнечное дело: " + stats.blacksmith);
        System.out.println("\tАлхимия: " + stats.alchemy);
        System.out.println("\tОдноручное оружие: " + stats.one_handed_weapon);
        System.out.println("\tДвуручное оружие: " + stats.two_handed_weapon);
        System.out.println("\tДревковое оружие: " + stats.pole_weapon);
        System.out.println("\tРубящее оружие: " + stats.chopping_weapon);
        System.out.println("\tДальнобойное оружие: " + stats.long_range_weapon);
    }

    public void addUpPoints(int count){
        upPointCount += count;
    }

    public void StatUp(Scanner in){
        System.out.println("Что бы вы хотели прокачать?(0,1,2,3,4,5,6,7,8,9,10,11,12,13)");
        System.out.println("0: Выход");
        System.out.println("1: Сила добавляет урон, хп, защиту");
        System.out.println("2: Скорость определяет то, кто будет бить раньше, а также влияет на некоторые другие вещи");
        System.out.println("3: Ловкость влияет на урон у дальнобойных орудий, а также позволяет уклоняться, наносить критические удары и т.п.");
        System.out.println("4: Интеллект влияет на магию, а также открывает множество способностей, которые будут сильно помогать персонажу");
        System.out.println("5: Удача будет влиять почти на всё, что будет касаться вас");
        System.out.println("6: Красноречие поможет решать проблемы без использования грубой силы, а также поможет при общении");
        System.out.println("7: Кузнечное дело откроет возможности создания оружия, брони, а также различных инструментов, драгоценностей и предметов декора");
        System.out.println("8: Алхимия откроет перед вами двери в мир зелий, которые окажут вам помощь по мере прохождения игры");
        System.out.println("9: Одноручное оружие будет улучшать все, связанное с оружием, которые вы держите в одной руке");
        System.out.println("10: Двуручное оружие будет улучшать все, связанное с оружием, которые вы держите в двух руках");
        System.out.println("11: Дрековое оружие будет улучшать древковое оружие");
        System.out.println("12: Рубящее оружие будет улучшать все оружие, которое может рубить");
        System.out.println("13: Дальний бой поможет вам расправляться с врагами на расстоянии, не подвергая вас опасности");
        String choice;
        while (upPointCount > 0){
            System.out.println("\nВыбор(Осталось очков прокачки: " + Integer.toString(upPointCount) + "):");
            // Coming soon
            stats.upStat(StatsEnum.STRENGTH);
        }
    }
    public void setName(String name){
        this.name = name;
    }
}
