package JGame;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Human {

    protected int money = 0;
    private int vision;
    private String name;
    private Difficulty difficulty;
    private UpStatsWindow upStatsWindow;
    private InventoryWindow inventoryWindow;
    private PlayerWindowManager managerWindow;

    private boolean isUpStatsOpen;
    private boolean isInventoryOpen;
    private boolean isManagerOpen;

    private ArrayList<Item> inventory = new ArrayList<Item>();

    private Window window;

    public Player(int x, int y, String name){
        super(x, y, name);

        this.name = name;
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
        vision = 3;

        color = Color.ORANGE;

        upStatsWindow = new UpStatsWindow(this);
        setUpStatsWindowIsVisible(false);

        inventoryWindow = new InventoryWindow(this);
        setInventoryWindowIsVisible(false);

        managerWindow = new PlayerWindowManager(this);
        setManagerWindowIsVisible(false);

        isPlayer = true;
    }

    public void addItemToInventory(Item ... itemList){
        for (Item item : itemList){
            inventory.add(item);
        }
    }

    public int getVision(){
        return vision;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }

    public void setWindow(Window window){
        this.window = window;
    }

    public void Status() throws InterruptedException {
        getStatusPosition();
        getStatusLocation();
        getStatusStats();
    }

    public void getStatusPosition() throws InterruptedException {
        window.writeToConsole("Позиция: x = "+x+", y = " + y);
    }

    public void getStatusLocation() throws InterruptedException {
        window.writeToConsole("Локация: " + location);
    }

    public void getStatusStats() throws InterruptedException {
        window.writeToConsole("Статы:");
        window.writeToConsole("\tСила: " + stats.strength);
        window.writeToConsole("\tЛовкость: " + stats.agility);
        window.writeToConsole("\tСкорость: " + stats.speed);
        window.writeToConsole("\tИнтеллект: " + stats.intelligence);
        window.writeToConsole("\tУдача: " + stats.luck);
        window.writeToConsole("\tКрасноречие: " + stats.eloquence);
        window.writeToConsole("\tКузнечное дело: " + stats.blacksmith);
        window.writeToConsole("\tАлхимия: " + stats.alchemy);
        window.writeToConsole("\tОдноручное оружие: " + stats.one_handed_weapon);
        window.writeToConsole("\tДвуручное оружие: " + stats.two_handed_weapon);
        window.writeToConsole("\tДревковое оружие: " + stats.pole_weapon);
        window.writeToConsole("\tРубящее оружие: " + stats.chopping_weapon);
        window.writeToConsole("\tДальнобойное оружие: " + stats.long_range_weapon);
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

    public void setManagerOpen(boolean isManagerOpen) {
        this.isManagerOpen = isManagerOpen;
    }

    public boolean getIsManagerOpen() {
        return isManagerOpen;
    }

    public void setManagerWindowIsVisible(boolean isVisible) {
        managerWindow.setIsVisible(isVisible);
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }
}
