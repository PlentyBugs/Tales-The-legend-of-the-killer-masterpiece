package Conversations;

import Creatures.LiveCreature;
import Creatures.Player;
import Windows.ConversationWindows.ShopWindow;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Conversation {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Conversation.propertyList);
        propertyList.add(GeneralProperty.SHOP);
    }

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
