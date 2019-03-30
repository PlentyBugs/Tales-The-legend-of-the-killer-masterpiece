package Windows.SupportWindows.SupportComponents;

import Creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;

public class SavePanel extends JFrame {

    private Player player;
    private JPanel panel;

    public SavePanel(Player player){
        this.player = player;
        drawWindow();
    }

    public void drawWindow(){
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 20, 0, 20);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JTextArea saveName = new JTextArea();
        saveName.setPreferredSize(new Dimension(140,20));
        saveName.setMinimumSize(new Dimension(140,20));
        saveName.setMaximumSize(new Dimension(140,20));
        JButton saveButton = new JButton("Сохранить");
        saveButton.setPreferredSize(new Dimension(140,20));
        saveButton.setMinimumSize(new Dimension(140,20));
        saveButton.setMaximumSize(new Dimension(140,20));

        saveButton.addActionListener((ActionListener & Serializable) e -> {
            try{
                Date date = new Date();
                try{
                    FileOutputStream fos = new FileOutputStream("./Saves/" + saveName.getText() + ".txt");
                    ObjectOutputStream outStream = new ObjectOutputStream(fos);
                    outStream.writeObject(player.getFieldWindow());
                    outStream.flush();
                    outStream.close();
                } catch (FileNotFoundException ex){
                    File dir = new File("./Saves");
                    dir.mkdir();
                    FileOutputStream fos = new FileOutputStream("./Saves/" + saveName.getText() + ".txt");
                    ObjectOutputStream outStream = new ObjectOutputStream(fos);
                    outStream.writeObject(player.getFieldWindow());
                    outStream.flush();
                    outStream.close();
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        panel.add(saveName, constraints);
        constraints.gridx ++;
        panel.add(saveButton, constraints);
        constraints.gridy ++;
    }

    public JPanel getPanel() {
        return panel;
    }
}
