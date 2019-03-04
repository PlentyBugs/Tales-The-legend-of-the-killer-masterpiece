package Things.ChestLike;

import Items.Item;
import Creatures.Player;
import Things.Thing;
import Windows.SupportWindows.InventoryWindowChest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Chest extends Thing {

    private Item[] inventory = new Item[0];
    private InventoryWindowChest inventoryWindow;
    private boolean isInventoryOpen;
    private Player player;
    private static final long serialVersionUID = 7320182835324217597L;

    public Chest(){
        name = "Сундук";
        color = new Color(183, 61, 0);
        isStep = true;

        inventoryWindow = new InventoryWindowChest(this);
        setInventoryWindowChestIsVisible(false);

    }

    public Chest(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }


    public void addItemToInventory(Item ... items){
        Item[] oldDropItem = inventory;
        inventory = new Item[oldDropItem.length+items.length];
        for (int s = 0; s < oldDropItem.length; s++){
            inventory[s] = oldDropItem[s];
        }
        for (int s = 0; s < items.length; s++){
            inventory[s+oldDropItem.length] = items[s];
        }
        setNameWithNumberOfItems();
    }
    public Item[] getInventory(){
        return inventory;
    }

    public void setInventoryChestOpen(boolean isInventoryOpen) {
        this.isInventoryOpen = isInventoryOpen;
    }

    public boolean getIsInventoryChestOpen() {
        return isInventoryOpen;
    }

    public void setInventoryWindowChestIsVisible(boolean isVisible) {
        inventoryWindow.setIsVisible(isVisible);
    }

    public InventoryWindowChest getInventoryWindow() {
        return inventoryWindow;
    }

    public void setInventoryWindow() {
        inventoryWindow = new InventoryWindowChest(this);
        inventoryWindow.setPlayer(player);
    }

    public void removeItemFromInventory(Item item){
        ArrayList<Item> oldinventory = new ArrayList<>(Arrays.asList(inventory));
        if (oldinventory.contains(item)){
            oldinventory.remove(item);
        }
        inventory = new Item[inventory.length-1];
        int s = 0;
        for(Item itemR : oldinventory){
            inventory[s] = itemR;
            s++;
        }
        setNameWithNumberOfItems();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    @Override
    public Chest getClearCopy() {
        return new Chest();
    }

    private void setNameWithNumberOfItems(){
        String[] str = name.split("()");
        name = str[0] + "(" + inventory.length + ")";
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
}
