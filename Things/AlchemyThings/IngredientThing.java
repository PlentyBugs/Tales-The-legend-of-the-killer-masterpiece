package Things.AlchemyThings;

import Creatures.GodCreature;
import Items.Alchemy.Ingredients.Ingredient;

public interface IngredientThing {
    Ingredient getIngredient();
    GodCreature getParent();
}
