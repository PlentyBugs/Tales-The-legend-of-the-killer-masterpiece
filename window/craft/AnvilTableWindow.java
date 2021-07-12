package window.craft;

import creature.Player;
import thing.craft.Anvil;
import utils.KeyBinder;
import window.Screen;
import window.player.UnfocusedButton;
import window.support.component.BluePrintButton;
import window.support.component.ResourceButton;
import support.AbilityProperty;
import utils.PanelProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;

// todo: completely rework in future
public class AnvilTableWindow extends CraftWindow {

    private final Anvil anvil;
    private JPanel panel = new JPanel(new GridBagLayout());

    public AnvilTableWindow(Anvil anvil){
        this.anvil = anvil;

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
        GridBagConstraints constraintsResDescPanel = PanelProvider.getEmptyGBC();
        resDescPanel.setPreferredSize(new Dimension(width /2, height -120));
        resDescPanel.setMinimumSize(new Dimension(width /2, height -120));
        resDescPanel.setMaximumSize(new Dimension(width /2, height -120));
        JLabel materialTemp = new JLabel("Температура материала: -");
        resDescPanel.add(materialTemp, constraintsResDescPanel);
        constraintsResDescPanel.gridy ++;

        JPanel buttons = new JPanel(new BorderLayout());

        BluePrintButton bluePrint = new BluePrintButton("Чертеж");
        bluePrint.addActionListener((ActionListener & Serializable) e -> new BluePrintChooser(player, bluePrint));
        bluePrint.setPreferredSize(new Dimension(width /2, (height -120)/2));
        bluePrint.setMinimumSize(new Dimension(width /2, (height -120)/2));
        bluePrint.setMaximumSize(new Dimension(width /2, (height -120)/2));

        ResourceButton resource = new ResourceButton("Ресурс");
//        resource.addActionListener((ActionListener & Serializable)e -> new ResourceChooser(player, resource));
        resource.setPreferredSize(new Dimension(width /2, (height -120)/2));
        resource.setMinimumSize(new Dimension(width /2, (height -120)/2));
        resource.setMaximumSize(new Dimension(width /2, (height -120)/2));

        buttons.add(bluePrint, BorderLayout.NORTH);
        buttons.add(resource, BorderLayout.SOUTH);

        resPanel.add(resDescPanel, BorderLayout.WEST);
        if(player != null && player.hasAbility(AbilityProperty.BLACKSMITH)){
            resPanel.add(buttons, BorderLayout.EAST);
        } else {
            resPanel.add(new Label("Я еще не умею этого делать"), BorderLayout.EAST);
        }

        JButton use = new UnfocusedButton("Создать");
        use.addActionListener((ActionListener & Serializable)e -> {
            if(resource.getResource() != null && bluePrint.getBluePrint() != null){
                if(bluePrint.getBluePrint().getTemperature() <= resource.getResource().getTemperature() && bluePrint.getBluePrint().hasResource(resource.getResource())){
                    anvil.setBluePrint(bluePrint.getBluePrint());
                    anvil.create(resource.getResource());
                    player.removeItem(resource.getResource());
                    drawWindow();
                }
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
