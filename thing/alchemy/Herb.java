package thing.alchemy;

import item.alchemy.ingredient.Ingredient;
import thing.Grass;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class Herb extends Grass implements IngredientThing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Grass.propertyList);
        propertyList.add(GeneralProperty.HERB);
    }
    private Ingredient herb;

    public Herb(Ingredient herb){
        super();
        name = herb.getName();
        this.herb = herb;
        color = herb.getColor();
    }

    public Herb(Ingredient herb, int x, int y){
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
