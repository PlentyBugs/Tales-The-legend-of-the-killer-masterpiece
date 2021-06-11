package Quests;

import Conversations.Conversation;
import Items.Item;
import Creatures.PeacefulNPC.Peaceful;
import Creatures.Player;
import Windows.SupportWindows.DialogWindow;
import support.Property;
import support.PropertyProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quest implements Serializable, PropertyProvider {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.add(Property.QUEST);
    }

    protected String title;
    protected ArrayList<Quest> autoNextQuest;
    protected int expReward;
    protected int goldReward;
    protected Item[] itemReward;
    protected String employer;
    protected Conversation conversationEmployer;
    protected Peaceful employerPeaceful;
    protected boolean visible;
    protected QuestCondition condition;
    protected boolean isFinished;

    public Quest(){
        expReward = 0;
        goldReward = 0;
        visible = true;
        isFinished = false;
        autoNextQuest = new ArrayList<>();
    }

    public boolean check(){
        if(condition != null){
            return condition.condition();
        }
        return false;
    }

    public Quest setCondition(QuestCondition condition){
        this.condition = condition;
        return this;
    }

    public Quest setConditionToBeVisible(VisibleCondition visibleCondition){
        visible = visibleCondition.visibleCondition();
        return this;
    }

    public String getTitle() {
        return title;
    }

    public int getExpReward() {
        return expReward;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public Item[] getItemReward() {
        return itemReward;
    }

    public Quest getReward(Player player){
        player.addExp(expReward);
        player.addMoney(goldReward);
        String rewardItemMessage = "";
        if(itemReward != null){
            rewardItemMessage = "Выпавшие вещи:\n";
            for (Item item : itemReward) {
                player.addItemToInventory(item);
                rewardItemMessage += item.getName() + "\n";
            }
        }

        DialogWindow dialogWindow = new DialogWindow("Поздравляем, вы выполнили квест" + title + "!" +
                "\nНагада золотом: " + Integer.toString(goldReward) +
                "\nНаграда опытом: " + Integer.toString(expReward) +
                rewardItemMessage);

        if(conversationEmployer != null)
            for(ArrayList<Conversation> list : conversationEmployer.getConversationTree())
                for(Conversation conversation : list)
                    conversation.setIsVisible(true);

        for(Quest quest : autoNextQuest){
            player.addQuest(quest);
        }

        isFinished = true;
        return this;
    }

    public Quest setExpReward(int expReward) {
        this.expReward = expReward;
        return this;
    }

    public Quest setGoldReward(int goldReward) {
        this.goldReward = goldReward;
        return this;
    }

    public Quest setItemReward(Item[] itemReward) {
        this.itemReward = itemReward;
        return this;
    }
    public Quest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getEmployerName() {
        return employer;
    }

    public Quest setEmployerName(String employer) {
        this.employer = employer;
        return this;
    }

    public Quest setConversationEmployer(Conversation conversationEmployer) {
        this.conversationEmployer = conversationEmployer;
        return this;
    }

    public Quest setEmployer(Peaceful employerPeaceful) {
        this.employerPeaceful = employerPeaceful;
        return this;
    }

    public Quest addAutoNextQuest(Quest quest){
        autoNextQuest.add(quest);
        return this;
    }

    public Quest setVisible(boolean visible){
        this.visible = visible;
        return this;
    }

    public boolean getVisible(){
        return visible;
    }

    public boolean getIsFinished(){
        return isFinished;
    }

    public Quest setIsFinished(boolean isFinished){
        this.isFinished = isFinished;
        return this;
    }
}
