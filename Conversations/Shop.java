package Conversations;

import Creatures.LiveCreature;
import Creatures.Player;
import Windows.ConversationWindows.ShopWindow;

public class Shop extends Conversation {

    private ShopWindow shop;
    private LiveCreature seller;
    private Player player;

    public void run(){
        shop = new ShopWindow(player, seller);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSeller(LiveCreature seller){
        this.seller = seller;
    }
}
