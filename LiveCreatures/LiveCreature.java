package LiveCreatures;

import Abilities.Buffs.Buff;
import Conversations.Conversation;
import Effects.Effect;
import Items.Item;
import Windows.ConversationWindows.ConversationWindow;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class LiveCreature extends GodCreature {
    protected int x;
    protected int y;
    protected double hp;
    protected int maxHp;
    protected int lvl;
    protected int money = 0;
    protected ArrayList<Effect> effects = new ArrayList<Effect>();
    protected ArrayList<Buff> buffs = new ArrayList<>();
    protected Stats stats = new Stats();
    protected Item[] uniqueDropItems;
    protected boolean talkative = false;
    protected Conversation conversation;
    private ConversationWindow conversationWindow;
    private boolean isConversationWindowOpen;
    protected double currentDamage;
    private static final long serialVersionUID = 3432956647310864719L;


    public LiveCreature(int x, int y, String name, int lvl, int hp){

        this.name = name;
        this.x = x;
        this.y = y;
        this.maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
    }

    public void initializeWindowConv(){
        conversationWindow = new ConversationWindow(this);
        setConversationWindowIsVisible(false);
    }

    public ArrayList<Buff> getBuffs() {
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
}
