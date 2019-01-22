package Conversations;

import Items.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class CatalogStock implements Serializable {

    ArrayList<Object> stock = new ArrayList<>();

    public void addToStock(Item item, int price, int count) {
        stock.add(new Object[]{item, price, count});
    }

    public ArrayList<Object> getStock() {
        return stock;
    }
}
