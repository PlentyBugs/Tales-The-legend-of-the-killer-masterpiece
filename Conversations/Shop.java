package Conversations;

import LiveCreatures.Player;
import Windows.ConversationWindows.ShopWindow;

import java.util.ArrayList;
import java.util.Arrays;

public class Shop extends Conversation {

    private ShopWindow shop;
    private ArrayList<Object> catalog = new ArrayList<Object>();
    private Player player;

    public void run(){
        shop = new ShopWindow(player, catalog);
    }

    public void setCatalog(CatalogStock catalogStock){
        catalog.addAll(Arrays.asList(catalogStock.getStock()));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
