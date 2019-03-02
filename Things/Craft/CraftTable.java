package Things.Craft;

import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;

public interface CraftTable {
    void setPlayer(Player player);
    void setCraftTableWindow(boolean isVisible);
    void setCraftTableWindowOpen(boolean isCraftWindowOpen);
    boolean getCraftTableWindowOpen();
    <T extends Ingredient> void create(T ... ingredient);
}
