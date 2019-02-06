package Quests;

import Items.Item;
import LiveCreatures.Player;

public class CollectItemQuest extends Quest {

    private Player player;
    private Item item;
    private int itemCount;
    private int itemCountCurrent;

    public CollectItemQuest(){
        super();
        itemCountCurrent = 0;
    }

    public boolean check(){
        for(Item item : player.getInventory()){
            if(item.getClass().toString().equals(this.item.getClass().toString())){
                return true;
            }
        }
        return false;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
