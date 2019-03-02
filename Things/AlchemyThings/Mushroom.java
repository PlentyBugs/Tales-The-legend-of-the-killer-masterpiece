package Things.AlchemyThings;

import Items.Alchemy.Ingredients.Ingredient;
import Things.Grass;

public class Mushroom extends Grass implements IngredientThing{
    private Ingredient mushroom;

    public Mushroom(Ingredient mushroom){
        super();
        name = mushroom.getName();
        this.mushroom = mushroom;
        color = mushroom.getColor();
    }

    public Mushroom(Ingredient mushroom, int x, int y){
        super();
        name = mushroom.getName();
        this.mushroom = mushroom;
        color = mushroom.getColor();
        this.x = x;
        this.y = y;
    }

    public Ingredient getIngredient() {
        return mushroom;
    }

    public void setIngredient(Ingredient mushroom) {
        this.mushroom = mushroom;
    }

    public Grass getParent(){
        return new Grass();
    }
}
