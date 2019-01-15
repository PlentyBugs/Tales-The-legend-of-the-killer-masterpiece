package Windows.BattleWindows;

import javax.swing.*;

public class LossWindow extends JFrame {

    public LossWindow(){
        super("Вы проиграли");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Вы проиграли");

        getContentPane().add(label);
        pack();
        setVisible(true);
    }
}
