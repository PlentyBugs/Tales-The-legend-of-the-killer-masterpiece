package LiveCreatures;

import Items.*;
import Ability.Ability;
import Ability.Passive.TwoOneHandedWeapon;
import Windows.*;
import Windows.PlayerWindows.*;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Human {

    private int vision;
    private int exp;
    private int needExpToNextLvl;
    private String name;
    private Difficulty difficulty;
    private UpStatsWindow upStatsWindow;
    private InventoryWindow inventoryWindow;
    private EquipmentWindow equipmentWindow;
    private PlayerWindowManager managerWindow;
    private PlayerInfoWindow playerInfoWindow;

    private boolean isUpStatsOpen;
    private boolean isInventoryOpen;
    private boolean isEquipmentOpen;
    private boolean isManagerOpen;
    private boolean isInfoWindowOpen;

    private ArrayList<Item> inventory = new ArrayList<Item>();

    private Equipment equipment = new Equipment();

    private ArrayList<Ability> abilities = new ArrayList<Ability>();

    private FieldWindow fieldWindow;

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

        playerInfoWindow = new PlayerInfoWindow(this);
        setInfoWindowIsVisible(false);

        isPlayer = true;

        exp = 0;
        needExpToNextLvl = 500;
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

    public void setInfoWindowOpen(boolean isInfoWindowOpen) {
        this.isInfoWindowOpen = isInfoWindowOpen;
    }

    public boolean getIsInfoWindowOpen() {
        return isInfoWindowOpen;
    }

    public void setInfoWindowIsVisible(boolean isVisible) {
        playerInfoWindow.setIsVisible(isVisible);
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

    public void addExp(int addExp) {
        this.exp += addExp;
        levelup();
    }

    public int getNeedExpToNextLvl() {
        return needExpToNextLvl;
    }

    private void levelup(){
        while(exp > needExpToNextLvl){
            exp -= needExpToNextLvl;
            int wasUpCountPoints = upPointCount;
            needExpToNextLvl += lvl*500;
            lvl ++;
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

    private void addMaxHpByStats(){
        maxHp += (int)(stats.strength*5 + (stats.luck*2)*Math.random());
    }

    public void removeItem(Item item){
        if(inventory.contains(item)){
            inventory.remove(item);
        }
    }

    public int absorbDamage(int damage){
        int countProtection = 1;
        for (Item item : equipment.getArmor()){
            if (item != null){
                countProtection += ((Armor)item).getProtection();
            }
        }

        int absorbedDamage = (int)(damage*(1 - Math.pow(Math.E, -200/countProtection)));
        return absorbedDamage;
    }
}
