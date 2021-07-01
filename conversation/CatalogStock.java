package conversation;

import support.Sellable;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class CatalogStock implements Serializable {

    ArrayList<CatalogItem> stock = new ArrayList<>();
    @Serial
    private static final long serialVersionUID = -9074859420467820538L;

    public void addToStock(CatalogItem catalogItem) {
        stock.add(catalogItem);
    }

    public ArrayList<CatalogItem> getStock() {
        return stock;
    }
}
