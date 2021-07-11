package window.support;

import window.player.UnfocusedButton;
import window.support.component.JPanelWithBackGroundImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;

public class StartWindow extends JFrame {

    private String game = "";

    public StartWindow(){
        super("Начать игру");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        UnfocusedButton newGame = new UnfocusedButton("Новая игра");
        UnfocusedButton loadGame = new UnfocusedButton("Загрузить игру");

        newGame.addActionListener((ActionListener & Serializable) e -> {
            synchronized (StartWindow.class) {
                StartWindow.class.notify();
            }
            game = "new";
        });

        loadGame.addActionListener((ActionListener & Serializable)e -> {
            synchronized (StartWindow.class) {
                StartWindow.class.notify();
            }
            game = "load";
        });

        panel.add(newGame, BorderLayout.NORTH);
        panel.add(loadGame, BorderLayout.SOUTH);

        getContentPane().add(panel);

        try {
            panel.add(new JPanelWithBackGroundImage("./Images/BackGroundMenu.png"));
        } catch (IOException ignored) {}

        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public String getGame() {
        return game;
    }
}
