package JGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private int x, y;
    private int counter = -1;
    private int vision;
    private Player player;

    Console console = new Console();

    Window(String name, int x, int y, int vision, Player player, GodCreature[][] information) {
        super(name);

        this.player = player;

        this.vision = vision;
        this.x = x;
        this.y = y;
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(x,y));

        drawField(vision, information);

        getContentPane().add(console, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public void drawField(int vision, GodCreature[][] information){
        int realVision = vision*2+1;
        int width = (int)(x*0.95)/realVision;
        int height = (int)(y*0.7)/realVision;
        for (int i = 0; i < realVision; i++){
            for (int j = 0; j < realVision; j++) {
                boolean isStep = information[i][j].getIsStep();
                int X = information[i][j].getX();
                int Y = information[i][j].getY();
                String info = information[i][j].getName();
                JButton button = new JButton(info);
                button.setBackground(information[i][j].getColor());
                button.setSize(width, height);
                button.setLocation((width+5)*j + 8,(height+5)*i + 5);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (isStep){
                            player.x = X;
                            player.y = Y;
                        }
                    }
                });

                getContentPane().add(button, BorderLayout.SOUTH);
            }
        }
    }

    public void writeToConsole(String text) throws InterruptedException {
        console.writeToConsole(text);
    }

}
