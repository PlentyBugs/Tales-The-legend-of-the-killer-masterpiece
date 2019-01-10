package JGame.Windows;

import JGame.LiveCreatures.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ChooseDifficultyWindow extends JFrame {

    private boolean check;
    private Difficulty difficulty;

    public ChooseDifficultyWindow(){
        super("Выберите сложность");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(100,350));

        JRadioButton easy = new JRadioButton("easy");
        easy.setActionCommand("easy");
        JRadioButton normal = new JRadioButton("normal");
        normal.setActionCommand("normal");
        JRadioButton hard = new JRadioButton("hard");
        hard.setActionCommand("hard");
        JRadioButton veryHard = new JRadioButton("veryHard");
        veryHard.setActionCommand("veryHard");
        JRadioButton nightmare = new JRadioButton("nightmare");
        nightmare.setActionCommand("nightmare");
        JRadioButton stopIt = new JRadioButton("stopIt");
        stopIt.setActionCommand("stopIt");
        stopIt.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(easy);
        group.add(normal);
        group.add(hard);
        group.add(veryHard);
        group.add(nightmare);
        group.add(stopIt);

        setLayout(new FlowLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 10, 0);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(easy, constraints);
        constraints.gridy = 1;
        panel.add(normal, constraints);
        constraints.gridy = 2;
        panel.add(hard, constraints);
        constraints.gridy = 3;
        panel.add(veryHard, constraints);
        constraints.gridy = 4;
        panel.add(nightmare, constraints);
        constraints.gridy = 5;
        panel.add(stopIt, constraints);

        JButton button = new JButton("Ок");
        button.setPreferredSize(new Dimension(150,30));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (easy.isSelected()){
                    difficulty = Difficulty.EASY;
                } else if (normal.isSelected()){
                    difficulty = Difficulty.NORMAL;
                } else if (hard.isSelected()){
                    difficulty = Difficulty.HARD;
                } else if (veryHard.isSelected()){
                    difficulty = Difficulty.VERYHARD;
                } else if (nightmare.isSelected()){
                    difficulty = Difficulty.NIGHTMARE;
                } else if (stopIt.isSelected()){
                    difficulty = Difficulty.STOPIT;
                }
                check = true;
            }
        });

        constraints.gridy = 6;
        panel.add(button, constraints);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public boolean getCheck(){
        return check;
    }
    public Difficulty getDifficulty(){
        return difficulty;
    }
    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
