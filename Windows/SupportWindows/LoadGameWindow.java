package Windows.SupportWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

public class LoadGameWindow extends JFrame {

    private String fileName;

    public LoadGameWindow(ArrayList<File> files){
        super("Загрузка");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel title = new JLabel("Выберите сохранение");
        panel.add(title, constraints);
        constraints.gridy ++;

        for (File file : files){
            constraints.gridx = 0;
            JLabel name = new JLabel(file.getName().substring(0, file.getName().length()-4));
            panel.add(name, constraints);
            constraints.gridx ++;
            JButton loadButton = new JButton("Загрузитть");
            loadButton.addActionListener(e -> fileName = file.getName());
            panel.add(loadButton, constraints);
            constraints.gridy ++;
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public String getFileName() {
        return fileName;
    }
}
