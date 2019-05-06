package Creatures;

import Abilities.Ability;
import Abilities.AbilityType;
import Abilities.Auras.Aura;
import Creatures.PeacefulNPC.NPC;
import Items.Item;
import Items.StatItem;
import Quests.Quest;
import Quests.ReachQuest;
import Windows.FieldWindow;
import Windows.PlayerWindows.*;
import Windows.SupportWindows.SupportComponents.SavePanel;

import java.awt.*;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Player extends Human {

    private int vision;
    private int exp;
    private int needExpToNextLvl;
    private int levelpoints;
    private String name;
    private Difficulty difficulty;
    private UpStatsWindow upStatsWindow;
    private InventoryWindow inventoryWindow;
    private EquipmentWindow equipmentWindow;
    private PlayerInfoWindow playerInfoWindow;
    private UpgradeSkillsWindow playerAbilityWindow;
    private QuestsWindow playerQuestWindow;
    private FieldWindow fieldWindow;
    private SavePanel savePanel;
    private DiseasesWindow diseasesWindow;
    private boolean inFight;

    private Set<Quest> quests = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private static final long serialVersionUID = 4994679203117290921L;

    public Player(int x, int y, String name, int lvl, int hp){
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        this.name = name;
        stats.setStrength(5);
        stats.setSpeed(5);
        stats.setAgility(5);
        stats.setIntelligence(5);
        stats.setLuck(5);
        stats.setEloquence(5);
        stats.setBlacksmith(500);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOne_handed_weapon(5);
        stats.setTwo_handed_weapon(5);
        stats.setPole_weapon(5);
        stats.setChopping_weapon(5);
        stats.setLong_range_weapon(5);

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);
        vision = 4;
        levelpoints = 0;

        color = Color.ORANGE;

        initWindows();

        isPlayer = true;

        exp = 0;
        needExpToNextLvl = 500;
        money = 457000;

        NPC npc = new NPC(this);
    }

    public void initWindows(){
        upStatsWindow = new UpStatsWindow(this);
        inventoryWindow = new InventoryWindow(this);
        equipmentWindow = new EquipmentWindow(this);
        playerInfoWindow = new PlayerInfoWindow(this);
        playerAbilityWindow = new UpgradeSkillsWindow(this);
        playerQuestWindow = new QuestsWindow(this);
        savePanel = new SavePanel(this);
        diseasesWindow = new DiseasesWindow(this);
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public Set<Quest> getQuests() {
        return quests;
    }

    public void addQuest(Quest quest){
        quests.add(quest);
    }

    public void removeQuest(Quest quest){
        if(quests.contains(quest)){
            quests.remove(quest);
        }
    }

    public int getVision(){
        return vision;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        if (name.equals("")){
            name = "Безымянный";
        }
        this.name = name;
    }

    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }

    public void setFieldWindow(FieldWindow fieldWindow){
        this.fieldWindow = fieldWindow;
    }

    public void Status() throws InterruptedException {
        getStatusPosition();
        getStatusLocation();
        getStatusStats();
    }

    public void getStatusPosition() throws InterruptedException {
        fieldWindow.writeToConsole("Позиция: x = "+x+", y = " + y);
    }

    public void getStatusLocation() throws InterruptedException {
        fieldWindow.writeToConsole("Локация: " + location);
    }

    public void getStatusStats() throws InterruptedException {
        fieldWindow.writeToConsole("Статы:");
        fieldWindow.writeToConsole("\tСила: " + stats.getStrength());
        fieldWindow.writeToConsole("\tЛовкость: " + stats.getAgility());
        fieldWindow.writeToConsole("\tСкорость: " + stats.getSpeed());
        fieldWindow.writeToConsole("\tИнтеллект: " + stats.getIntelligence());
        fieldWindow.writeToConsole("\tУдача: " + stats.getLuck());
        fieldWindow.writeToConsole("\tКрасноречие: " + stats.getEloquence());
        fieldWindow.writeToConsole("\tКузнечное дело: " + stats.getBlacksmith());
        fieldWindow.writeToConsole("\tАлхимия: " + stats.getAlchemy());
        fieldWindow.writeToConsole("\tОдноручное оружие: " + stats.getOne_handed_weapon());
        fieldWindow.writeToConsole("\tДвуручное оружие: " + stats.getTwo_handed_weapon());
        fieldWindow.writeToConsole("\tДревковое оружие: " + stats.getPole_weapon());
        fieldWindow.writeToConsole("\tРубящее оружие: " + stats.getChopping_weapon());
        fieldWindow.writeToConsole("\tДальнобойное оружие: " + stats.getLong_range_weapon());
    }

    public QuestsWindow getPlayerQuestWindow() {
        return playerQuestWindow;
    }

    public PlayerInfoWindow getPlayerInfoWindow() {
        return playerInfoWindow;
    }

    public UpgradeSkillsWindow getPlayerAbilityWindow() {
        return playerAbilityWindow;
    }

    public DiseasesWindow getDiseasesWindow() {
        return diseasesWindow;
    }

    public void setUpStatsOpen(boolean isUpStatsOpen) {
    }

    public int getExp() {
        return exp;
    }

    public void addExp(int addExp) {
        this.exp += addExp;
        levelup();
    }

    public int getLevelpoints() {
        return levelpoints;
    }

    public void setLevelpoints(int levelpoints) {
        this.levelpoints = levelpoints;
    }

    public int getNeedExpToNextLvl() {
        return needExpToNextLvl;
    }

    private void levelup(){
        Thread myThready = new Thread(new Runnable()
        {
            public void run()
            {

                while(exp > needExpToNextLvl){
                    exp -= needExpToNextLvl;
                    int wasUpCountPoints = upPointCount;
                    needExpToNextLvl += lvl*500;
                    lvl ++;
                    levelpoints ++;
                    int chance = 10;
                    switch (difficulty){
                        case EASY:
                            upPointCount += 5;
                            chance = 10;
                            break;
                        case NORMAL:
                            upPointCount += 5;
                            chance = 15;
                            break;
                        case HARD:
                            upPointCount += 5;
                            chance = 20;
                            break;
                        case VERYHARD:
                            upPointCount += 6;
                            chance = 25;
                            break;
                        case NIGHTMARE:
                            upPointCount += 6;
                            chance = 30;
                            break;
                        case STOPIT:
                            upPointCount += 7;
                            chance = 40;
                            break;
                    }

                    chance += stats.getLuck()/2;
                    while(chance > 0){
                        int isExtraPoint = (int) Math.ceil(Math.random() * 100);
                        if (isExtraPoint < chance){
                            upPointCount ++;
                        }
                        chance -= 5;
                    }

                    addMaxHpByStats();
                    setHp(maxHp);

                    fieldWindow.writeToConsole("Вы повысили уровень(" + Integer.toString(lvl-1) + "->" + Integer.toString(lvl) + ")");
                    fieldWindow.writeToConsole("Вы получили очков прокачки: " + Integer.toString(upPointCount-wasUpCountPoints));
                }
            }
        });
        myThready.start();
    }

    private void addMaxHpByStats(){
        maxHp += (int)(stats.getStrength()*5 + (stats.getLuck()*2)*Math.random());
    }

    public void countPassiveBuffs(){
        for (Ability ability : abilities){
            if (ability.getAbilityType().contains(AbilityType.AURA)){
                ((Aura)ability).use(this);
            }
        }
    }

    public void countEquipmentBuffs(){
        stats.getBonusStats().clear();
        for(Item item : equipment.getListOfEquipment()){
            if(item instanceof StatItem){
                stats.getBonusStats().upStat(((StatItem)item).getStat(), ((StatItem)item).getStatPower());
            }
        }
    }

    public void checkQuests(){
        for(Quest quest : quests){
            if(quest instanceof ReachQuest){
                ((ReachQuest)quest).setCurrentLocation(location);
            }
            if(quest.getVisible() && quest.check()){
                quest.getReward(this);
                removeQuest(quest);
            }
        }
    }

    public FieldWindow getFieldWindow() {
        return fieldWindow;
    }

    public EquipmentWindow getEquipmentWindow() {
        return equipmentWindow;
    }

    public InventoryWindow getInventoryWindow() {
        return inventoryWindow;
    }

    public SavePanel getSavePanel() {
        return savePanel;
    }

    public UpStatsWindow getUpStatsWindow() {
        return upStatsWindow;
    }

    public void setInFight(boolean inFight) {
        this.inFight = inFight;
    }

    public boolean getInFight(){
        return inFight;
    }

    public void setLocation(String location){
        this.location = location;
    }
}
