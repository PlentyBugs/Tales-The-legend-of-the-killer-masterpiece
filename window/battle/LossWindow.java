package window.battle;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class LossWindow extends JFrame implements Serializable {

    public LossWindow(){
        super("Вы проиграли");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setAlwaysOnTop(true);

        JLabel label = new JLabel("Вы проиграли");
        label.setFont(new Font("Default", Font.BOLD, 150));

        getContentPane().add(label);
        pack();
        setVisible(true);
    }
}
