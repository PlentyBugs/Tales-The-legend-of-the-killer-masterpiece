package Windows.BattleWindows;

import javax.swing.*;
import java.io.Serializable;

public class LossWindow extends JFrame implements Serializable {

    public LossWindow(){
        super("Вы проиграли");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Вы проиграли");

        getContentPane().add(label);
        pack();
        setVisible(true);
    }
}
