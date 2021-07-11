package constructortool;

import conversation.Conversation;
import conversation.DialogConversation;
import creature.peaceful.Peaceful;
import window.player.UnfocusedButton;
import window.support.component.ScrollPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ConstructorConversationWindow extends JFrame {

    private Peaceful peaceful;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private Conversation chosenConversation;
    private List<Integer> branches = new ArrayList<>();
    private ScrollPanels scrollPanels = new ScrollPanels();

    public ConstructorConversationWindow(Peaceful peaceful){
        super("Редактор диалогов");

        setPreferredSize(new Dimension(800,720));
        setMinimumSize(new Dimension(800,720));
        setMaximumSize(new Dimension(800,720));
        this.peaceful = peaceful;
        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        UnfocusedButton addConversationDialog = new UnfocusedButton("Добавить диалог");

        addConversationDialog.setPreferredSize(new Dimension(180,40));
        addConversationDialog.setMinimumSize(new Dimension(180,40));
        addConversationDialog.setMaximumSize(new Dimension(180,40));
        addConversationDialog.addActionListener(e -> {
            branches.clear();
            for(Conversation conv : peaceful.getConversation().getConversationTree()) {
                for (Conversation conversation : conv.getConversationTree()) {
                    if(!branches.contains(conversation.getBranchNumber())){
                        branches.add(conversation.getBranchNumber());
                    }
                }
            }
            ConstructorConversationWindowEditor constructorConversationWindowEditor = new ConstructorConversationWindowEditor((DialogConversation) null, ConstructorConversationWindow.this, branches.size());
        });

        panel.add(addConversationDialog, constraints);
        constraints.gridx ++;

        UnfocusedButton addConversationShop = new UnfocusedButton("Добавить магазин");

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

        UnfocusedButton addBranchConversation = new UnfocusedButton("Добавить ветку");

        addBranchConversation.setPreferredSize(new Dimension(180,40));
        addBranchConversation.setMinimumSize(new Dimension(180,40));
        addBranchConversation.setMaximumSize(new Dimension(180,40));
        addBranchConversation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                branches.clear();
                for(Conversation conv : chosenConversation.getConversationTree()) {
                    for (Conversation conversation : conv.getConversationTree()) {
                        if(!branches.contains(conversation.getBranchNumber())){
                            branches.add(conversation.getBranchNumber());
                        }
                    }
                }

                ConstructorConversationWindowEditor constructorConversationWindowEditor = new ConstructorConversationWindowEditor((DialogConversation) chosenConversation, ConstructorConversationWindow.this, branches.size());
            }
        });
        panel.add(addBranchConversation, constraints);
        constraints.gridy ++;
        constraints.gridx = 0;

        getContentPane().add(panel, BorderLayout.NORTH);

        drawWindow();
    }

    public void drawWindow(){
        getContentPane().remove(scrollPanels.getScroll());

        scrollPanels.setConstructorConversationWindow(this);
        scrollPanels.setTree(peaceful.getConversation().getConversationTree());
        scrollPanels.countPanel();

        getContentPane().add(scrollPanels.getScroll(), BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public Peaceful getPeaceful() {
        return peaceful;
    }

    public void setChosenConversation(Conversation chosenConversation) {
        this.chosenConversation = chosenConversation;
    }
}
