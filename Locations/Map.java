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
import Creatures.AggressiveNPC.Goblin;
import Creatures.AggressiveNPC.GoblinKing;
import Creatures.GodCreature;
import Creatures.Human;
import Creatures.LiveCreature;
import Creatures.PeacefulNPC.Dealer;
import Creatures.PeacefulNPC.Inhabitant;
import Creatures.Player;
import Items.Alchemy.Ingredients.*;
import Items.Armors.Helmet;
import Items.Grade;
import Items.Item;
import Items.Material;
import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PoisonPotion;
import Items.QuestItems.KingGoblinRing;
import Items.Rarity;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;
import Quests.CollectItemQuest;
import Quests.KillQuest;
import Things.*;
import Things.AlchemyThings.Berry;
import Things.AlchemyThings.Mushroom;
import Things.Craft.AlchemyTable;
import Things.AlchemyThings.Herb;

import java.io.Serializable;

public class Map implements Serializable {

    private int playerVision;
    private GodCreature[][] map;
    protected int mapWidth;
    protected int mapHeight;
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
                    ((LiveCreature)randomGodCreature).countStatsAfterBorn();
                    ((LiveCreature)randomGodCreature).setHp((int)(Math.random()*player.getHp()+70) + 40*player.getLvl() + 70*((Human) randomGodCreature).getLvl() + ((Human)randomGodCreature).getStats().strength*12);
                } else {
                    randomGodCreature = randomThingList[(int)(randomThingList.length*Math.random())];
                    if (randomGodCreature.getClass().toString().contains("Grass")){
                        int chanceHerb = (int) Math.ceil(Math.random() * 100);
                        Ingredient[] herb = {new RedHerb(), new BlueHerb(), new GreenHerb()};
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
        for (int i = 0; i < 6; i++){
            int doorToUpperLevelLocationY = (int)(Math.random()*(mapHeight-1));
            int doorToUpperLevelLocationX = (int)(Math.random()*(mapWidth-1));
            map[doorToUpperLevelLocationY][doorToUpperLevelLocationX] = new DoorToUpperLevelLocation(doorToUpperLevelLocationX, doorToUpperLevelLocationY);
        }

        GoblinCamp goblinCamp = new GoblinCamp();
        GodCreature[][] goblinCampPart = goblinCamp.getMap().getMap();

        System.out.println(goblinCampPart.length);
        for (int i = 0; i < goblinCampPart.length; i++){
            for (int j = 0; j < goblinCampPart[0].length; j++){
                goblinCampPart[i][j].setX(j+15);
                goblinCampPart[i][j].setY(i+15);
                map[i+15][j+15] = goblinCampPart[i][j];
            }
        }

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
        map[1][1] = dealer;


        Dealer shutep = new Dealer(3,3,"Шутеп", 15623, 8461315);
        Sword shutepSwordForSale = new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONEHANDED);
        shutepSwordForSale.countProperty();
        shutep.addConversationShop(1, "Магазин", new Object[] {shutepSwordForSale, 265000, 20});
        shutep.addConversationShop(2, "Тренировка", new Object[] {new DamageUp(), 99000, 1}, new Object[] {new DecreaseDamage(), 99000, 1}, new Object[] {new Vision(), 99000, 1});
        shutep.getConversationWindow().setPlayer(player);
        map[3][3] = shutep;

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
        map[2][2] = inhabitant;
        map[2][3] = new AlchemyTable();


        int GoblinKingY = (int)(Math.random()*(mapHeight-1));
        int GoblinKingX = (int)(Math.random()*(mapWidth-1));
        GoblinKing goblinKing = new GoblinKing();
        goblinKing.setX(GoblinKingX);
        goblinKing.setY(GoblinKingY);
        map[GoblinKingY][GoblinKingX] = goblinKing;
    }

    public GodCreature[][] getMap() {
        return map;
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
