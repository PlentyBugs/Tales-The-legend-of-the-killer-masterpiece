package Locations;

import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Auras.Vision;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Abilities.Passive.Professions.Steal;
import Abilities.Passive.TwoOneHandedWeapon;
import Conversations.DialogConversation;
import Conversations.QuestDialogConversation;
import Creatures.AggressiveNPC.Bandit;
import Creatures.AggressiveNPC.Ent;
import Creatures.AggressiveNPC.Goblin;
import Creatures.GodCreature;
import Creatures.Human;
import Creatures.LiveCreature;
import Creatures.PeacefulNPC.Dealer;
import Creatures.PeacefulNPC.Inhabitant;
import Creatures.Player;
import Items.Alchemy.Ingredients.*;
import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PoisonPotion;
import Items.Armors.Helmet;
import Items.Grade;
import Items.Item;
import Items.Material;
import Items.QuestItems.KingGoblinRing;
import Items.Rarity;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;
import Quests.CollectItemQuest;
import Quests.KillQuest;
import Things.AlchemyThings.Berry;
import Things.AlchemyThings.Herb;
import Things.AlchemyThings.Mushroom;
import Things.Craft.AlchemyTable;
import Things.*;

import java.io.Serializable;

public class Map implements Serializable {

    private int playerVision;
    private GodCreature[][] mapLowerObjects;
    private GodCreature[][] mapUpperObjects;
    private int mapWidth;
    private int mapHeight;
    private Player player;

    private static final long serialVersionUID = 5350390037103737479L;

    public Map(){
        this(null, 0, 0);
    }

