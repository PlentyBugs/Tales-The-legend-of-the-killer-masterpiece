package Windows;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Console extends JPanel
{
    private JScrollPane vertical;
    private JTextArea console;
    private boolean firstTime = true;
    private int speed = 100;

    public Console()
    {
        console = new JTextArea(8,140);

        console.setBackground(Color.LIGHT_GRAY);
        console.setForeground(Color.BLACK);
        console.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        console.setLineWrap(true);
        console.setWrapStyleWord(true);

        console.setEditable(false);

        DefaultCaret caret = (DefaultCaret) console.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        vertical = new JScrollPane(console,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(vertical);
    }
    public void writeToConsole(String text) throws InterruptedException {
        if(!firstTime){
            console.append("\n");
        }
        firstTime = false;

        for(char ch : text.toCharArray()){
            console.append(Character.toString(ch));
            TimeUnit.MILLISECONDS.sleep(speed);
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
}