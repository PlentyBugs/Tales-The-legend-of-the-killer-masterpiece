package Windows.CraftWindow;

import Abilities.Passive.Professions.Alchemist;
import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Things.Craft.AlchemyTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;

public class AlchemyTableWindow extends CraftWindow {

    private AlchemyTable alchemyTable;
    private JPanel panel;
    private Player player;
    private GridBagConstraints constraints;
    private Ingredient[] ingredients;
    private JPanel ingredientsPanel = new JPanel();

    public AlchemyTableWindow(AlchemyTable parent){
        super("Алхимический стол");
        setAlwaysOnTop(true);
        alchemyTable = parent;
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
        ingredients = new Ingredient[]{null, null, null, null, null, null};

        int alchemyLevel = 0;
        if(player != null && player.hasAbility(new Alchemist())){
            alchemyLevel = player.getAbility(new Alchemist()).getLevel();
        }

        ingredientsPanel.removeAll();
        ingredientsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints ingredientsConstraints = new GridBagConstraints();

        ingredientsConstraints.anchor = GridBagConstraints.NORTH;
        ingredientsConstraints.insets = new Insets(10, 10, 10, 10);
        ingredientsConstraints.gridx = 0;
        ingredientsConstraints.gridy = 0;
        for(int i = 0; i < 3 + alchemyLevel; i++){
            JButton button = new JButton("Ингридиент");
            button.setPreferredSize(new Dimension(180, 120));
            button.setMaximumSize(new Dimension(180, 120));
            button.setMinimumSize(new Dimension(180, 120));
            int finalI = i;
            button.addActionListener(e -> {
                IngredientChooser ingredientChooser = new IngredientChooser(player, ingredients, finalI, button);
            });
            ingredientsPanel.add(button, ingredientsConstraints);

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
        JButton create = new JButton("Создать");
        create.addActionListener(e -> {
            alchemyTable.create(ingredients);
            for(Ingredient ingredient : ingredients){
                player.removeItem(ingredient);
            }
            if(alchemyTable.getCreatedPotion() != null){
                player.addItemToInventory(alchemyTable.getCreatedPotion());
            }
            player.getFieldWindow().drawAllPlayerWindow();
            close();
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