    public Map(Player player, int mapWidth, int mapHeight){

        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        this.player = player;

        playerVision = player.getVision()*2+1;
        mapLowerObjects = new GodCreature[mapHeight][mapWidth];
        mapUpperObjects = new GodCreature[mapHeight][mapWidth];
        for(int i = 0; i < mapHeight; i++){
            for(int j = 0; j < mapWidth; j++){
                Thing[] randomThingList = {new Grass(), new Stone(), new Tree()};

                GodCreature randomGodCreature;
                randomGodCreature = randomThingList[(int)(randomThingList.length*Math.random())];
                if (randomGodCreature.getClass().toString().contains("Grass")){
                    int chanceHerb = (int) Math.ceil(Math.random() * 100);
                    Ingredient[] herb = {new RedHerb(), new BlueHerb(), new GreenHerb(), new Hop(), new Grapes()};
                    Ingredient[] berry = {new BlueBerry(), new DrunkenBerry(), new GoblinBerry(), new WildBerry()};
                    Ingredient[] mushroom = {new HellMushroom(), new WhiteMushroom()};
                    if(chanceHerb < 2){
                        randomGodCreature = new Herb(herb[(int) (herb.length * Math.random())]);
                    } else if (chanceHerb < 4){
                        randomGodCreature = new Berry(berry[(int) (berry.length * Math.random())]);
                    } else if (chanceHerb < 6){
                        randomGodCreature = new Mushroom(mushroom[(int) (mushroom.length * Math.random())]);
                    }
                }
                randomGodCreature.setX(j);
                randomGodCreature.setY(i);

                mapLowerObjects[i][j] = randomGodCreature;

            }
        }

        for(int i = 0; i < mapHeight; i++){
            for(int j = 0; j < mapWidth; j++) {
                Human[] randomHumanList = {new Bandit(), new Goblin(), new Ent()};
                int chance = (int) Math.ceil(Math.random() * 100);

                if (chance <= 5){
                    LiveCreature randomGodCreature = randomHumanList[(int) (randomHumanList.length * Math.random())];
                    randomGodCreature.setLvl((int)(Math.random()*(player.getLvl()+16)+1) + player.getLvl() - 1);
                    randomGodCreature.countStatsAfterBorn();
                    randomGodCreature.setHp((int)(Math.random()*player.getHp()+70) + 40*player.getLvl() + 70* randomGodCreature.getLvl() + randomGodCreature.getStats().getStrength()*12);

                    randomGodCreature.setX(j);
                    randomGodCreature.setY(i);

                    mapUpperObjects[i][j] = randomGodCreature;
                } else
                    mapUpperObjects[i][j] = null;
            }
        }

        for (int i = 0; i < 7; i++){
            int healBlockY = (int)(Math.random()*(mapHeight-1));
            int healBlockX = (int)(Math.random()*(mapWidth-1));
            mapLowerObjects[healBlockY][healBlockX] = new HealBlock(healBlockX, healBlockY);
        }
        for (int i = 0; i < 6; i++){
            int doorToUpperLevelLocationY = (int)(Math.random()*(mapHeight-1));
            int doorToUpperLevelLocationX = (int)(Math.random()*(mapWidth-1));
            mapLowerObjects[doorToUpperLevelLocationY][doorToUpperLevelLocationX] = new DoorToUpperLevelLocation(doorToUpperLevelLocationX, doorToUpperLevelLocationY);
        }
/*
        for(int s = 0; s < 2; s++){
            GoblinCamp goblinCamp = new GoblinCamp();
            if(goblinCamp.getMap() != null && mapHeight >= 100 && mapWidth >= 100){
                GodCreature[][] goblinCampPart = rotate(s+1, goblinCamp.getMap().getMapLowerObjects());
                int randomCoordinate = (int)(Math.random()*(mapWidth-goblinCampPart.length));
                while(!(randomCoordinate < mapWidth)){
                    randomCoordinate = (int)(Math.random()*(mapWidth-goblinCampPart.length));
                }
                for (int i = 0; i < goblinCampPart.length; i++){
                    for (int j = 0; j < goblinCampPart[0].length; j++){
                        goblinCampPart[i][j].setX(j+randomCoordinate);
                        goblinCampPart[i][j].setY(i+randomCoordinate);
                        if(i+randomCoordinate < mapLowerObjects.length && mapLowerObjects[i+randomCoordinate].length > j+randomCoordinate){
                            mapLowerObjects[i+randomCoordinate][j+randomCoordinate] = goblinCampPart[i][j];
                        }
                    }
                }
            }
        }
*/

        Dealer dealer = new Dealer(1,1,"Петуш", 57, 59000);
        dealer.setStarterPhrase("Добрый день, путник.");
        dealer.addConversationShop(1, "Магазин", new Object[] {new HealPotion(), 4000, 300}, new Object[] {new PoisonPotion(), 6000, 300});
        dealer.addConversationShop(2, "Тренировка", new Object[] {new TwoOneHandedWeapon(), 188000, 1}, new Object[] {new CriticalStrike(), 45000, 1}, new Object[] {new Evasion(), 38000, 1}, new Object[] {new Steal(), 99000, 1});
        dealer.getConversationWindow().setPlayer(player);

        QuestDialogConversation questDialogConversationDealer = new QuestDialogConversation();
        KillQuest questDealer = new KillQuest();
        questDealer.setExpReward(15000);
        questDealer.setGoldReward(48000);
        questDealer.setTitle("Зеленая опасность!");
        questDealer.setEnemyCountToKill(16);
        questDealer.setEnemyToKill(new Goblin());
        questDealer.setEmployerName(dealer.getName());
        questDealer.setEmployer(dealer);
        questDealer.setConversationEmployer(questDialogConversationDealer);
        questDialogConversationDealer.setTitle(questDealer.getTitle());
        questDialogConversationDealer.setText("Иди убей 16 гоблинов");
        questDialogConversationDealer.setPlayerText("У тебя есть для меня задание?");
        questDialogConversationDealer.setQuest(questDealer);
        QuestDialogConversation questDialogConversationDealer2 = new QuestDialogConversation();
        CollectItemQuest questDealer2 = new CollectItemQuest();
        questDealer2.setExpReward(78120);
        questDealer2.setGoldReward(253000);
        questDealer2.setItem(new KingGoblinRing());
        questDealer2.setPlayer(player);
        questDealer2.setTitle("Король гоблинов");
        questDealer2.setEmployerName(dealer.getName());
        questDealer2.setEmployer(dealer);
        questDealer2.setItemCount(1);
        questDealer2.setConversationEmployer(questDialogConversationDealer2);
        questDialogConversationDealer2.setTitle(questDealer2.getTitle());
        questDialogConversationDealer2.setText("Да, тут где-то находится Король гоблинов, крайне сильная тварь, мне нужно его кольцо, говорят, что оно стоит немалых денег!");
        questDialogConversationDealer2.setPlayerText("У тебя есть еще что-нибудь для меня?");
        questDialogConversationDealer2.setQuest(questDealer2);
        questDialogConversationDealer2.setIsVisible(false);
        questDialogConversationDealer.addConversationBranch(questDialogConversationDealer2, 1);
        dealer.addConversationDialog(3, questDialogConversationDealer);

        dealer.addItemToInventory(
                new Sword(Material.ELVENMYTHRIL, Rarity.LEGENDARY, Grade.ARTIFACT, 3, WeaponType.TWOHANDED),
                new Helmet(Material.ELVENMYTHRIL, Rarity.MYSTICAL, Grade.ARTIFACT, 1)
        );

        for (Item item : dealer.getInventory()) {
            item.countProperty();
        }
        dealer.setX(1);
        dealer.setY(1);
        mapUpperObjects[1][1] = dealer;


        Dealer shutep = new Dealer(3,3,"Шутеп", 15623, 8461315);
        shutep.setX(3);
        shutep.setY(3);
        Sword shutepSwordForSale = new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONEHANDED);
        shutepSwordForSale.countProperty();
        shutep.addConversationShop(1, "Магазин", new Object[] {shutepSwordForSale, 265000, 20});
        shutep.addConversationShop(2, "Тренировка", new Object[] {new DamageUp(), 99000, 1}, new Object[] {new DecreaseDamage(), 99000, 1}, new Object[] {new Vision(), 99000, 1});
        shutep.getConversationWindow().setPlayer(player);
        mapUpperObjects[3][3] = shutep;

