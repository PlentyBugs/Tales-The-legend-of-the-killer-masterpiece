package Things.Herbs;

import Items.Alchemy.Ingredients.Ingredient;
import Things.Grass;

public class Herb extends Grass {
    private Ingredient herb;

    public Herb(Ingredient herb){
        super();
        name = herb.getName();
        this.herb = herb;
        color = herb.getColor();
    }

    public Ingredient getHerb() {
        return herb;
    }

    public void setHerb(Ingredient herb) {
        this.herb = herb;
    }
}
