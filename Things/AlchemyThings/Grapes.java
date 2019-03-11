package Things.AlchemyThings;

import Items.Alchemy.Ingredients.Ingredient;
import Things.Grass;

public class Grapes extends Grass implements IngredientThing{
    private Ingredient herb;

    public Grapes(Ingredient herb){
        super();
        name = herb.getName();
        this.herb = herb;
        color = herb.getColor();
    }

    public Grapes(Ingredient herb, int x, int y){
        super();
        name = herb.getName();
        this.herb = herb;
        color = herb.getColor();
        this.x = x;
        this.y = y;
    }

    public Ingredient getIngredient() {
        return herb;
    }

    public void setIngredient(Ingredient herb) {
        this.herb = herb;
    }

    public Grass getParent(){
        return new Grass();
    }
}
