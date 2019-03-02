package Windows.CraftWindow;

import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class IngredientChooser extends JFrame{
    private ArrayList<Item> uniqueInventory = new ArrayList<>();

    public IngredientChooser(Player player, Ingredient[] ingredients, int i, JButton buttonParent){
        setAlwaysOnTop(true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(Item item : player.getInventory()){
            if(!uniqueInventoryContains(item)){
                uniqueInventory.add(item);
            } else if(player.countOfItemInInventory(item) > 1){
                continue;
            }
            if(!item.getClass().toString().contains("Ingredients")){
                continue;
            }
            boolean isOk = true;
            for(int j = 0; j < ingredients.length; j++){
                if(ingredients[j] == null)
                    continue;
                if(ingredients[j].getClass().toString().equals(item.getClass().toString()))
                    isOk = false;
            }
            if(!isOk)
                continue;

            int count = player.countOfItemInInventory(item);
            JLabel ccunt = new JLabel();
            if(count > 1){
                ccunt = new JLabel("Количество: " + count);
            }
            JButton button = new JButton(item.getName() + " Количество: " + count);
            button.addActionListener(e -> {
                ingredients[i] = (Ingredient) item;
                buttonParent.setText(item.getName());
                close();
            });
            panel.add(button,constraints);
            constraints.gridy ++;
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private boolean uniqueInventoryContains(Item item){
        for(Item itm : uniqueInventory){
            if(itm.compareTo(item) == 0){
                return true;
            }
        }
        return false;
    }
}
