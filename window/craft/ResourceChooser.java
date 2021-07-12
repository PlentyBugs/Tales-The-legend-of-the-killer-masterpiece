package window.craft;

import creature.Player;
import item.blacksmith.resource.Resource;
import item.Item;
import window.player.UnfocusedButton;
import window.support.component.ResourceButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class ResourceChooser extends JFrame implements Serializable {
//    private ArrayList<Item> uniqueInventory = new ArrayList<>();
//
//    public ResourceChooser(Player player, ResourceButton buttonParent){
//        setAlwaysOnTop(true);
//        JPanel panel = new JPanel(new GridBagLayout());
//        GridBagConstraints constraints;
//        constraints = new GridBagConstraints();
//
//        constraints.anchor = GridBagConstraints.NORTH;
//        constraints.insets = new Insets(5, 0, 0, 0);
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//
//        for(Item item : player.getInventory()){
//            if(!uniqueInventoryContains(item)){
//                uniqueInventory.add(item);
//            } else if(player.countOfItemInInventory(item) > 1){
//                continue;
//            }
//            if(!(item instanceof Resource)){
//                continue;
//            }
//
//            int count = player.countOfItemInInventory(item);
//            JButton button = new UnfocusedButton(item.getName() + " Количество: " + count);
//            button.addActionListener((ActionListener & Serializable) e -> {
//                buttonParent.setResource((Resource)item);
//                buttonParent.setCountOfResources(count);
//                buttonParent.writeText();
//                close();
//            });
//            panel.add(button,constraints);
//            constraints.gridy ++;
//        }
//
//        getContentPane().add(panel);
//        pack();
//        setVisible(true);
//    }
//
//    public void close(){
//        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
//    }
//
//    private boolean uniqueInventoryContains(Item item){
//        for(Item itm : uniqueInventory){
//            if(itm.compareTo(item) == 0){
//                return true;
//            }
//        }
//        return false;
//    }
}
