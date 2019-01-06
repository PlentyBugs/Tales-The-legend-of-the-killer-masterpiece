package JGame;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private int x, y;
    private int counter = -1;
    private int vision;

    Console console = new Console();

    Window(String name, int x, int y, int vision, String[][] information) {
        super(name);

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

    public void drawField(int vision, String[][] information){
        int realVision = vision*2+1;
        int width = (int)(x*0.95)/realVision;
        int height = (int)(y*0.7)/realVision;
        for (int i = 0; i < realVision; i++){
            for (int j = 0; j < realVision; j++) {
                String info = information[i][j];
                if (i == j && i == vision){
                    info = "Вы";
                }
                JButton button = new JButton(info);
                button.setSize(width, height);
                button.setLocation((width+5)*j + 8,(height+5)*i + 5);
                getContentPane().add(button, BorderLayout.SOUTH);
            }
        }
    }

    public void writeToConsole(String text) throws InterruptedException {
        console.writeToConsole(text);
    }

}
