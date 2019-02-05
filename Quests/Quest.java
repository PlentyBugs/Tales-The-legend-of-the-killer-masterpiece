package Quests;

import Items.Item;
import LiveCreatures.Player;

public class Quest {

    private String title;
    private int expReward;
    private int goldReward;
    private Item[] itemReward;

    public Quest(){
        expReward = 0;
        goldReward = 0;
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

    public void getReward(Player player){
        player.addExp(expReward);
        player.addMoney(goldReward);
        if(itemReward != null){
            for (Item item : itemReward) {
                player.addItemToInventory(item);
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
}
