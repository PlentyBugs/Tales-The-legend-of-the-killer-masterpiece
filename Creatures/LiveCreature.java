package Creatures;

import Abilities.Ability;
import Abilities.AbilityType;
import Abilities.Buffs.Buff;
import Abilities.Passive.TwoOneHandedWeapon;
import Conversations.Conversation;
import Effects.Effect;
import Items.Armors.Armor;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.Equipment;
import Items.Item;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;
import Windows.ConversationWindows.ConversationWindow;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LiveCreature extends GodCreature {
    protected int x;
    protected int y;
    protected double hp;
    protected int maxHp;
    protected int lvl;
    protected int money = 0;
    protected HashMap<LiveCreature, Integer> loyalty = new HashMap<>();
    protected Set<Effect> effects = Collections.newSetFromMap(new ConcurrentHashMap<>());
    protected Set<Buff> buffs = Collections.newSetFromMap(new ConcurrentHashMap<>());
    protected Stats stats = new Stats();
    protected Item[] uniqueDropItems;
    protected boolean talkative = false;
    protected Conversation conversation;
    private ConversationWindow conversationWindow;
    private boolean isConversationWindowOpen;
    protected double currentDamage;
    private static final long serialVersionUID = 3432956647310864719L;

    protected ArrayList<Item> inventory = new ArrayList<>();

    protected Equipment equipment = new Equipment();

    protected ArrayList<Ability> abilities = new ArrayList<>();

    public LiveCreature(){
        this(0,0,"Существо",1,100);
    }

    public LiveCreature(int x, int y, String name, int lvl, int hp){

        this.name = name;
        this.x = x;
        this.y = y;
        this.maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        isStep = false;
    }

    public void initializeWindowConv(){
        conversationWindow = new ConversationWindow(this);
        setConversationWindowIsVisible(false);
    }

    public Set<Buff> getBuffs() {
        return buffs;
    }

    public void addBuffs(Buff ... buffs) {
        this.buffs.addAll(Arrays.asList(buffs));
    }

    public void removeBuff(Buff buff){
        if (buffs.contains(buff)){
            buffs.remove(buff);
        }
    }

    public void addAbility(Ability ... abilities){
        for (Ability ability : abilities){
            if (!this.abilities.contains(ability)){
                this.abilities.add(ability);
            }
        }
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public ArrayList<Ability> getAbilitiesByType(AbilityType abilityType) {
        ArrayList<Ability> abilitiesByType = new ArrayList<>();
        for (Ability ability : abilities){
            if (ability.getAbilityType().contains(abilityType)){
                abilitiesByType.add(ability);
            }
        }
        return abilitiesByType;
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

    public boolean hasAbility(Ability ability){
        if (getAbility(ability) != null){
            return true;
        }
        return false;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public double getCurrentDamage() {
        return currentDamage;
    }

    public double getHp() {
        return hp;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public boolean getTalkative(){
        return talkative;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setCurrentDamage(double currentDamage) {
        this.currentDamage = currentDamage;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setMaxHp(int hp) {
        this.maxHp = hp;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void addEffect(Effect effect){
        effects.add(effect);
    }

    public Stats getStats() {
        return stats;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void reduceMoney(int money) {
        this.money -= money;
    }

    public Item[] getUniqueDropItems() {
        return uniqueDropItems;
    }

    public void useMomentEffect(Effect effect){
        effect.use(this);
    }

    public void countStatsAfterBorn(){}

    public void setConversationWindowIsVisible(boolean isVisible) { conversationWindow.setIsVisible(isVisible);}
    public void setConversationWindowPlayer(Player player){
        conversationWindow.setPlayer(player);
    }

    public void setConversationWindowOpen(boolean isConversationWindowOpen) {
        this.isConversationWindowOpen = isConversationWindowOpen;
    }

    public boolean getIsConversationWindowOpen() {
        return isConversationWindowOpen;
    }

    public ConversationWindow getConversationWindow() {
        return conversationWindow;
    }

    public void setConversationWindow(ConversationWindow conversationWindow) {
        this.conversationWindow = conversationWindow;
    }

    public void addToUniqueDropItem(Item ... items){
        Item[] oldUniqueDropItem = uniqueDropItems;
        uniqueDropItems = new Item[oldUniqueDropItem.length+items.length];
        for (int s = 0; s < oldUniqueDropItem.length; s++){
            uniqueDropItems[s] = oldUniqueDropItem[s];
        }
        for (int s = 0; s < items.length; s++){
            uniqueDropItems[s+oldUniqueDropItem.length] = items[s];
        }
    }

    public void addItemToInventory(Item ... itemList){
        for(Item item : itemList){
            item.setId(inventory.size());
            inventory.add(item);
        }
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public void equip(Item item){
        if (inventory.contains(item)){
            if (item.getClass().toString().contains("Weapons")){
                if (((Weapon)item).getWeaponType().contains(WeaponType.ONEHANDED)){
                    if (getAbility(new TwoOneHandedWeapon()) != null && item != equipment.getOneHandedWeaponLeft()){
                        equipment.setOneHandedWeaponRight(equipment.getOneHandedWeaponLeft());
                        equipment.setOneHandedWeaponLeft((Weapon)item);
                        equipment.setTwoHandedWeapon(null);
                    } else {
                        equipment.setOneHandedWeaponLeft((Weapon)item);
                        equipment.setOneHandedWeaponRight(null);
                        equipment.setTwoHandedWeapon(null);
                    }
                } else {
                    equipment.setTwoHandedWeapon((Weapon)item);
                    equipment.setOneHandedWeaponLeft(null);
                    equipment.setOneHandedWeaponRight(null);
                }
            } else if (item.getClass().toString().contains("Helmet")){
                equipment.setHelmet((Helmet)item);
            } else if (item.getClass().toString().contains("Torso")){
                equipment.setTorso((Torso)item);
            } else if (item.getClass().toString().contains("Ring")){
                equipment.setRings((Ring) item);
            }
        }
    }

    public void removeItem(Item item){
        for(Item itm : inventory){
            if(itm.compareTo(item) == 0){
                inventory.remove(itm);
                break;
            }
        }
    }

    public int countOfItemInInventory(Item item){
        int counter = 0;
        for(Item itm : inventory){
            if(item.compareTo(itm) == 0){
                counter ++;
            }
        }
        return counter;
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

    public int getLoyaltyByIndex(LiveCreature index){
        if(loyalty.containsKey(index)){
            return loyalty.get(index);
        } else {
            loyalty.put(index, 0);
            return 0;
        }
    }

    public void addCreatureToLoyalty(LiveCreature index){
        loyalty.put(index, 0);
    }

    public void addLoyaltyToCreature(LiveCreature index, int count){
        if(loyalty.containsKey(index)){
            if(count + loyalty.get(index) > 100)
                loyalty.put(index, 100);
            else
                loyalty.put(index, count + loyalty.get(index));
        } else {
            loyalty.put(index, count);
        }
    }

    public void addLoyalityByClassName(String className, int count){
        for(LiveCreature key : loyalty.keySet()){
            if(key.getClass().toString().equals(className)){
                addLoyaltyToCreature(key, count);
            }
        }
    }
}
