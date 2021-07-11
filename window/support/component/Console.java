package window.support.component;

import window.Screen;
import window.menu.AbstractMenu;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Console extends AbstractMenu implements Serializable {
    private final JScrollPane vertical;
    private final JTextArea console;
    private boolean firstTime = true;
    private int speed = 10;

    public Console() {
        setLayout(new BorderLayout());
        console = new JTextArea(8,140);
        console.setBorder(BorderFactory.createEmptyBorder(HEIGHT / 80, WIDTH / 80, HEIGHT / 80, WIDTH / 80));

        console.setBackground(Color.LIGHT_GRAY);
        console.setForeground(Color.BLACK);
        console.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        console.setLineWrap(true);
        console.setWrapStyleWord(true);

        console.setEditable(false);

        console.setFont(FONT_MEDIUM);
        console.setBackground(STYLED_COLOR_LIGHT);

        DefaultCaret caret = (DefaultCaret) console.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        vertical = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        customizeScroll(vertical);

        add(vertical);
    }
    public void writeToConsole(String text) {
        if(!firstTime){
            console.append("\n");
        }
        firstTime = false;

        for(char ch : text.toCharArray()){
            console.append(Character.toString(ch));
            try {
                TimeUnit.MILLISECONDS.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public JTextArea getConsole(String password){
        if (password.equals("2efghsd6fbuh3bsfud5sbafu4ysadbdvabsfyuob1ds4518dv1a46v1ds1v6as")){
            return console;
        } else {
            return null;
        }
    }
    public void setRowsAndColumns(int rows, int columns){
        console.setRows(rows);
        console.setColumns(columns);
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void clear(){
        console.setText("");
    }

    public void setSizeArea(int width, int height){
        vertical.setPreferredSize(new Dimension(width, height));
        vertical.setMinimumSize(new Dimension(width, height));
        vertical.setMaximumSize(new Dimension(width, height));
    }

    protected void close(Screen screen) {}
}