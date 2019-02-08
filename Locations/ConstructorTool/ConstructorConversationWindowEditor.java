package Locations.ConstructorTool;

import Conversations.DialogConversation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConstructorConversationWindowEditor extends JFrame {

    public ConstructorConversationWindowEditor(DialogConversation conversation, ConstructorConversationWindow constructorConversationWindow){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel titleLabel = new JLabel("Заголовок: ");
        panel.add(titleLabel, constraints);
        constraints.gridx = 1;
        JTextArea titleTextArea = new JTextArea();
        titleTextArea.setPreferredSize(new Dimension(360,40));
        titleTextArea.setMinimumSize(new Dimension(360,40));
        titleTextArea.setMaximumSize(new Dimension(360,40));
        panel.add(titleTextArea, constraints);
        constraints.gridx = 0;
        constraints.gridy ++;

        JLabel branchLabel = new JLabel("Диалоговая ветка: ");
        panel.add(branchLabel, constraints);
        constraints.gridx = 1;
        JComboBox branchComboBox = new JComboBox();
        panel.add(branchComboBox, constraints);
        constraints.gridx = 0;
        constraints.gridy ++;

        JLabel textLabel = new JLabel("Текст: ");
        panel.add(textLabel, constraints);
        constraints.gridx = 1;
        JTextArea textTextArea = new JTextArea();
        textTextArea.setPreferredSize(new Dimension(360,240));
        textTextArea.setMinimumSize(new Dimension(360,240));
        textTextArea.setMaximumSize(new Dimension(360,240));
        panel.add(textTextArea, constraints);
        constraints.gridx = 0;
        constraints.gridy ++;

        JLabel playerTextLabel = new JLabel("Текст игрока: ");
        panel.add(playerTextLabel, constraints);
        constraints.gridx = 1;
        JTextArea playerTextTextArea = new JTextArea();
        playerTextTextArea.setPreferredSize(new Dimension(360,240));
        playerTextTextArea.setMinimumSize(new Dimension(360,240));
        playerTextTextArea.setMaximumSize(new Dimension(360,240));
        panel.add(playerTextTextArea, constraints);
        constraints.gridx = 0;
        constraints.gridy ++;


        JButton sum = new JButton("Принять");
        sum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conversation.setTitle(titleTextArea.getText());
                conversation.setText(textTextArea.getText());
                conversation.setPlayerText(playerTextLabel.getText());
                constructorConversationWindow.drawWindow();
            }
        });
        panel.add(sum, constraints);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
