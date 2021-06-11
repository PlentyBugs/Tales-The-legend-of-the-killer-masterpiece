package Things.AlchemyThings;

import Items.Alchemy.Ingredients.Ingredient;
import Things.Grass;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class Grapes extends Grass implements IngredientThing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Grass.propertyList);
        propertyList.add(GeneralProperty.GRAPES);
    }

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
