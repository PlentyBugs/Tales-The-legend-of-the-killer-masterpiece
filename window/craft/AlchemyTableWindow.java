package window.craft;

import conversation.TablePart;
import creature.Player;
import item.Item;
import item.alchemy.ingredient.Ingredient;
import support.AbilityProperty;
import thing.craft.AlchemyTable;
import utils.KeyBinder;
import utils.PanelProvider;
import window.MultiWindow;
import window.Screen;
import window.Switcher;
import window.WindowInterface;
import window.conversation.AbstractShop;
import window.player.UnfocusedButton;
import window.support.component.IngredientButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AlchemyTableWindow extends CraftWindow {

    private final JPanel panel = new JPanel(new GridBagLayout());
    private final IngredientButton[] buttons;
    private final AlchemyTable alchemyTable;
    private final Ingredient[] ingredients;
    private final Box ingredientsPanel;
    private final int btnSize = WIDTH / 6;
    private int currentSlot = 0;

    public AlchemyTableWindow(AlchemyTable parent) {
        alchemyTable = parent;
        buttons = new IngredientButton[]{null, null, null, null, null, null};
        ingredients = new Ingredient[]{null, null, null, null, null, null};
        ingredientsPanel = Box.createHorizontalBox();

        Dimension preferredSize = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(preferredSize);
        setMaximumSize(preferredSize);
        setMinimumSize(preferredSize);

        setLayout(new BorderLayout());

        drawWindow();
        KeyBinder.bindEscape(this, () -> close(Screen.GAME));
        setVisible(true);
    }

    public void drawWindow() {
        panel.removeAll();
        GridBagConstraints gbc = PanelProvider.getEmptyGBC();
        int alchemyLevel = 0;
        if(player != null && player.hasAbility(AbilityProperty.ALCHEMIST)){
            alchemyLevel = player.getAbility(AbilityProperty.ALCHEMIST).getLevel();
        }
        IngredientChooser chooser = new IngredientChooser(player, multiWindow);

        ingredientsPanel.removeAll();
        Dimension buttonSize = new Dimension(btnSize, btnSize);
        ingredientsPanel.setPreferredSize(new Dimension(WIDTH, btnSize));
        ingredientsPanel.setMaximumSize(new Dimension(WIDTH, btnSize));
        ingredientsPanel.setMinimumSize(new Dimension(WIDTH, btnSize));
        int slotCount = 3 + alchemyLevel;
        for (int i = 0; i < 6; i++) {
            IngredientButton button = new IngredientButton("Ингредиент");
            buttons[i] = button;
            customizeButton(button);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
            if (i < slotCount) {
                int finalI = i;
                button.addActionListener((ActionListener & Serializable)e -> {
                    currentSlot = finalI;
                    chooser.printItems();
                });
            } else {
                button.setText("");
                button.setBackground(STYLED_COLOR_DARK.darker());
            }
            ingredientsPanel.add(buttons[i]);
        }
        panel.add(ingredientsPanel, gbc);
        gbc.gridy++;

        UnfocusedButton create = new UnfocusedButton("Создать");
        customizeButton(create);
        create.setSize(WIDTH, (HEIGHT - btnSize) / 9);
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
        panel.add(create, gbc);
        gbc.gridy++;
        panel.add(chooser, gbc);
        add(panel);
        chooser.printItems();
    }

    private final class IngredientChooser extends AbstractShop {

        public IngredientChooser(Player player, Switcher switcher) {
            super(
                    0,
                    player,
                    null,
                    switcher,
                    new String[]{"Название", "Известные эффекты", "Цена", "Количество", ""},
                    new Class[]{String.class, String.class, String.class, String.class, JButton.class}
            );
            add(choose, BorderLayout.NORTH);
            scroll = new JScrollPane(itemStock);
            Dimension scrollSize = new Dimension(width, (HEIGHT - btnSize) * 10 / 9);
            scroll.setMinimumSize(scrollSize);
            scroll.setPreferredSize(scrollSize);
            scroll.setMaximumSize(scrollSize);
            customizeScroll(scroll);

            add(scroll, BorderLayout.SOUTH);
            setVisible(true);
        }

        @Override
        protected void printItems() {
            if (player == null) return;
            dtm.clear();
            Map<Item, Integer> map = new HashMap<>();
            for(Item item : player.getInventory()) {
                if (item.getCost() == 0) {
                    item.countCost();
                }
                map.put(item, map.getOrDefault(item, 0) + 1);
            }
            map.forEach((item, count) -> {
                if (item instanceof Ingredient ingredient) {
                    UnfocusedButton button = new UnfocusedButton("Использовать");
                    button.addActionListener((e) -> {
                        ingredients[currentSlot] = ingredient;
                        IngredientButton currentButton = buttons[currentSlot];
                        if (currentButton != null) {
                            currentButton.setBackground(ingredient.getColor());
                            currentButton.setIngredient((Ingredient)item);
                            currentButton.setCountOfIngredients(count);
                            currentButton.writeText();
                        }
                    });
                    customizeButton(button);
                    TablePart tablePart = new TablePart(
                        ingredient.getName(),
                        ingredient.getItemProperty(),
                        ingredient.getCost(),
                        count,
                        button
                    );
                    dtm.addRow(tablePart);
                }
            });
        }
    }
}
