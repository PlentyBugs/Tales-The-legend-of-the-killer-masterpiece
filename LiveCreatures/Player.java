package JGame.LiveCreatures;

import JGame.Ability.Ability;
import JGame.Ability.Passive.TwoOneHandedWeapon;
import JGame.Items.*;
import JGame.Windows.EquipmentWindow;
import JGame.Windows.InventoryWindow;
import JGame.Windows.PlayerWindowManager;
import JGame.Windows.UpStatsWindow;
import JGame.Windows.FieldWindow;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Human {

    private int vision;
    private int exp;
    private String name;
    private Difficulty difficulty;
    private UpStatsWindow upStatsWindow;
    private InventoryWindow inventoryWindow;
    private EquipmentWindow equipmentWindow;
    private PlayerWindowManager managerWindow;

    private boolean isUpStatsOpen;
    private boolean isInventoryOpen;
    private boolean isEquipmentOpen;
    private boolean isManagerOpen;

    private ArrayList<Item> inventory = new ArrayList<Item>();

    private Equipment equipment = new Equipment();

    private ArrayList<Ability> abilities = new ArrayList<Ability>();

    private FieldWindow fieldWindow;

    public Player(int x, int y, String name, int lvl, int hp){
        super(x, y, name, lvl, hp);

        this.hp = hp;
        this.lvl = lvl;
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

        equipmentWindow = new EquipmentWindow(this);
        setEquipmentIsVisible(false);

        managerWindow = new PlayerWindowManager(this);
        setManagerWindowIsVisible(false);

        isPlayer = true;
    }

    public void addItemToInventory(Item ... itemList){
        for (Item item : itemList){
            inventory.add(item);
        }
    }

    public void addAbility(Ability ... abilities){
        for (Ability ability : abilities){
            if (!this.abilities.contains(ability)){
                this.abilities.add(ability);
            }
        }
    }

    public Ability getAbility(Ability ability){
        String abilityName = ability.getClass().toString().split("\\.")[ability.getClass().toString().split("\\.").length-1];
        for (Ability abil : abilities){
            if (abilityName.equals(abil.getClass().toString().split("\\.")[abil.getClass().toString().split("\\.").length-1])){
                return abil;
            }
        }
        return null;
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

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public UpStatsWindow getUpStatsWindow(){
        return upStatsWindow;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void equip(Item item){
        if (inventory.contains(item)){
            String itemClass = item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1];
            if (itemClass.equals("Sword")){
                if (((Weapon)item).getWeaponType() == WeaponType.ONEHANDED){
                    if (getAbility(new TwoOneHandedWeapon()) != null && item != equipment.getOneHandedWeaponLeft()){
                        equipment.setOneHandedWeaponRight(equipment.getOneHandedWeaponLeft());
                        equipment.setOneHandedWeaponLeft((Weapon)item);
                    } else {
                        equipment.setOneHandedWeaponLeft((Weapon)item);
                        equipment.setOneHandedWeaponRight(null);
                    }
                } else {
                    equipment.setTwoHandedWeapon((Weapon)item);
                    equipment.setOneHandedWeaponRight(null);
                }
            } else if (itemClass.equals("Helmet")){
                equipment.setHelmet((Helmet)item);
            } else if (itemClass.equals("Torso")){
                equipment.setTorso((Torso)item);
            }
        }
    }
    public int getExp() {
        return exp;
    }

    public void addExp(int exp) {
        this.exp += exp;
    }
}
