package Windows.BattleWindows;

import Creatures.LiveCreature;
import Creatures.Player;
import Items.Alchemy.Potions.Potion;
import Items.BattleItem;
import Items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerFightItemWindow extends JFrame implements Serializable {

    private Player player;
    private LiveCreature enemy;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JScrollPane scroll = new JScrollPane(panel);
    private GridBagConstraints constraints;
    private FightWindow fightWindow;
    private int width = 720;
    private int height = 720;
    private ArrayList<Item> uniqueInventory = new ArrayList<>();


    public PlayerFightItemWindow(Player player, LiveCreature enemy, FightWindow fightWindow){
        super("Предметы");
        this.player = player;
        this.enemy = enemy;
        this.fightWindow = fightWindow;
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

        drawWindow();
    }

    public void drawWindow(){
        getContentPane().remove(scroll);

        panel = new JPanel(new GridBagLayout());
        scroll = new JScrollPane(panel);
        constraints = new GridBagConstraints();

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

                Color colorBackground = new Color(255,255,255,255);
                Color colorForeground = new Color(0,0,0);

                switch(item.getGrade()){
                    case COMMON: colorForeground = new Color(0,0,0); break;
                    case MAGIC: colorForeground = new Color(67, 162,255); break;
                    case CURSE: colorForeground = new Color(1,155, 24); break;
                    case ARTIFACT: colorForeground = new Color(255, 0, 18); break;
                    case HEROIC: colorForeground = new Color(255, 96, 0); break;
                    case ABOVETHEGODS: colorForeground = new Color(255, 0, 197); break;
                    default:  colorForeground = new Color(0,0,0); break;
                }

                switch(item.getRarity()){
                    case COMMON: colorBackground = new Color(255,255,255,100); break;
                    case UNCOMMON: colorBackground = new Color(0, 115,255,100); break;
                    case RARE: colorBackground = new Color(12, 0,255,100); break;
                    case MYSTICAL: colorBackground = new Color(255, 0, 119,100); break;
                    case LEGENDARY: colorBackground = new Color(255, 232, 0,100); break;
                    case DRAGON: colorBackground = new Color(255, 9, 0,100); break;
                    case DIVINE: colorBackground = new Color(255, 169, 0,100); break;
                    default:  colorBackground = new Color(255,255,255,100); break;
                }

                JLabel itemName = new JLabel(item.getName());
                JLabel itemQuality = new JLabel("Прочность: " + Double.toString(item.getQuality()));
                JLabel property = new JLabel();

                if (item.getClass().toString().contains("Potions")){
                    property.setText("Мощность: ");
                    propertyCount.setText(Integer.toString(((Potion)item).getEffect().getPower()));
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

                JButton useButton = new JButton("Использовать");

                useButton.addActionListener((ActionListener & Serializable)  e -> {
                    if(item.getClass().toString().contains("Heal") || item.getClass().toString().contains("Power") || item.getClass().toString().contains("StatsUp")){
                        ((Potion)item).use(player);
                        fightWindow.enemyTurn();
                        player.removeItem(item);
                        fightWindow.writeToPlayerConsole(player.getName() + " использовал " + item.getName());
                    } else if(item.getClass().toString().contains("Poison")){
                        ((Potion)item).use(enemy);
                        fightWindow.enemyTurn();
                        player.removeItem(item);
                        fightWindow.writeToPlayerConsole(player.getName() + " использовал " + item.getName());
                        fightWindow.writeToEnemyStatusConsole("У " + enemy.getName() + " осталось " + enemy.getHp());
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
