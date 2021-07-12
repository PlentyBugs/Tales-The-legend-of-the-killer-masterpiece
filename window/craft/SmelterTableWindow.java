package window.craft;

import item.blacksmith.resource.Resource;
import thing.craft.Smelter;
import utils.KeyBinder;
import window.Screen;
import window.player.UnfocusedButton;
import window.support.component.ResourceButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

// todo: completely rework in future
public class SmelterTableWindow extends CraftWindow {

    private final Smelter smelter;
    private JPanel panel = new JPanel(new GridBagLayout());
    private Resource res;

    public SmelterTableWindow(Smelter smelter){
        this.smelter = smelter;

        Dimension preferredSize = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(preferredSize);
        setMaximumSize(preferredSize);
        setMinimumSize(preferredSize);

        setLayout(new BorderLayout());

        drawWindow();
        KeyBinder.bindEscape(this, () -> close(Screen.GAME));
        setVisible(true);
    }

    public void drawWindow(){
        panel.removeAll();
        panel = new JPanel(new GridBagLayout());
        int width = 480;
        int height = 480;
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        GridBagConstraints constraints = new GridBagConstraints();

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
        resDescPanel.setPreferredSize(new Dimension(width /2, height -120));
        resDescPanel.setMinimumSize(new Dimension(width /2, height -120));
        resDescPanel.setMaximumSize(new Dimension(width /2, height -120));
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
//        resource.addActionListener((ActionListener & Serializable) e -> new ResourceChooser(player, resource));
        resource.setPreferredSize(new Dimension(width /2, height -120));
        resource.setMinimumSize(new Dimension(width /2, height -120));
        resource.setMaximumSize(new Dimension(width /2, height -120));
        resPanel.add(resDescPanel, BorderLayout.WEST);
        resPanel.add(resource, BorderLayout.EAST);

        JButton use = new UnfocusedButton("Плавить");
        use.addActionListener((ActionListener & Serializable)e -> {
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
        add(panel);
        setVisible(true);
    }
}
