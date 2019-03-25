package Windows.CraftWindow;

import Abilities.Passive.Professions.BlackSmith;
import Creatures.Player;
import Items.BlackSmith.Resource.Resource;
import Things.Craft.Anvil;
import Windows.SupportWindows.SupportComponents.BluePrintButton;
import Windows.SupportWindows.SupportComponents.ResourceButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class AnvilTableWindow extends JFrame {

    private Player player;
    private Anvil anvil;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private int width = 480;
    private int height = 480;
    private Resource res;

    public AnvilTableWindow(Anvil anvil){
        super("Наковальня");
        this.anvil = anvil;

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
        JLabel materialTemp = new JLabel("Температура материала: -");
        resDescPanel.add(materialTemp, constraintsResDescPanel);
        constraintsResDescPanel.gridy ++;

        JPanel buttons = new JPanel(new BorderLayout());

        BluePrintButton bluePrint = new BluePrintButton("Чертеж");
        bluePrint.addActionListener(e -> new BluePrintChooser(player, bluePrint));
        bluePrint.setPreferredSize(new Dimension(width/2, (height-120)/2));
        bluePrint.setMinimumSize(new Dimension(width/2, (height-120)/2));
        bluePrint.setMaximumSize(new Dimension(width/2, (height-120)/2));

        ResourceButton resource = new ResourceButton("Ресурс");
        if(res != null){
            resource.setResource(res);
            resource.setText(res.getName());
            materialTemp.setText("Температура материала: " + resource.getResource().getTemperature());
        }
        resource.addActionListener(e -> new ResourceChooser(player, resource));
        resource.setPreferredSize(new Dimension(width/2, (height-120)/2));
        resource.setMinimumSize(new Dimension(width/2, (height-120)/2));
        resource.setMaximumSize(new Dimension(width/2, (height-120)/2));

        buttons.add(bluePrint, BorderLayout.NORTH);
        buttons.add(resource, BorderLayout.SOUTH);

        resPanel.add(resDescPanel, BorderLayout.WEST);
        if(player != null && player.hasAbility(new BlackSmith())){
            resPanel.add(buttons, BorderLayout.EAST);
        } else {
            resPanel.add(new Label("Я еще не умею этого делать"), BorderLayout.EAST);
        }

        JButton use = new JButton("Создать");
        use.addActionListener(e -> {
            if(resource.getResource() != null && bluePrint.getBluePrint() != null){
                if(bluePrint.getBluePrint().getTemperature() <= resource.getResource().getTemperature() && bluePrint.getBluePrint().hasResource(resource.getResource())){
                    res = resource.getResource();
                    anvil.setBluePrint(bluePrint.getBluePrint());
                    anvil.create(res);
                    player.removeItem(res);
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
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void setPlayer(Player player) {
        this.player = player;
        drawWindow();
    }

    public void setIsVisible(boolean visible){
        drawWindow();
        setVisible(visible);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
