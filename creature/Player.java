package creature;

import abilities.Ability;
import abilities.AbilityType;
import abilities.auras.Aura;
import creature.peaceful.npc.NPC;
import item.Item;
import item.StatItem;
import quest.Quest;
import quest.ReachQuest;
import window.player.*;
import window.support.component.SavePanel;
import window.WindowInterface;
import support.Property;
import support.CreatureProperty;

import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Player extends Human {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Human.propertyList);
        propertyList.add(CreatureProperty.PLAYER);
    }

    private int vision;
    private int exp;
    private int needExpToNextLvl;
    private int levelPoints;
    private String name;
    private Difficulty difficulty;
    private UpStatsWindow upStatsWindow;
    private PlayerInventory inventoryWindow;
    private EquipmentWindow equipmentWindow;
    private PlayerInfoWindow playerInfoWindow;
    private UpgradeSkillsWindow playerAbilityWindow;
    private QuestsWindow playerQuestWindow;
    private WindowInterface windowInterface;
    private SavePanel savePanel;
    private DiseasesWindow diseasesWindow;
    private boolean inFight;

    private final Set<Quest> quests = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @Serial
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
        stats.setOneHandedWeapon(5);
        stats.setTwoHandedWeapon(5);
        stats.setPoleWeapon(5);
        stats.setChoppingWeapon(5);
        stats.setLongRangeWeapon(5);

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);
        vision = 4;
        levelPoints = 0;

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
        inventoryWindow = new PlayerInventory(this);
        equipmentWindow = new EquipmentWindow(this);
        playerInfoWindow = new PlayerInfoWindow(this);
        playerAbilityWindow = new UpgradeSkillsWindow(this);
        playerQuestWindow = new QuestsWindow(this);
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
        quests.remove(quest);
    }

    public int getVision(){
        return vision;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        if ("".equals(name) || name == null){
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

    public void setWindowInterface(WindowInterface windowInterface){
        this.windowInterface = windowInterface;
    }

    public void Status() throws InterruptedException {
        getStatusPosition();
        getStatusLocation();
        getStatusStats();
    }

    public void getStatusPosition() {
        windowInterface.writeToConsole("Позиция: x = "+x+", y = " + y);
    }

    public void getStatusLocation() {
        windowInterface.writeToConsole("Локация: " + location);
    }

    public void getStatusStats() {
        windowInterface.writeToConsole("Статы:");
        windowInterface.writeToConsole("\tСила: " + stats.getStrength());
        windowInterface.writeToConsole("\tЛовкость: " + stats.getAgility());
        windowInterface.writeToConsole("\tСкорость: " + stats.getSpeed());
        windowInterface.writeToConsole("\tИнтеллект: " + stats.getIntelligence());
        windowInterface.writeToConsole("\tУдача: " + stats.getLuck());
        windowInterface.writeToConsole("\tКрасноречие: " + stats.getEloquence());
        windowInterface.writeToConsole("\tКузнечное дело: " + stats.getBlacksmith());
        windowInterface.writeToConsole("\tАлхимия: " + stats.getAlchemy());
        windowInterface.writeToConsole("\tОдноручное оружие: " + stats.getOneHandedWeapon());
        windowInterface.writeToConsole("\tДвуручное оружие: " + stats.getTwoHandedWeapon());
        windowInterface.writeToConsole("\tДревковое оружие: " + stats.getPoleWeapon());
        windowInterface.writeToConsole("\tРубящее оружие: " + stats.getChoppingWeapon());
        windowInterface.writeToConsole("\tДальнобойное оружие: " + stats.getLongRangeWeapon());
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

    public void setUpStatsOpen(boolean isUpStatsOpen) {}

    public int getExp() {
        return exp;
    }

    public void addExp(int addExp) {
        this.exp += addExp;
        levelup();
    }

    public int getLevelPoints() {
        return levelPoints;
    }

    public void setLevelPoints(int levelPoints) {
        this.levelPoints = levelPoints;
    }

    public int getNeedExpToNextLvl() {
        return needExpToNextLvl;
    }

    private void levelup(){
        Thread myThready = new Thread(() -> {

            while(exp > needExpToNextLvl){
                exp -= needExpToNextLvl;
                int wasUpCountPoints = upPointCount;
                needExpToNextLvl += lvl*500;
                lvl ++;
                levelPoints++;
                upPointCount += switch (difficulty) {
                    case EASY, NORMAL, HARD -> 5;
                    case VERY_HARD, NIGHTMARE -> 6;
                    case STOP_IT -> 7;
                };
                int chance = switch (difficulty) {
                    case EASY -> 10;
                    case NORMAL -> 15;
                    case HARD -> 20;
                    case VERY_HARD -> 25;
                    case NIGHTMARE -> 30;
                    case STOP_IT -> 40;
                };

                chance += stats.getLuck() / 2;
                while(chance > 0){
                    int isExtraPoint = (int) Math.ceil(Math.random() * 100);
                    if (isExtraPoint < chance){
                        upPointCount ++;
                    }
                    chance -= 5;
                }

                addMaxHpByStats();
                setHp(maxHp);

                windowInterface.writeToConsole("Вы повысили уровень(" + (lvl - 1) + "->" + lvl + ")");
                windowInterface.writeToConsole("Вы получили очков прокачки: " + (upPointCount - wasUpCountPoints));
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
            if(item instanceof StatItem statItem){
                stats.getBonusStats().upStat(statItem.getStat(), statItem.getStatPower());
            }
        }
    }

    public void checkQuests(){
        for(Quest quest : quests){
            if(quest instanceof ReachQuest reachQuest){
                reachQuest.setCurrentLocation(location);
            }
            if(quest.getVisible() && quest.check()){
                quest.getReward(this);
                removeQuest(quest);
            }
        }
    }

    public WindowInterface getWindowInterface() {
        return windowInterface;
    }

    public EquipmentWindow getEquipmentWindow() {
        return equipmentWindow;
    }

    public PlayerInventory getInventoryWindow() {
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
