package window.battle;

import creature.LiveCreature;
import creature.Player;
import support.CreatureProperty;
import texture.Texture;
import texture.TextureFactory;
import window.Menu;
import window.MultiWindow;
import window.Screen;
import window.WindowInterface;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class ChooseEnemyWindow extends Menu implements Serializable {

    private final LiveCreature creature;
    private final MultiWindow multiWindow;

    private final Player player;
    private final WindowInterface field;

    public ChooseEnemyWindow(Player player, WindowInterface field, MultiWindow multiWindow, LiveCreature liveCreature) {
        super();
        bindKeys();

        this.player = player;
        this.field = field;
        this.multiWindow = multiWindow;
        this.creature = liveCreature;


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel();
        Texture texture = TextureFactory.get((CreatureProperty) creature.getLastProperty());
        BufferedImage image = texture.getTexture();
        int height = image.getHeight();
        int newHeight = HEIGHT * 5 / 6;
        double coefficient = (newHeight * 1.0) / height;
        int newWidth = (int) (image.getWidth() * coefficient);
        Image scaledInstance = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        imagePanel.add(new JLabel(new ImageIcon(scaledInstance)), BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        Dimension creatureNameDimension = new Dimension(newWidth, HEIGHT / 10);
        infoPanel.setMinimumSize(creatureNameDimension);
        infoPanel.setMaximumSize(creatureNameDimension);
        infoPanel.setPreferredSize(creatureNameDimension);
        JLabel creatureNameLabel = new JLabel(creature.getName(), SwingConstants.CENTER);
        creatureNameLabel.setFont(FONT);
        infoPanel.add(creatureNameLabel, BorderLayout.NORTH);
        JLabel creatureLevelLabel = new JLabel(creature.getLvl() + " Lvl", SwingConstants.CENTER);
        creatureLevelLabel.setFont(FONT);
        infoPanel.add(creatureLevelLabel, BorderLayout.CENTER);
        JProgressBar life = new JProgressBar(0, creature.getMaxHp());
        life.setValue((int) (creature.getHp()));
        life.setStringPainted(true);
        life.setString(((int) creature.getHp()) + " / " + creature.getMaxHp());
        life.setFont(FONT);
        life.setBackground(Color.RED);
        life.setForeground(Color.GREEN);
        infoPanel.add(life, BorderLayout.SOUTH);
        JButton fight = new UnfocusedButton("Fight");
        fight.addActionListener(e -> fight());
        customizeButton(fight);

        panel.add(infoPanel, BorderLayout.NORTH);
        panel.add(imagePanel, BorderLayout.CENTER);
        panel.add(fight, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);

        field.setIsVisible(false);
        multiWindow.newWindow(this, Screen.ENEMY);
        multiWindow.switchScreen(Screen.ENEMY);
    }

    private void close(Screen screen) {
        multiWindow.removeWindow(this);
        field.returnKeyControl();
        multiWindow.switchScreen(screen);
    }

    private void fight() {
        FightWindow fightWindow = new FightWindow(player, creature, field, multiWindow);
        multiWindow.newWindow(fightWindow, Screen.FIGHT);
        close(Screen.FIGHT);
        player.getWindowInterface().getNpcController().setWaiting(true);
    }

    private void bindKeys() {
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape,   "escape");
        getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close(Screen.GAME);
            }
        });
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke,   "fight");
        getActionMap().put("fight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fight();
            }
        });
    }
}
