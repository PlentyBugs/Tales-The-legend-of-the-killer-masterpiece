package Locations.ConstructorTool;

import Conversations.Conversation;
import Conversations.DialogConversation;
import LiveCreatures.PeacefulNPC.Peaceful;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConstructorConversationWindow extends JFrame {

    private Peaceful peaceful;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private Conversation chosenConversation;

    public ConstructorConversationWindow(Peaceful peaceful){
        super("Редактор диалогов");

        setPreferredSize(new Dimension(720,720));
        setMinimumSize(new Dimension(720,720));
        setMaximumSize(new Dimension(720,720));
        this.peaceful = peaceful;

        drawWindow();
    }

    public void drawWindow(){

        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JPanel convPanel = new JPanel(new GridBagLayout());
        JScrollPane convScrollPane = new JScrollPane(convPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        convScrollPane.setPreferredSize(new Dimension(150,600));
        convScrollPane.setMinimumSize(new Dimension(150,600));
        convScrollPane.setMaximumSize(new Dimension(150,600));
        GridBagConstraints convConstraints = new GridBagConstraints();

        convConstraints.anchor = GridBagConstraints.WEST;
        convConstraints.insets = new Insets(5, 5, 5, 5);
        convConstraints.gridx = 0;
        convConstraints.gridy = 0;

        for(ArrayList<Conversation> conversationArrayList : peaceful.getConversation().getConversationTree()){

            for(Conversation conversation : conversationArrayList){
                JButton button = new JButton(conversation.getTitle());
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        chosenConversation = conversation;
                    }
                });
                button.setPreferredSize(new Dimension(120,40));
                button.setMinimumSize(new Dimension(120,40));
                button.setMaximumSize(new Dimension(120,40));
                convPanel.add(button, convConstraints);
                convConstraints.gridy ++;
            }
        }
        panel.add(convScrollPane, constraints);
        constraints.gridy ++;

        JButton addConversationDialog = new JButton("Добавить диалог");

        addConversationDialog.setPreferredSize(new Dimension(180,40));
        addConversationDialog.setMinimumSize(new Dimension(180,40));
        addConversationDialog.setMaximumSize(new Dimension(180,40));
        addConversationDialog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DialogConversation dialogConversation = new DialogConversation();
                dialogConversation.setOpponentName(peaceful.getName());
                chosenConversation = dialogConversation;
                peaceful.addConversationDialog(peaceful.getConversation().getConversationTree().size()+1, dialogConversation);
                drawWindow();
            }
        });

        panel.add(addConversationDialog, constraints);
        constraints.gridx ++;

        JButton addConversationShop = new JButton("Добавить магазин");

        addConversationShop.setPreferredSize(new Dimension(180,40));
        addConversationShop.setMinimumSize(new Dimension(180,40));
        addConversationShop.setMaximumSize(new Dimension(180,40));
        /*
        addConversationShop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Shop dialogConversation = new DialogConversation();
                dialogConversation.setOpponentName(peaceful.getName());
                chosenConversation = dialogConversation;
                peaceful.addConversationDialog(peaceful.getConversation().getConversationTree().size()+1, dialogConversation);
                drawWindow();
            }
        });
        */

        panel.add(addConversationShop, constraints);
        constraints.gridx ++;
        JButton editConversation = new JButton("Редактировать");

        editConversation.setPreferredSize(new Dimension(180,40));
        editConversation.setMinimumSize(new Dimension(180,40));
        editConversation.setMaximumSize(new Dimension(180,40));
        editConversation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConstructorConversationWindowEditor constructorConversationWindowEditor = new ConstructorConversationWindowEditor((DialogConversation) chosenConversation, ConstructorConversationWindow.this);
            }
        });

        panel.add(editConversation, constraints);

        getContentPane().add(panel, BorderLayout.WEST);
        pack();
        setVisible(true);
    }
}