        Inhabitant inhabitant = new Inhabitant(2,2,"Данил", 2, 140);
        inhabitant.setStarterPhrase("Привет!");
        inhabitant.addConversationShop(1, "Магазин", new Object[] {new HealPotion(), 3000, 1000});
        inhabitant.addConversationDialog(2, "Прощание", "Пока", "Прощай");


        DialogConversation hello = new DialogConversation();
        hello.setTitle("Приветствие");
        hello.setText("Привет");
        hello.setPlayerText("Привет");
        DialogConversation dialogConversation = new DialogConversation();
        dialogConversation.setTitle("Как дела?");
        dialogConversation.setText("Хорошо");
        dialogConversation.setPlayerText("Как дела?");
        DialogConversation dialogConversation2 = new DialogConversation();
        dialogConversation2.setTitle("Точно?");
        dialogConversation2.setText("Да");
        dialogConversation2.setPlayerText("Точно?");
        dialogConversation.addConversationBranch(dialogConversation2, 1);
        hello.addConversationBranch(dialogConversation, 3);
        QuestDialogConversation questDialogConversation = new QuestDialogConversation();
        KillQuest quest = new KillQuest();
        quest.setExpReward(2000);
        quest.setGoldReward(15000);
        quest.setTitle("Бандиты атакуют!");
        quest.setEnemyCountToKill(6);
        quest.setEnemyToKill(new Bandit());
        quest.setEmployerName(inhabitant.getName());
        quest.setEmployer(inhabitant);
        quest.setConversationEmployer(questDialogConversation);
        questDialogConversation.setTitle(quest.getTitle());
        questDialogConversation.setText("У нас проблемы с бандитами, иди убей для меня полдюжины");
        questDialogConversation.setPlayerText("У тебя есть для меня задание?");
        questDialogConversation.setQuest(quest);
        dialogConversation2.addConversationBranch(questDialogConversation, 1);
        inhabitant.addConversationDialog(3, hello);

        inhabitant.getConversationWindow().setPlayer(player);
        inhabitant.setX(2);
        inhabitant.setY(2);
        mapUpperObjects[2][2] = inhabitant;
        mapUpperObjects[2][3] = new AlchemyTable();
    }

    public GodCreature[][] getMap(int x, int y){
        playerVision = player.getVision()*2+1;
        GodCreature[][] currentMap = new GodCreature[playerVision][playerVision];
        int vision = (playerVision-1)/2;
        for (int i = 0; i < playerVision; i++){
            for (int j = 0; j < playerVision; j++){
                if (i + y-vision >= 0 && j + x-vision >= 0 && i + y-vision < mapHeight && j + x-vision < mapWidth) {
                    if(mapUpperObjects[i + y-vision][j + x-vision] != null){
                        currentMap[i][j] = mapUpperObjects[i + y-vision][j + x-vision];
                    } else {
                        currentMap[i][j] = mapLowerObjects[i + y-vision][j + x-vision];
                    }
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

    public void setMapLowerObjects(GodCreature[][] mapLowerObjects) {
        this.mapLowerObjects = mapLowerObjects;
    }

    public void setElementByCoordinates(int x, int y, GodCreature godCreature){
        mapLowerObjects[y][x] = godCreature;
    }

    public void setElementByCoordinatesUpper(int x, int y, GodCreature godCreature){
        if(y >= 0 && y < mapHeight && x >= 0 && x < mapWidth){
            mapUpperObjects[y][x] = godCreature;
        }
    }

    public GodCreature getElementByCoordinates(int x, int y){
        if(y >= 0 && y < mapHeight && x >= 0 && x < mapWidth){
            if(mapUpperObjects[y][x] != null){
                return mapUpperObjects[y][x];
            }
            return mapLowerObjects[y][x];
        }
        return null;
    }

    public int getMapHeight() {
        return mapHeight;
    }
    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapHeight() {
        this.mapHeight = mapLowerObjects.length;
    }

    public void setMapWidth() {
        this.mapWidth = mapLowerObjects[0].length;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private GodCreature[][] rotate(int countRotationsToNinetyDegr, GodCreature[][] oldMap){
        if(oldMap.length != 0 & oldMap[0].length != 0){
            GodCreature[][] newMap = new GodCreature[oldMap[0].length][oldMap.length];
            for(int i = 0; i < oldMap.length; i++){
                for(int j = 0; j < oldMap[i].length; j++){
                    newMap[j][i] = oldMap[i][j];
                }
            }
            if(countRotationsToNinetyDegr == 1){
                return newMap;
            } else {
                return rotate(countRotationsToNinetyDegr - 1, newMap);
            }
        }
        return oldMap;
    }
}
