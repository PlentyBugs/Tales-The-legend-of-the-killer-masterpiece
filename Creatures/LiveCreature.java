package Creatures;

import Abilities.Ability;
import Abilities.AbilityType;
import Abilities.Buffs.Buff;
import Conversations.Conversation;
import Diseases.Disease;
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
import support.AbilityProperty;
import support.GeneralProperty;
import support.Property;

import java.io.Serial;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LiveCreature extends GodCreature  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(GodCreature.propertyList);
        propertyList.add(GeneralProperty.LIVE);
    }

    protected int x;
    protected int y;
    protected double hp;
    protected int maxHp;
    protected int stepCountBonus;
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
    @Serial
    private static final long serialVersionUID = 3432956647310864719L;

    protected Set<Item> inventory = Collections.newSetFromMap(new ConcurrentHashMap<>());

    protected Equipment equipment = new Equipment();

    protected ArrayList<Ability> abilities = new ArrayList<>();

    protected ArrayList<Disease> diseases = new ArrayList<>();

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
        buffs.remove(buff);
    }

    public void addAbility(Ability ... abilities){
        for (Ability ability : abilities){
            if (!this.abilities.contains(ability)){
                ability.setLiveCreature(this);
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

    public Ability getAbility(AbilityProperty abilityProperty) {
        for (Ability ability : abilities){
            if (ability.getProperties().contains(abilityProperty)){
                return ability;
            }
        }
        return null;
    }

    public boolean hasAbility(Property abilityProperty){
        return abilities.stream().flatMap(e -> e.getProperties().stream()).anyMatch(e -> e.equals(abilityProperty));
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

    public void addMoney(double money) {
        this.money += money;
    }

    public void reduceMoney(double money) {
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
        if (oldUniqueDropItem.length >= 0)
            System.arraycopy(oldUniqueDropItem, 0, uniqueDropItems, 0, oldUniqueDropItem.length);
        System.arraycopy(items, 0, uniqueDropItems, oldUniqueDropItem.length, items.length);
    }

    public LiveCreature addItemToInventory(Item ... itemList){
        inventory.addAll(Arrays.asList(itemList));
        return this;
    }

    public Set<Item> getInventory(){
        return inventory;
    }

    public void equip(Item item){
        if (inventory.contains(item)){
            if (item instanceof Weapon weapon){
                if (weapon.getWeaponType().contains(WeaponType.ONE_HANDED)){
                    if (getAbility(AbilityProperty.TWO_ONE_HANDED_WEAPONS) != null && item != equipment.getOneHandedWeaponLeft()){
                        equipment.setOneHandedWeaponRight(equipment.getOneHandedWeaponLeft());
                        equipment.setOneHandedWeaponLeft(weapon);
                    } else {
                        equipment.setOneHandedWeaponLeft(weapon);
                        equipment.setOneHandedWeaponRight(null);
                    }
                    equipment.setTwoHandedWeapon(null);
                } else {
                    equipment.setTwoHandedWeapon(weapon);
                    equipment.setOneHandedWeaponLeft(null);
                    equipment.setOneHandedWeaponRight(null);
                }
            } else if (item instanceof Helmet helmet) {
                equipment.setHelmet(helmet);
            } else if (item instanceof Torso torso) {
                equipment.setTorso(torso);
            } else if (item instanceof Ring ring) {
                equipment.setRings(ring);
            }
        }
    }

    public void unequip(Item item){
        if (inventory.contains(item) && equipment.getListOfEquipment().contains(item)){
            equipment.unequip(item);
        }
    }

    public void removeItem(Item item){
        for(Item itm : inventory){
            if(itm.compareTo(item) >= 0){
                if(equipment.getListOfEquipment().contains(item)){
                    unequip(item);
                }
                inventory.remove(itm);
                break;
            }
        }
    }

    public void removeBrokenItems(){
        for(Item itm : inventory)
            if(itm.getQuality() <= 0) {
                if (equipment.getListOfEquipment().contains(itm)) {
                    unequip(itm);
                }
                inventory.remove(itm);
            }

    }

    public boolean hasItem(Item item){
        for(Item itm : inventory){
            if(itm.compareTo(item) >= 0){
                return true;
            }
        }
        return false;
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
        return damage*(1 - Math.pow(Math.E, -16*(Math.pow(getLvl(), 1.03))/countProtection));
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
            loyalty.put(index, Math.min(count + loyalty.get(index), 100));
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

    public int getCountBuffs(Buff buff){
        int counter = 0;
        for(Buff bff : buffs){
            if(bff.getClass().toString().equals(buff.getClass().toString())){
                counter ++;
            }
        }
        return counter;
    }

    public int getStepCountBonus() {
        return stepCountBonus;
    }

    public void setStepCountBonus(int stepCountBonus) {
        this.stepCountBonus = stepCountBonus;
    }

    public void useRacePower(LiveCreature enemy){}

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }

    public void addDisease(Disease disease){
        if(!hasDisease(disease))
            diseases.add(disease);
    }

    public boolean hasDisease(Disease disease){
        for(Disease d : diseases){
            if(d.compareTo(disease) == 0){
                return true;
            }
        }
        return false;
    }
}
