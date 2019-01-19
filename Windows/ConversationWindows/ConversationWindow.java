package Windows.ConversationWindows;

import Conversations.Conversation;
import Conversations.Shop;
import LiveCreatures.LiveCreature;
import LiveCreatures.Player;
import Windows.SupportWindows.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ConversationWindow extends JFrame {

    private Player player;
    private LiveCreature opponent;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;
    Console dialog = new Console();
    private int width = 720;
    private int height = 720;

    public ConversationWindow(LiveCreature opponent){
        super("Диалог с " + opponent.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.opponent = opponent;

        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        drawWindow();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void drawWindow(){

        getContentPane().remove(dialog);
        getContentPane().remove(panel);

        dialog = new Console();
        dialog.setSpeed(5);

        dialog.setSizeArea(width-30,height-240);

        getContentPane().add(dialog, BorderLayout.NORTH);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(Conversation conversation : opponent.getConversation().getConversationTree()) {
            JPanel convPart = new JPanel(new GridBagLayout());
            GridBagConstraints convPartconstraints = new GridBagConstraints();

            convPartconstraints.anchor = GridBagConstraints.WEST;
            convPartconstraints.insets = new Insets(0, 10, 0, 0);
            convPartconstraints.gridx = 0;
            convPartconstraints.gridy = 0;

            JButton title = new JButton(conversation.getTitle());

            title.setPreferredSize(new Dimension(width,60));
            title.setMinimumSize(new Dimension(width,60));
            title.setMaximumSize(new Dimension(width,60));

            title.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(conversation.getTitle().contains("Магазин")){
                        ((Shop)conversation).setPlayer(player);
                        close();
                    }
                    conversation.run();
                }
            });

            convPart.add(title, convPartconstraints);

            panel.add(convPart, constraints);
            constraints.gridy ++;
        }
        getContentPane().add(panel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        drawWindow();
        setVisible(b);
    }
}
