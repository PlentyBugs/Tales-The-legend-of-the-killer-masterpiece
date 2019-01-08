package JGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class InventoryWindow extends JFrame {

    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());;
    private GridBagConstraints constraints;

    public InventoryWindow(Player player){
        super("Инвентарь");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300,300));

        this.player = player;
        drawInventory();
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        drawInventory();
        setVisible(b);
    }

    private void drawInventory(){

        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for (Item item : player.getInventory()){

            JPanel itemPanel = new JPanel();
            GridBagConstraints itemConstraints = new GridBagConstraints();
            itemConstraints.anchor = GridBagConstraints.WEST;
            itemConstraints.insets = new Insets(20, 10, 20, 10);
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;

            Color colorBackground = new Color(0,0,0,150);
            Color colorForeground = new Color(255,0,0);

            JLabel itemName = new JLabel(item.name);
            itemConstraints.gridx = 1;
            JLabel itemQuality = new JLabel(Integer.toString(item.quality));

            itemName.setForeground(colorForeground);
            itemQuality.setForeground(colorForeground);

            itemPanel.add(itemName, itemConstraints);
            itemPanel.add(itemQuality, itemConstraints);


            itemPanel.setBackground(colorBackground);

            panel.add(itemPanel, constraints);

            constraints.gridy ++;
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
