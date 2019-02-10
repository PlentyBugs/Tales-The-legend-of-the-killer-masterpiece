package LiveCreatures;

import Abilities.Ability;
import Abilities.AbilityType;
import Abilities.Auras.Aura;
import Items.Armors.Armor;
import Items.Item;
import Quests.Quest;
import Windows.FieldWindow;
import Windows.PlayerWindows.*;

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
    private PlayerWindowManager managerWindow;
    private PlayerInfoWindow playerInfoWindow;
    private UpgradeSkillsWindow playerAbilityWindow;
    private QuestsWindow playerQuestWindow;
    private FieldWindow fieldWindow;

    private boolean isUpStatsOpen;
    private boolean isInventoryOpen;
    private boolean isEquipmentOpen;
    private boolean isManagerOpen;
    private boolean isInfoWindowOpen;
    private boolean isAbilityWindowOpen;
    private boolean isQuestWindowOpen;

    private Set<Quest> quests = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private static final long serialVersionUID = 4994679203117290921L;

    public Player(int x, int y, String name, int lvl, int hp){
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        this.name = name;
        stats.strength = 5;
        stats.speed = 5;
        stats.agility = 5;
        stats.intelligence = 5;
        stats.luck = 5;
        stats.eloquence = 5;
        stats.blacksmith = 5;
        stats.theft = 5;
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
        vision = 3;
        levelpoints = 0;

        color = Color.ORANGE;

        initWindoows();

        isPlayer = true;

        exp = 0;
        needExpToNextLvl = 500;
        money = 457000;
    }

    public void initWindoows(){
        upStatsWindow = new UpStatsWindow(this);
        setUpStatsWindowIsVisible(false);

        inventoryWindow = new InventoryWindow(this);
        setInventoryWindowIsVisible(false);

        equipmentWindow = new EquipmentWindow(this);
        setEquipmentIsVisible(false);

        managerWindow = new PlayerWindowManager(this);
        setManagerWindowIsVisible(false);

        playerInfoWindow = new PlayerInfoWindow(this);
        setInfoWindowIsVisible(false);

        playerAbilityWindow = new UpgradeSkillsWindow(this);
        setAbilityWindowIsVisible(false);

        playerQuestWindow = new QuestsWindow(this);
        setQuestWindowIsVisible(false);
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
        fieldWindow.writeToConsole("\tСила: " + stats.strength);
        fieldWindow.writeToConsole("\tЛовкость: " + stats.agility);
        fieldWindow.writeToConsole("\tСкорость: " + stats.speed);
        fieldWindow.writeToConsole("\tИнтеллект: " + stats.intelligence);
        fieldWindow.writeToConsole("\tУдача: " + stats.luck);
        fieldWindow.writeToConsole("\tКрасноречие: " + stats.eloquence);
        fieldWindow.writeToConsole("\tКузнечное дело: " + stats.blacksmith);
        fieldWindow.writeToConsole("\tАлхимия: " + stats.alchemy);
        fieldWindow.writeToConsole("\tОдноручное оружие: " + stats.one_handed_weapon);
        fieldWindow.writeToConsole("\tДвуручное оружие: " + stats.two_handed_weapon);
        fieldWindow.writeToConsole("\tДревковое оружие: " + stats.pole_weapon);
        fieldWindow.writeToConsole("\tРубящее оружие: " + stats.chopping_weapon);
        fieldWindow.writeToConsole("\tДальнобойное оружие: " + stats.long_range_weapon);
    }

    public void setUpStatsOpen(boolean isUpStatsOpen) {
        this.isUpStatsOpen = isUpStatsOpen;
    }

    public boolean getIsUpStatsOpen() {
        return isUpStatsOpen;
    }

    public void setUpStatsWindowIsVisible(boolean isVisible) {
        upStatsWindow.setIsVisible(isVisible);
    }

    public void setInventoryOpen(boolean isInventoryOpen) {
        this.isInventoryOpen = isInventoryOpen;
    }

    public boolean getIsInventoryOpen() {
        return isInventoryOpen;
    }

    public void setInventoryWindowIsVisible(boolean isVisible) {
        inventoryWindow.setIsVisible(isVisible);
    }

    public void setEquipmentOpen(boolean isEquipmentOpen) {
        this.isEquipmentOpen = isEquipmentOpen;
    }

    public boolean getIsEquipmentOpen() {
        return isEquipmentOpen;
    }

    public void setEquipmentIsVisible(boolean isVisible) {
        equipmentWindow.setIsVisible(isVisible);
    }

    public void setManagerOpen(boolean isManagerOpen) {
        this.isManagerOpen = isManagerOpen;
    }

    public boolean getIsManagerOpen() {
        return isManagerOpen;
    }

    public void setManagerWindowIsVisible(boolean isVisible) {
        managerWindow.setIsVisible(isVisible);
    }

    public void setInfoWindowOpen(boolean isInfoWindowOpen) {
        this.isInfoWindowOpen = isInfoWindowOpen;
    }

    public boolean getIsInfoWindowOpen() {
        return isInfoWindowOpen;
    }

    public void setInfoWindowIsVisible(boolean isVisible) {
        playerInfoWindow.setIsVisible(isVisible);
    }

    public void setAbilityWindowIsVisible(boolean isVisible) {
        playerAbilityWindow.setIsVisible(isVisible);
    }

    public void setAbilityWindowOpen(boolean isAbilityWindowOpen) {
        this.isAbilityWindowOpen = isAbilityWindowOpen;
    }

    public boolean getIsAbilityWindowOpen() {
        return isAbilityWindowOpen;
    }

    public void setQuestWindowIsVisible(boolean isVisible) {
        playerQuestWindow.setIsVisible(isVisible);
    }

    public void setQuestWindowOpen(boolean isQuestWindowOpen) {
        this.isQuestWindowOpen = isQuestWindowOpen;
    }

    public boolean getIsQuestWindowOpen() {
        return isQuestWindowOpen;
    }
    public UpStatsWindow getUpStatsWindow(){
        return upStatsWindow;
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

                    chance += (int)(stats.luck/2);
                    while(chance > 0){
                        int isExtraPoint = (int) Math.ceil(Math.random() * 100);
                        if (isExtraPoint < chance){
                            upPointCount ++;
                        }
                        chance -= 5;
                    }

                    addMaxHpByStats();
                    setHp(maxHp);

                    try {
                        fieldWindow.writeToConsole("Вы повысили уровень(" + Integer.toString(lvl-1) + "->" + Integer.toString(lvl) + ")");
                        fieldWindow.writeToConsole("Вы получили очков прокачки: " + Integer.toString(upPointCount-wasUpCountPoints));
                    } catch (InterruptedException ex){

                    }
                }
            }
        });
        myThready.start();
    }

    private void addMaxHpByStats(){
        maxHp += (int)(stats.strength*5 + (stats.luck*2)*Math.random());
    }

    public double absorbDamage(double damage){
        int countProtection = 1;
        for (Item item : equipment.getArmor()){
            if (item != null){
                countProtection += ((Armor)item).getProtection();
            }
        }
        double absorbedDamage = damage*(1 - Math.pow(Math.E, -16*(Math.pow(getLvl(), 1.03))/countProtection));
        return absorbedDamage;
    }

    public void countPassiveBuffs(){
        for (Ability ability : abilities){
            if (ability.getAbilityType().contains(AbilityType.AURA)){
                ((Aura)ability).use(this);
            }
        }
    }

    public void checkQuests(){
        for(Quest quest : quests){
            if(quest.check()){
                quest.getReward(this);
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
}
