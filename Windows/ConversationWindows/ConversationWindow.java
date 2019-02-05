package Windows.ConversationWindows;

import Conversations.Conversation;
import Conversations.DialogConversation;
import Conversations.Shop;
import LiveCreatures.LiveCreature;
import LiveCreatures.Player;
import Windows.SupportWindows.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class ConversationWindow extends JFrame implements Serializable {

    private Player player;
    private LiveCreature opponent;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;
    private Console dialog = new Console();
    private int width = 720;
    private int height = 720;

    public ConversationWindow(LiveCreature opponent){
        super("Диалог с " + opponent.getName());

        this.opponent = opponent;

        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        dialog = new Console();

        dialog.setSpeed(5);

        dialog.setSizeArea(width-30,height-240);

        getContentPane().add(dialog, BorderLayout.NORTH);

        drawWindow();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void drawWindow(){
        Thread thread = new Thread(new Runnable(){
            public void run(){

                getContentPane().remove(panel);

                panel = new JPanel(new GridBagLayout());
                constraints = new GridBagConstraints();

                constraints.anchor = GridBagConstraints.NORTH;
                constraints.insets = new Insets(5, 0, 0, 0);
                constraints.gridx = 0;
                constraints.gridy = 0;

                for(int s = 0; s < opponent.getConversation().getConversationTree().size(); s++) {
                    for (int k = 0; k < opponent.getConversation().getConversationTree().get(s).size(); k++) {
                        if(opponent.getConversation().getConversationTree().get(s).get(k) == null){
                            continue;
                        }
                        JPanel convPart = new JPanel(new GridBagLayout());
                        GridBagConstraints convPartconstraints = new GridBagConstraints();

                        convPartconstraints.anchor = GridBagConstraints.WEST;
                        convPartconstraints.insets = new Insets(0, 10, 0, 0);
                        convPartconstraints.gridx = 0;
                        convPartconstraints.gridy = 0;

                        JButton title = new JButton(opponent.getConversation().getConversationTree().get(s).get(k).getTitle());

                        title.setPreferredSize(new Dimension(width, 30));
                        title.setMinimumSize(new Dimension(width, 30));
                        title.setMaximumSize(new Dimension(width, 30));

                        int finalS = s;
                        int finalK = k;
                        title.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (opponent.getConversation().getConversationTree().get(finalS).get(finalK).getClass().toString().contains("Shop")) {
                                    ((Shop) opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setPlayer(player);
                                    close();
                                    opponent.getConversation().getConversationTree().get(finalS).get(finalK).run();
                                } else if (opponent.getConversation().getConversationTree().get(finalS).get(finalK).getClass().toString().contains("DialogConversation")) {
                                    ((DialogConversation) opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setPlayerName(player.getName());
                                    ((DialogConversation) opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setOpponentName(opponent.getName());
                                    ((DialogConversation) opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setConsole(dialog);
                                    opponent.getConversation().getConversationTree().get(finalS).get(finalK).run();

                                    int size = opponent.getConversation().getConversationTree().get(finalS).get(finalK).getConversationTree().size();
                                    Conversation supportConversation = opponent.getConversation().getConversationTree().get(finalS).get(finalK);
                                    opponent.getConversation().getConversationTree().remove(finalS);
                                    for (int z = 0; z < size; z++){
                                        ArrayList<Conversation> conv = new ArrayList<>();
                                        for (int x = 0; x < supportConversation.getConversationTree().get(z).size(); x++){
                                            conv.add(supportConversation.getConversationTree().get(z).get(x));
                                        }
                                        opponent.getConversation().getConversationTree().add(z+1, conv);
                                    }
                                    setVisible(false);
                                    drawWindow();
                                }
                            }
                        });

                        convPart.add(title, convPartconstraints);

                        panel.add(convPart, constraints);
                        constraints.gridy++;
                    }
                    getContentPane().add(panel, BorderLayout.SOUTH);
                    pack();
                    setVisible(true);
                }
            }
        });
        thread.run();
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        setVisible(b);
        if(b){
            drawWindow();
        }
    }

    public Console getDialog() {
        return dialog;
    }
}
