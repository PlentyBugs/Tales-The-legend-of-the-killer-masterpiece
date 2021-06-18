package thing.alchemy;

import item.alchemy.ingredient.Ingredient;
import thing.Grass;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class Mushroom extends Grass implements IngredientThing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Grass.propertyList);
        propertyList.add(GeneralProperty.MUSHROOM);
    }
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
