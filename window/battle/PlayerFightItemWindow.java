package window.battle;

import creature.LiveCreature;
import creature.Player;
import item.alchemy.potion.*;
import item.BattleItem;
import item.Item;
import window.player.UnfocusedButton;
import utils.ColoringProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerFightItemWindow extends JFrame implements Serializable, ColoringProfile {

    private final Player player;
    private final LiveCreature enemy;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JScrollPane scroll = new JScrollPane(panel);
    private final FightWindow fightWindow;
    private final int width = 720;
    private final ArrayList<Item> uniqueInventory = new ArrayList<>();


    public PlayerFightItemWindow(Player player, LiveCreature enemy, FightWindow fightWindow){
        super("Предметы");
        this.player = player;
        this.enemy = enemy;
        this.fightWindow = fightWindow;
        int height = 720;
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

        drawWindow();
    }

    public void drawWindow(){
        getContentPane().remove(scroll);

        panel = new JPanel(new GridBagLayout());
        scroll = new JScrollPane(panel);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(Item item : player.getInventory()){
            if(item instanceof BattleItem){

                if(!uniqueInventoryContains(item)){
                    uniqueInventory.add(item);
                } else if(player.countOfItemInInventory(item) > 1 && item.getStackable()){
                    continue;
                }

                JPanel itemPanel = new JPanel();
                itemPanel.setPreferredSize(new Dimension(width, 40));
                GridBagConstraints itemConstraints = new GridBagConstraints();
                itemConstraints.anchor = GridBagConstraints.WEST;
                itemConstraints.insets = new Insets(20, 10, 20, 10);
                itemConstraints.gridx = 0;
                itemConstraints.gridy = 0;
                JLabel propertyCount = new JLabel();

                Color colorBackground;
                new Color(0, 0, 0);
                Color colorForeground = getColorByGrade(item);
                colorBackground = getColorByRarity(item);

                JLabel itemName = new JLabel(item.getName());
                JLabel itemQuality = new JLabel("Прочность: " + item.getQuality());
                JLabel property = new JLabel();

                if (item instanceof Potion potion){
                    property.setText("Мощность: ");
                    propertyCount.setText(Integer.toString(potion.getEffect().getPower()));
                }

                int count = player.countOfItemInInventory(item);
                JLabel ccunt = new JLabel();
                if(count > 1 && item.getStackable()){
                    ccunt = new JLabel("Количество: " + count);
                }

                itemName.setFont(new Font("Serif", Font.PLAIN, 12));
                itemQuality.setFont(new Font("Serif", Font.PLAIN, 12));
                property.setFont(new Font("Serif", Font.PLAIN, 12));
                propertyCount.setFont(new Font("Serif", Font.PLAIN, 12));

                itemName.setForeground(colorForeground);
                itemQuality.setForeground(colorForeground);
                property.setForeground(colorForeground);
                propertyCount.setForeground(colorForeground);

                JButton useButton = new UnfocusedButton("Использовать");

                useButton.addActionListener((ActionListener & Serializable)  e -> {
                    if(item instanceof PoisonPotion poison){
                        poison.use(enemy);
                        fightWindow.enemyTurn();
                        player.removeItem(item);
                        fightWindow.writeToPlayerConsole(player.getName() + " использовал " + item.getName());
                        fightWindow.writeToEnemyStatusConsole("У " + enemy.getName() + " осталось " + enemy.getHp());
                    } else if(item instanceof Potion potion){
                        potion.use(player);
                        fightWindow.enemyTurn();
                        player.removeItem(item);
                        fightWindow.writeToPlayerConsole(player.getName() + " использовал " + item.getName());
                    }
                    drawWindow();
                });

                useButton.setSize(100,40);

                itemPanel.add(itemName, itemConstraints);
                itemConstraints.gridx ++;
                itemPanel.add(itemQuality, itemConstraints);
                itemConstraints.gridx ++;
                itemPanel.add(property, itemConstraints);
                itemConstraints.gridx ++;
                itemPanel.add(propertyCount, itemConstraints);
                itemConstraints.gridx ++;
                itemPanel.add(ccunt, itemConstraints);
                itemConstraints.gridx ++;
                itemPanel.add(useButton, itemConstraints);
                itemConstraints.gridx ++;

                itemPanel.setBackground(colorBackground);

                panel.add(itemPanel, constraints);

                constraints.gridy ++;
            }
        }

        getContentPane().add(scroll);
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
