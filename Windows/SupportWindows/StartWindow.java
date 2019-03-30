package Windows.SupportWindows;

import Windows.SupportWindows.SupportComponents.JPanelWithBackGroundImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;

public class StartWindow extends JFrame {

    private boolean startGame;
    private String game = "";

    public StartWindow(){
        super("Начать игру");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JButton newGame = new JButton("Новая игра");
        JButton loadGame = new JButton("Загрузить игру");

        newGame.addActionListener((ActionListener & Serializable) e -> {
            startGame = true;
            game = "new";
        });

        loadGame.addActionListener((ActionListener & Serializable)e -> {
            startGame = true;
            game = "load";
        });

        panel.add(newGame, BorderLayout.NORTH);
        panel.add(loadGame, BorderLayout.SOUTH);

        getContentPane().add(panel);

        try {
            panel.add(new JPanelWithBackGroundImage("./Images/BackGroundMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
