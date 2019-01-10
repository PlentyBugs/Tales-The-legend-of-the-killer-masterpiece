package JGame.Windows;

import JGame.LiveCreatures.GodCreature;
import JGame.Map;
import JGame.LiveCreatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private int x, y;
    private int counter = -1;
    private int vision;
    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private Map currentMap;

    Console console = new Console();

    public Window(String name, int x, int y, int vision, Player player, GodCreature[][] information) {
        super(name);

        this.player = player;

        this.vision = vision;
        this.x = x;
        this.y = y;
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(x,y));
        setPreferredSize(new Dimension(x,y));

        drawField(information);

        getContentPane().add(panel);
        getContentPane().add(console, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public void drawField(GodCreature[][] information){

        int realVision = vision*2+1;
        int width = (int)(x*0.95)/realVision;
        int height = (int)(y*0.7)/realVision;

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2, 2, 2, 2);

        for (int i = 0; i < realVision; i++){
            for (int j = 0; j < realVision; j++) {
                constraints.gridx = j;
                constraints.gridy = i;
                boolean isStep = information[i][j].getIsStep();
                int X = information[i][j].getX();
                int Y = information[i][j].getY();
                String info = information[i][j].getName();
                JButton button = new JButton(info);
                button.setBackground(information[i][j].getColor());
                button.setPreferredSize(new Dimension(width, height));
                button.setLocation((width+5)*j + 8,(height+5)*i + 5);

                if (information[i][j].getIsPlayer()){
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if(!player.getIsManagerOpen()){
                                player.setManagerWindowIsVisible(true);
                                player.setManagerOpen(true);
                            } else {
                                player.setManagerWindowIsVisible(false);
                                player.setManagerOpen(false);
                            }
                        }
                    });
                } else {
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (isStep){
                                player.setX(X);
                                player.setY(Y);
                                drawMap();
                            }
                        }
                    });
                }

                panel.add(button, constraints);
            }
        }
    }

    public void writeToConsole(String text) throws InterruptedException {
        console.writeToConsole(text);
    }

    public void drawMap(){
        getContentPane().remove(panel);
        panel = new JPanel(new GridBagLayout());
        drawField(currentMap.getMap(player.getX(),player.getY()));
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

    public Console getConsole() {
        return console;
    }
}
