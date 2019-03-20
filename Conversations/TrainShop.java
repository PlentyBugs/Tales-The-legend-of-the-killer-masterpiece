package Conversations;

import Creatures.Player;
import Windows.ConversationWindows.ShopAbilityWindow;

import java.util.ArrayList;
import java.util.Arrays;

public class TrainShop extends Conversation {

    private ShopAbilityWindow shop;
    private ArrayList<Object> catalog = new ArrayList<Object>();
    private Player player;

    public void run(){
        shop = new ShopAbilityWindow(player, catalog);
    }

    public void setCatalog(CatalogStock catalogStock){
        catalog.addAll(Arrays.asList(catalogStock.getStock()));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}