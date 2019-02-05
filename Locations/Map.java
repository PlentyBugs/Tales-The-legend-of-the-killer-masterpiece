package Locations;

import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Conversations.DialogConversation;
import Conversations.QuestDialogConversation;
import Items.Potions.HealPotion;
import Items.Potions.PoisonPotion;
import LiveCreatures.*;
import Quests.Quest;
import Things.*;

import java.io.Serializable;

public class Map implements Serializable {

    private int playerVision;
    private GodCreature[][] map;
    protected int mapWidth;
    protected int mapHeight;
    private Player player;

    private static final long serialVersionUID = 5350390037103737479L;

    public Map(Player player, int mapWidth, int mapHeight){
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        this.player = player;

        playerVision = player.getVision()*2+1;
        map = new GodCreature[mapHeight][mapWidth];
        for(int i = 0; i < mapHeight; i++){
            for(int j = 0; j < mapWidth; j++){
                Thing[] randomThingList = {new Grass(), new Stone(), new Tree()};
                Human[] randomHumanList = {new Bandit(), new Goblin()};

                int chance = (int) Math.ceil(Math.random() * 100);

                GodCreature randomGodCreature = new GodCreature();
                if (chance >= 1 && chance <= 5){
                    randomGodCreature = randomHumanList[(int) (randomHumanList.length * Math.random())];
                    ((LiveCreature)randomGodCreature).setLvl((int)(Math.random()*(player.getLvl()+16)+1) + player.getLvl() - 1);
                    ((LiveCreature)randomGodCreature).setHp((int)(Math.random()*player.getHp()+70) + 40*player.getLvl() + 70*((Human) randomGodCreature).getLvl());
                    ((LiveCreature)randomGodCreature).countStatsAfterBorn();
                } else {
                    randomGodCreature = randomThingList[(int)(randomThingList.length*Math.random())];
                }
                randomGodCreature.setX(j);
                randomGodCreature.setY(i);

                map[i][j] = randomGodCreature;

            }
        }
        for (int i = 0; i < 7; i++){
            int healBlockY = (int)(Math.random()*(mapHeight-1));
            int healBlockX = (int)(Math.random()*(mapWidth-1));
            map[healBlockY][healBlockX] = new HealBlock(healBlockX, healBlockY);
        }
        for (int i = 0; i < 3; i++){
            int doorToUpperLevelLocationY = (int)(Math.random()*(mapHeight-1));
            int doorToUpperLevelLocationX = (int)(Math.random()*(mapWidth-1));
            map[doorToUpperLevelLocationY][doorToUpperLevelLocationX] = new DoorToUpperLevelLocation(doorToUpperLevelLocationX, doorToUpperLevelLocationY);
        }

        Dealer dealer = new Dealer(1,1,"Петуш", 57, 59000);
        dealer.setStarterPhrase("Добрый день, путник.");
        dealer.addConversationShop(1, "Магазин", new Object[] {new HealPotion(), 4000, 300}, new Object[] {new PoisonPotion(), 6000, 300});
        dealer.addConversationShop(2, "Тренировка", new Object[] {new CriticalStrike(), 45000, 1}, new Object[] {new Evasion(), 38000, 1});
        dealer.getConversationWindow().setPlayer(player);
        map[1][1] = dealer;

        Inhabitant inhabitant = new Inhabitant(2,2,"Данил", 2, 140);
        inhabitant.setStarterPhrase("Привет!");
        inhabitant.addConversationShop(1, "Магазин", new Object[] {new HealPotion(), 3000, 1000});
        inhabitant.addConversationDialog(2, "Прощание", "Пока", "Прощай");
        inhabitant.addConversationDialog(3, "Приветствие", "Привет", "Привет");

        DialogConversation dialogConversation = new DialogConversation();
        dialogConversation.setTitle("Как дела?");
        dialogConversation.setText("Хорошо");
        dialogConversation.setPlayerText("Как дела?");
        DialogConversation dialogConversation2 = new DialogConversation();
        dialogConversation2.setTitle("Точно?");
        dialogConversation2.setText("Да");
        dialogConversation2.setPlayerText("Точно?");
        dialogConversation.addConversationBranch(dialogConversation2, 1);
        QuestDialogConversation questDialogConversation = new QuestDialogConversation();
        questDialogConversation.setTitle("Задание");
        questDialogConversation.setText("У нас проблемы с бандитами, иди убей дял меня полдюжины");
        questDialogConversation.setPlayerText("У тебя есть для меня задание?");
        Quest quest = new Quest();
        quest.setExpReward(2000);
        quest.setGoldReward(15000);
        quest.setTitle("Бандиты атакуют!");
        questDialogConversation.setQuest(quest);
        dialogConversation2.addConversationBranch(questDialogConversation, 1);
        inhabitant.addConversationDialog(3, dialogConversation);

        inhabitant.getConversationWindow().setPlayer(player);
        map[2][2] = inhabitant;
    }

    public GodCreature[][] getMap(int x, int y){
        playerVision = player.getVision()*2+1;
        GodCreature[][] currentMap = new GodCreature[playerVision][playerVision];
        int vision = (playerVision-1)/2;
        for (int i = 0; i < playerVision; i++){
            for (int j = 0; j < playerVision; j++){
                if (i + y-vision >= 0 && j + x-vision >= 0 && i + y-vision < mapHeight && j + x-vision < mapWidth) {
                    currentMap[i][j] = map[i + y-vision][j + x-vision];
                    if (i == j && i == player.getVision()){
                        currentMap[i][j] = player;
                    }
                } else {
                    currentMap[i][j] = new GreatWallNullerField();
                }
            }
        }
        return currentMap;
    }

    public void setMap(GodCreature[][] map) {
        this.map = map;
    }

    public void setElementByCoordinates(int x, int y, GodCreature godCreature){
        map[y][x] = godCreature;
    }

    public int getMapHeight() {
        return mapHeight;
    }
    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapHeight() {
        this.mapHeight = map.length;
    }

    public void setMapWidth() {
        this.mapWidth = map[0].length;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
