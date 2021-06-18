package thing.alchemy;

import item.alchemy.ingredient.Ingredient;
import thing.Stone;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class Other extends Stone implements IngredientThing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Stone.propertyList);
        propertyList.add(GeneralProperty.OTHER);
    }
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
