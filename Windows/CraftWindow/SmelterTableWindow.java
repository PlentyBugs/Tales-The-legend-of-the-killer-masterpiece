package Windows.CraftWindow;

import Creatures.Player;
import Items.BlackSmith.Resource.Resource;
import Things.Craft.Smelter;
import Windows.SupportWindows.SupportComponents.ResourceButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class SmelterTableWindow extends JFrame {

    private Player player;
    private Smelter smelter;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private int width = 480;
    private int height = 480;
    private Resource res;

    public SmelterTableWindow(Smelter smelter){
        super("Плавильня");
        setAlwaysOnTop(true);
        this.smelter = smelter;

        drawWindow();
    }

    public void drawWindow(){
        getContentPane().remove(panel);
        panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JPanel resPanel = new JPanel(new BorderLayout());
        JPanel resDescPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsResDescPanel = new GridBagConstraints();
        constraintsResDescPanel.anchor = GridBagConstraints.NORTH;
        constraintsResDescPanel.insets = new Insets(0, 0, 0, 0);
        constraintsResDescPanel.gridx = 0;
        constraintsResDescPanel.gridy = 0;
        resDescPanel.setPreferredSize(new Dimension(width/2, height-120));
        resDescPanel.setMinimumSize(new Dimension(width/2, height-120));
        resDescPanel.setMaximumSize(new Dimension(width/2, height-120));
        JLabel power = new JLabel("Сила плавки(Градус): " + smelter.getPower());
        resDescPanel.add(power, constraintsResDescPanel);
        constraintsResDescPanel.gridy ++;
        JLabel maxTemp = new JLabel("Макс температура плавильни: " + smelter.getMaxTemperature());
        resDescPanel.add(maxTemp, constraintsResDescPanel);
        constraintsResDescPanel.gridy ++;
        JLabel materialTemp = new JLabel("Температура материала: -");
        resDescPanel.add(materialTemp, constraintsResDescPanel);
        constraintsResDescPanel.gridy ++;
        JLabel materialMaxTemp = new JLabel("Температура плавления м-ла: -");
        resDescPanel.add(materialMaxTemp, constraintsResDescPanel);

        ResourceButton resource = new ResourceButton("Ресурс");
        if(res != null){
            resource.setResource(res);
            resource.setText(res.getName());
            materialTemp.setText("Температура материала: " + resource.getResource().getTemperature());
            materialMaxTemp.setText("Температура плавления м-ла: " + resource.getResource().getMaxTemperature());
        }
        resource.addActionListener(e -> new ResourceChooser(player, resource));
        resource.setPreferredSize(new Dimension(width/2, height-120));
        resource.setMinimumSize(new Dimension(width/2, height-120));
        resource.setMaximumSize(new Dimension(width/2, height-120));
        resPanel.add(resDescPanel, BorderLayout.WEST);
        resPanel.add(resource, BorderLayout.EAST);

        JButton use = new JButton("Плавить");
        use.addActionListener(e -> {
            if(resource.getResource() != null){
                res = resource.getResource();
                smelter.create(resource.getResource());
                materialTemp.setText("Температура материала: " + resource.getResource().getTemperature());
                materialMaxTemp.setText("Температура плавления м-ла: " + resource.getResource().getMaxTemperature());
                drawWindow();
            }
        });
        use.setPreferredSize(new Dimension(width, 120));
        use.setMinimumSize(new Dimension(width, 120));
        use.setMaximumSize(new Dimension(width, 120));
        use.setBackground(Color.red);

        panel.add(resPanel, constraints);
        constraints.gridy ++;
        panel.add(use, constraints);
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setIsVisible(boolean visible){
        drawWindow();
        setVisible(visible);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
