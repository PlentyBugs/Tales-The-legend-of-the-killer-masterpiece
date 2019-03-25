package Things.Craft;

import Items.Alchemy.Ingredients.Ingredient;

public interface AlchemyCraftTable extends CraftTable {
    <T extends Ingredient> void create(T ... ingredient);
}
