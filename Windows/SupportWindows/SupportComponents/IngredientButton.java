package Windows.SupportWindows.SupportComponents;

import Items.Alchemy.Ingredients.Ingredient;

import javax.swing.*;

public class IngredientButton extends JButton {
    private String nameStandard;
    private Ingredient ingredient;
    private int countOfIngredients;

    public IngredientButton(String text){
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
