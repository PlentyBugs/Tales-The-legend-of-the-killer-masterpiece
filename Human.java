package JGame;

import java.util.Scanner;

public class Human extends LiveCreature {
    protected String name = "JGame.Human";
    protected String location = "Пустота";

    protected int strength = 5;
    protected int speed = 5;
    protected int agility = 5;
    protected int intelligence = 5;
    protected int luck = 5;
    protected int eloquence = 5;
    protected int blacksmith = 5;
    protected int alchemy = 5;
    protected int one_handed_weapon = 5;
    protected int two_handed_weapon = 5;
    protected int pole_weapon = 5;
    protected int chopping_weapon = 5;
    protected int long_range_weapon = 5;

    protected int knowledge = 0;
    protected int energy = 0;

    protected int militarism = 0;
    protected int pacifism = 0;

    protected int upPointCount = 0;
    public int hp = 100;

    protected String race = "Человек";

    public Human(int x, int y, String name){
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
        System.out.println("\tСила: " + strength);
        System.out.println("\tЛовкость: " + agility);
        System.out.println("\tСкорость: " + speed);
        System.out.println("\tИнтеллект: " + intelligence);
        System.out.println("\tУдача: " + luck);
        System.out.println("\tКрасноречие: " + eloquence);
        System.out.println("\tКузнечное дело: " + blacksmith);
        System.out.println("\tАлхимия: " + alchemy);
        System.out.println("\tОдноручное оружие: " + one_handed_weapon);
        System.out.println("\tДвуручное оружие: " + two_handed_weapon);
        System.out.println("\tДревковое оружие: " + pole_weapon);
        System.out.println("\tРубящее оружие: " + chopping_weapon);
        System.out.println("\tДальнобойное оружие: " + long_range_weapon);
        System.out.println("\tМилитаризм: " + militarism);
        System.out.println("\tПацифизм: " + pacifism);
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
            choice = in.nextLine();
            if (choice.equals("1")){
                strength += 1;
                upPointCount --;
                System.out.println("Сила увеличилась на 1");
            }
            if (choice.equals("2")){
                speed += 1;
                upPointCount --;
                System.out.println("Скорость увеличилась на 1");
            }
            if (choice.equals("3")){
                agility += 1;
                upPointCount --;
                System.out.println("Ловкость увеличилась на 1");
            }
            if (choice.equals("4")){
                intelligence += 1;
                upPointCount --;
                System.out.println("Интеллект увеличился на 1");
            }
            if (choice.equals("5")){
                luck += 1;
                upPointCount --;
                System.out.println("Удача увеличилась на 1");
            }
            if (choice.equals("6")){
                eloquence += 1;
                upPointCount --;
                System.out.println("Красноречие увеличилось на 1");
            }
            if (choice.equals("7")){
                blacksmith += 1;
                upPointCount --;
                System.out.println("Кузнечное дело увеличилось на 1");
            }
            if (choice.equals("8")){
                alchemy += 1;
                upPointCount --;
                System.out.println("Алхимия увеличилась на 1");
            }
            if (choice.equals("9")){
                one_handed_weapon += 1;
                upPointCount --;
                System.out.println("Одноручное оружие увеличилось на 1");
            }
            if (choice.equals("10")){
                two_handed_weapon += 1;
                upPointCount --;
                System.out.println("Двуручное оружие увеличилось на 1");
            }
            if (choice.equals("11")){
                pole_weapon += 1;
                upPointCount --;
                System.out.println("Древковое оружие увеличилось на 1");
            }
            if (choice.equals("12")){
                chopping_weapon += 1;
                upPointCount --;
                System.out.println("Рубящее оружие увеличилось на 1");
            }
            if (choice.equals("13")){
                long_range_weapon += 1;
                upPointCount --;
                System.out.println("Дальнобойное оружие увеличилось на 1");
            }
            if (choice.equals("0")){
                break;
            }
        }
    }
    public void setName(String name){
        this.name = name;
    }
}
