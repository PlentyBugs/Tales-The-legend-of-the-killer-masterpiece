package Quests;

import Conversations.Conversation;
import Items.Item;
import Creatures.PeacefulNPC.Peaceful;
import Creatures.Player;
import Windows.SupportWindows.DialogWindow;

import java.io.Serializable;
import java.util.ArrayList;

public class Quest implements Serializable {

    protected String title;
    protected int expReward;
    protected int goldReward;
    protected Item[] itemReward;
    protected String employer;
    protected Conversation conversationEmployer;
    protected Peaceful employerPeaceful;

    public Quest(){
        expReward = 0;
        goldReward = 0;
    }

    public boolean check(){return false;}

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

        for(ArrayList<Conversation> list : conversationEmployer.getConversationTree()){
            for(Conversation conversation : list){
                conversation.setIsVisible(true);
            }
        }
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
}
