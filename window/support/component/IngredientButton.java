package window.support.component;

import item.alchemy.ingredient.Ingredient;
import window.player.UnfocusedButton;

import javax.swing.*;

public class IngredientButton extends UnfocusedButton {
    private String nameStandard;
    private Ingredient ingredient;
    private int countOfIngredients;

    public IngredientButton(String text) {
        super(text);
        this.nameStandard = text;
        countOfIngredients = 0;
        setText(text);
    }

    public void setCountOfIngredients(int countOfIngredients) {
        this.countOfIngredients = countOfIngredients;
    }

    public int getCountOfIngredients() {
        return countOfIngredients;
    }

    public void setNameStandard(String nameStandard) {
        this.nameStandard = nameStandard;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        setNameStandard(ingredient.getName());
        this.ingredient = ingredient;
    }

    public boolean writeText(){
        if(countOfIngredients > 0){
            setText(nameStandard + "(" + countOfIngredients + ")");
            return true;
        } else {
            setText("Ингредиент");
            return false;
        }
    }
}
