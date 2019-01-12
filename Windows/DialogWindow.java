package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class DialogWindow extends JFrame {

    public DialogWindow(String message){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();


        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(String text : message.split("\n")){
            JLabel label = new JLabel(text);
            panel.add(label, constraints);
            constraints.gridy ++;
        }
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        setVisible(b);
    }
}
