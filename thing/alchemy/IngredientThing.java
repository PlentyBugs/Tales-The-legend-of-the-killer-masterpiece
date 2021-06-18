package thing.alchemy;

import creature.GodCreature;
import item.alchemy.ingredient.Ingredient;

public interface IngredientThing {
    Ingredient getIngredient();
    GodCreature getParent();
}
