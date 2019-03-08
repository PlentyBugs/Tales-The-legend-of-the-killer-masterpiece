package Windows.BattleWindows;

import javax.swing.*;
import java.io.Serializable;

public class LossWindow extends JFrame implements Serializable {

    public LossWindow(){
        super("Вы проиграли");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setAlwaysOnTop(true);

        JLabel label = new JLabel("Вы проиграли");

        getContentPane().add(label);
        pack();
        setVisible(true);
    }
}
