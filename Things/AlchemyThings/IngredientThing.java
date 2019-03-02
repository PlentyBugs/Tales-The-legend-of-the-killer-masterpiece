package Things.AlchemyThings;

import Items.Alchemy.Ingredients.Ingredient;

public interface IngredientThing<T> {
    Ingredient getIngredient();
    T getParent();
}
