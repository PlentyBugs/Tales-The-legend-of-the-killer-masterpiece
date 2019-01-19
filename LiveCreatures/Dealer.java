package LiveCreatures;

import Conversations.CatalogStock;
import Conversations.Conversation;
import Conversations.Shop;
import Items.Potions.HealPotion;
import Items.Potions.PoisonPotion;

import java.awt.*;

public class Dealer extends Human {

    private Shop shop;
    private CatalogStock catalogStock;

    public Dealer(int x, int y, String name, int lvl, int hp) {
        super(x, y, "Торговец " + name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        talkative = true;

        this.name = name;
        color = new Color(255, 100, 204);

        stats.strength = 510;
        stats.speed = 120;
        stats.intelligence = 310;
        stats.luck = 210;
        stats.eloquence = 650;

        conversation = new Conversation();

        conversation.setTitle("Магазин");

        shop = new Shop();
        shop.setTitle("Магазин");
        catalogStock = new CatalogStock();

        catalogStock.addToStock(new HealPotion(), 4000, 300);
        catalogStock.addToStock(new PoisonPotion(), 6000, 300);

        shop.setCatalog(catalogStock);

        conversation.addConversationBranch(shop);
        if(talkative){
            initializeWindowConv();
        }
    }
}
