package Quests;

import Items.Item;
import Creatures.Player;

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
        int counter = 0;
        for(Item item : player.getInventory()){
            if(item.getClass().toString().equals(this.item.getClass().toString())){
                counter ++;
                itemCountCurrent = counter;
            }
        }
        if(itemCountCurrent >= itemCount){
            return true;
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

    public int getItemCountCurrent() {
        return itemCountCurrent;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setItemCountCurrent(int itemCountCurrent) {
        this.itemCountCurrent = itemCountCurrent;
    }
}
