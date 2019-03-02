package Things.AlchemyThings;

import Items.Alchemy.Ingredients.Ingredient;
import Things.Stone;

public class Other extends Stone implements IngredientThing{
    private Ingredient ingredient;

    public Other(Ingredient ingredient){
        super();
        name = ingredient.getName();
        this.ingredient = ingredient;
        color = ingredient.getColor();
    }

    public Other(Ingredient ingredient, int x, int y){
        super();
        name = ingredient.getName();
        this.ingredient = ingredient;
        color = ingredient.getColor();
        this.x = x;
        this.y = y;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Stone getParent(){
        return new Stone();
    }
}
