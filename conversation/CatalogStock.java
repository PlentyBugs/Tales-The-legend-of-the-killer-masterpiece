package conversation;

import abilities.Ability;
import item.Item;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class CatalogStock implements Serializable {

    ArrayList<CatalogItem> stock = new ArrayList<>();
    @Serial
    private static final long serialVersionUID = -9074859420467820538L;

    public void addToStock(Item item, int price, int count) {
        stock.add(new CatalogItem(item, price, count));
    }

    public void addToStock(Ability ability, int price, int count) {
        stock.add(new CatalogItem(ability, price, count));
    }

    public ArrayList<CatalogItem> getStock() {
        return stock;
    }
}
