package Quests;

import Conversations.Conversation;
import Items.Item;
import LiveCreatures.Player;

import java.util.ArrayList;

public class Quest {

    protected String title;
    protected int expReward;
    protected int goldReward;
    protected Item[] itemReward;
    protected String employer;
    protected Conversation conversationEmployer;

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

    public void getReward(Player player){
        player.addExp(expReward);
        player.addMoney(goldReward);
        if(itemReward != null){
            for (Item item : itemReward) {
                player.addItemToInventory(item);
            }
        }
        for(ArrayList<Conversation> list : conversationEmployer.getConversationTree()){
            for(Conversation conversation : list){
                conversation.setIsVisible(true);
            }
        }
    }

    public void setExpReward(int expReward) {
        this.expReward = expReward;
    }

    public void setGoldReward(int goldReward) {
        this.goldReward = goldReward;
    }

    public void setItemReward(Item[] itemReward) {
        this.itemReward = itemReward;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmployerName() {
        return employer;
    }

    public void setEmployerName(String employer) {
        this.employer = employer;
    }

    public void setConversationEmployer(Conversation conversationEmployer) {
        this.conversationEmployer = conversationEmployer;
    }
}
