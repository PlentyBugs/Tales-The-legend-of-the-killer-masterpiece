package Windows.CraftWindow;

import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Things.Craft.AlchemyTable;
import Windows.PlayerWindows.UnfocusedButton;
import Windows.SupportWindows.SupportComponents.IngredientButton;
import Windows.WindowInterface;
import support.AbilityProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class AlchemyTableWindow extends CraftWindow {

    private final GridBagConstraints constraints;
    private final IngredientButton[] buttons;
    private final AlchemyTable alchemyTable;
    private final Ingredient[] ingredients;
    private final JPanel panel;
    private JPanel ingredientsPanel;
    private Player player;

    public AlchemyTableWindow(AlchemyTable parent){
        super("Алхимический стол");
        setAlwaysOnTop(true);
        alchemyTable = parent;
        buttons = new IngredientButton[]{null, null, null, null, null, null};
        ingredients = new Ingredient[]{null, null, null, null, null, null};
        ingredientsPanel = new JPanel();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                alchemyTable.setCraftTableWindowOpen(true);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                alchemyTable.setCraftTableWindowOpen(false);
            }
        });

        setPreferredSize(new Dimension(720,480));
        setMaximumSize(new Dimension(720,480));
        setMinimumSize(new Dimension(720,480));

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;

        drawWindow();
    }

    public void drawWindow(){
        getContentPane().remove(panel);

        int alchemyLevel = 0;
        if(player != null && player.hasAbility(AbilityProperty.ALCHEMIST)){
            alchemyLevel = player.getAbility(AbilityProperty.ALCHEMIST).getLevel();
        }

        ingredientsPanel.removeAll();
        ingredientsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints ingredientsConstraints = new GridBagConstraints();

        ingredientsConstraints.anchor = GridBagConstraints.NORTH;
        ingredientsConstraints.insets = new Insets(10, 10, 10, 10);
        ingredientsConstraints.gridx = 0;
        ingredientsConstraints.gridy = 0;
        for(int i = 0; i < 3 + alchemyLevel; i++){
            if(buttons[i] == null){
                IngredientButton button = new IngredientButton("Ингредиент");
                buttons[i] = button;
                button.setPreferredSize(new Dimension(180, 120));
                button.setMaximumSize(new Dimension(180, 120));
                button.setMinimumSize(new Dimension(180, 120));
                int finalI = i;
                button.addActionListener((ActionListener & Serializable)e -> {
                    IngredientChooser ingredientChooser = new IngredientChooser(player, ingredients, finalI, button);
                });
            }
            ingredientsPanel.add(buttons[i], ingredientsConstraints);

            if(i < 2){
                ingredientsConstraints.gridx ++;
            }else if(i == 2){
                ingredientsConstraints.gridx = 1;
                ingredientsConstraints.gridy ++;
            } else if(i == 3){
                ingredientsConstraints.gridx = 0;
            } else if(i == 4){
                ingredientsConstraints.gridx = 2;
            } else if(i == 5){
                ingredientsConstraints.gridx = 1;
                ingredientsConstraints.gridy ++;
            }
        }
        constraints.gridy = 0;
        panel.add(ingredientsPanel, constraints);

        constraints.gridy ++;
        JButton create = new UnfocusedButton("Создать");
        create.addActionListener((ActionListener & Serializable) e -> {
            alchemyTable.clearCreatedPotion();
            alchemyTable.create(ingredients);
            for(int i = 0; i < buttons.length; i++){
                if(buttons[i] != null)
                    if(buttons[i].getCountOfIngredients() > 0){
                        player.removeItem(buttons[i].getIngredient());
                        buttons[i].setCountOfIngredients(buttons[i].getCountOfIngredients()-1);
                        buttons[i].writeText();
                        if(buttons[i].getCountOfIngredients() == 0){
                            buttons[i] = null;
                            ingredients[i] = null;
                            drawWindow();
                        }
                    } else {
                        buttons[i] = null;
                        ingredients[i] = null;
                        drawWindow();
                    }
            }
            if(alchemyTable.getCreatedPotion() != null){
                player.addItemToInventory(alchemyTable.getCreatedPotion());
            }
            WindowInterface windowInterface = player.getWindowInterface();
            windowInterface.drawAllPlayerWindow(player, windowInterface);
        });
        panel.add(create, constraints);
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void setIsVisible(boolean visible){
        drawWindow();
        setVisible(visible);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
