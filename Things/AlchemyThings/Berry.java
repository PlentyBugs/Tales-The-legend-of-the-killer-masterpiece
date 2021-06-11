package Things.AlchemyThings;

import Items.Alchemy.Ingredients.Ingredient;
import Things.Grass;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class Berry extends Grass implements IngredientThing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Grass.propertyList);
        propertyList.add(GeneralProperty.BERRY);
    }
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
