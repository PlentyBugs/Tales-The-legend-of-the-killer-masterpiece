package Windows.SupportWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class ChooseNameWindow extends JFrame implements Serializable {

    private JTextArea jTextArea;
    private String name = "";
    private boolean check = false;

    public ChooseNameWindow(){
        super("Назовите себя");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300,100));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 10, 0);

        jTextArea = new JTextArea();
        jTextArea.setSize(280,40);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(jTextArea, constraints);

        JButton button = new JButton("Так меня зовут");
        button.setSize(280,80);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name = jTextArea.getText();
                check = true;
                if (name.equals("")){
                    name = "Безымянный";
                }
            }
        });
        constraints.gridy = 1;
        panel.add(button, constraints);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public String getName(){
        return name;
    }

    public boolean getCheck(){
        return check;
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
