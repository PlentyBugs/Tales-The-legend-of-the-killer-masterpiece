package Windows.SupportWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class StartWindow extends JFrame {

    private boolean startGame;
    private String game = "";

    public StartWindow(){
        super("Начать игру");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JButton newGame = new JButton("Новая игра");
        JButton loadGame = new JButton("Загрузить игру");

        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame = true;
                game = "new";
            }
        });

        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame = true;
                game = "load";
            }
        });

        panel.add(newGame, BorderLayout.NORTH);
        panel.add(loadGame, BorderLayout.SOUTH);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public String getGame() {
        return game;
    }

    public boolean getStartGame(){
        return startGame;
    }
}
