package thing.craft;

import item.alchemy.ingredient.Ingredient;

public interface AlchemyCraftTable extends CraftTable {
    <T extends Ingredient> void create(T ... ingredient);
}
