package Conversations;

import Abilities.Ability;
import Items.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class CatalogStock implements Serializable {

    ArrayList<Object> stock = new ArrayList<>();
    private static final long serialVersionUID = -9074859420467820538L;

    public void addToStock(Item item, int price, int count) {
        stock.add(new Object[]{item, price, count, CatalogStockTypeOfItem.ITEM});
    }

    public void addToStock(Ability ability, int price, int count) {
        stock.add(new Object[]{ability, price, count, CatalogStockTypeOfItem.ABILITY});
    }

    public ArrayList<Object> getStock() {
        return stock;
    }
}
