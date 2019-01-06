package JGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ChooseNameWindow extends JFrame {

    private JTextArea jTextArea;
    private String name = "";
    private boolean check = false;

    ChooseNameWindow(){
        super("Назовите себя");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300,78));

        jTextArea = new JTextArea();
        jTextArea.setSize(280,40);
        jTextArea.setLocation(10,10);
        jTextArea.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(jTextArea, BorderLayout.NORTH);

        JButton button = new JButton("Так меня зовут");
        button.setSize(280,80);
        button.setLocation(10,80);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name = jTextArea.getText();
                check = true;
                if (name.equals("")){
                    name = "Безымянный";
                }
            }
        });
        getContentPane().add(button, BorderLayout.SOUTH);

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
