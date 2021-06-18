package conversation;

import creature.Player;
import window.conversation.ShopAbilityWindow;

import java.util.ArrayList;

public class TrainShop extends Conversation {

    private final ArrayList<CatalogItem> catalog = new ArrayList<>();
    private Player player;

    public void run(){
        ShopAbilityWindow shop = new ShopAbilityWindow(player, catalog);
    }

    public void setCatalog(CatalogStock catalogStock){
        catalog.addAll(catalogStock.getStock());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}