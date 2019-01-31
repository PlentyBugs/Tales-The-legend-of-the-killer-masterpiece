package LiveCreatures;

import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
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

        Shop shop = new Shop();
        shop.setTitle("Магазин");
        catalogStock = new CatalogStock();

        catalogStock.addToStock(new HealPotion(), 4000, 300);
        catalogStock.addToStock(new PoisonPotion(), 6000, 300);

        shop.setCatalog(catalogStock);

        conversation.addConversationBranch(shop);

        Shop train = new Shop();
        train.setTitle("Тренировка");
        catalogStock = new CatalogStock();

        catalogStock.addToStock(new CriticalStrike(), 45000, 1);
        catalogStock.addToStock(new Evasion(), 38000, 1);

        train.setCatalog(catalogStock);

        conversation.addConversationBranch(train);
        if(talkative){
            initializeWindowConv();
        }
    }
}
