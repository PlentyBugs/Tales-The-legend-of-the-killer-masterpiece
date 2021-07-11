package constructortool;

import conversation.DialogConversation;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ConstructorConversationWindowEditor extends JFrame {

    public ConstructorConversationWindowEditor(DialogConversation conversation, ConstructorConversationWindow constructorConversationWindow, int countBranches){
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
        String[] branches = new String[countBranches+1];
        for(int i = 0; i <= countBranches; i++){
            branches[i] = Integer.toString(i);
        }
        JComboBox branchComboBox = new JComboBox(branches);
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


        UnfocusedButton sum = new UnfocusedButton("Принять");
        sum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                DialogConversation dialogConversation = new DialogConversation();
                dialogConversation.setOpponentName(constructorConversationWindow.getPeaceful().getName());
                if(conversation == null){
                    constructorConversationWindow.getPeaceful().addConversationDialog(branchComboBox.getSelectedIndex()+1, dialogConversation);
                } else {
                    conversation.addConversationBranch(dialogConversation, branchComboBox.getSelectedIndex()+1);
                }

                dialogConversation.setTitle(titleTextArea.getText());
                dialogConversation.setBranchNumber(branchComboBox.getSelectedIndex()+1);
                dialogConversation.setText(textTextArea.getText());
                dialogConversation.setPlayerText(playerTextLabel.getText());
                constructorConversationWindow.setChosenConversation(dialogConversation);

                constructorConversationWindow.drawWindow();
                close();
            }
        });
        panel.add(sum, constraints);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}