package Things.AlchemyThings;

import Items.Alchemy.Ingredients.Ingredient;
import Things.Grass;

public class Berry extends Grass implements IngredientThing{
    private Ingredient berry;

    public Berry(Ingredient berry){
        super();
        name = berry.getName();
        this.berry = berry;
        color = berry.getColor();
    }

    public Berry(Ingredient berry, int x, int y){
        super();
        name = berry.getName();
        this.berry = berry;
        color = berry.getColor();
        this.x = x;
        this.y = y;
    }

    public Ingredient getIngredient() {
        return berry;
    }

    public void setIngredient(Ingredient berry) {
        this.berry = berry;
    }

    public Grass getParent(){
        return new Grass();
    }
}
