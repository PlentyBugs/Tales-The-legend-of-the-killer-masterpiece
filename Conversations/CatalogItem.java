package Conversations;

import support.Sellable;

public class CatalogItem {
    private final Sellable sellable;
    private final int price;
    private int count;

    public CatalogItem(Sellable sellable, int price, int count) {
        this.sellable = sellable;
        this.price = price;
        this.count = count;
    }

    public Sellable sellable() {
        return sellable;
    }

    public int price() {
        return price;
    }

    public int count() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}