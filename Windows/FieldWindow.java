package Windows;

import LiveCreatures.GodCreature;
import LiveCreatures.LiveCreature;
import LiveCreatures.Player;
import Locations.Map;
import Things.Grass;
import Things.HealBlock;
import Windows.SupportWindows.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class FieldWindow extends JFrame {
    private int x, y;
    private int counter = -1;
    private int vision;
    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private Map currentMap;

    Console console = new Console();

    public FieldWindow(String name, int x, int y, int vision, Player player, GodCreature[][] information) {
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

                boolean isLiveCreature = information[i][j].getClass().toString().split("\\.")[0].split(" ")[1].equals("LiveCreatures");
                boolean isHealBlock = information[i][j].getClass().toString().split("\\.")[1].split(" ")[0].equals("HealBlock");
                boolean isDoorToUpperLevel = information[i][j].getClass().toString().split("\\.")[1].split(" ")[0].equals("DoorToUpperLevelLocation");

                GodCreature liveCreature = information[i][j];

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
                } else if (isHealBlock){
                    button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        currentMap.setElementByCoordinates(liveCreature.getX(), liveCreature.getY(), new Grass());
                        ((HealBlock)liveCreature).heal(player);
                        int healBlockY = (int)(Math.random()*(currentMap.getMapHeight()-1));
                        int healBlockX = (int)(Math.random()*(currentMap.getMapWidth()-1));
                        currentMap.setElementByCoordinates(healBlockX, healBlockY, new HealBlock(healBlockX, healBlockY));
                        drawMap();
                    }
                });
                } else if (isDoorToUpperLevel) {
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            currentMap = new Map(player, player.getLvl()*50, player.getLvl()*60);
                            player.setX(0);
                            player.setY(0);
                            drawMap();
                        }
                    });
                }else {
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (isStep){
                                player.setX(X);
                                player.setY(Y);
                                drawMap();
                            } else if (isLiveCreature){
                                if (liveCreature.getChooseEnemyWindow() == null){
                                    liveCreature.setChooseEnemyWindow(player, FieldWindow.this, (LiveCreature) liveCreature);
                                }
                                if(!liveCreature.getIsChooseEnemyWindowOpen()){
                                    liveCreature.setChooseEnemyWindowIsVisible(true);
                                    liveCreature.setChooseEnemyWindowOpen(true);
                                } else {
                                    liveCreature.setChooseEnemyWindowIsVisible(false);
                                    liveCreature.setChooseEnemyWindowOpen(false);
                                }
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

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        setVisible(b);
    }

    public Map getCurrentMap() {
        return currentMap;
    }
}
