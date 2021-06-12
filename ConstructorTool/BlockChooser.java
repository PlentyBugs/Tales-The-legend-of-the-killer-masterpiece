package ConstructorTool;

import Creatures.AggressiveNPC.Bandit;
import Creatures.AggressiveNPC.Goblin;
import Creatures.AggressiveNPC.GoblinKing;
import Creatures.AggressiveNPC.Knight;
import Creatures.GodCreature;
import Creatures.LiveCreature;
import Creatures.PeacefulNPC.Dealer;
import Creatures.PeacefulNPC.Inhabitant;
import Creatures.Player;
import Locations.Map;
import Things.*;
import Things.ChestLike.Chest;
import Things.ChestLike.Corpse;
import Windows.PlayerWindows.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BlockChooser extends JFrame {

    private final int width = 300;
    private final Block block;
    private final GodCreature[] blockListNotLive = new GodCreature[]{new GreatWallNullerField(), new Grass(), new Corpse(0,0), new BrickRoad(), new House(), new Stone(), new HealBlock(0,0), new Tree(), new Chest()};
    private final GodCreature[] blockListLive = new GodCreature[]{ new Bandit(), Dealer.getInstance(), new Goblin(), new Knight(), Inhabitant.getInstance(), new GoblinKing()};

    private JPanel panel = new JPanel(new GridBagLayout());
    private final ToolMode toolMode;
    private final ConstructorField constructorField;

    public BlockChooser(Block block, ConstructorField constructorField, ToolMode toolMode){
        super("Выбор блока");

        this.block = block;
        this.constructorField = constructorField;
        this.toolMode = toolMode;

        int height = 720;
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setResizable(false);

        drawWindow();
    }


    public void drawWindow(){
        getContentPane().remove(panel);
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;


        JButton build = new UnfocusedButton("Строить");
        build.setPreferredSize(new Dimension(width, 20));
        build.setMinimumSize(new Dimension(width, 20));
        build.setMaximumSize(new Dimension(width, 20));
        panel.add(build, constraints);
        constraints.gridy ++;
        JButton areaBuilder = new UnfocusedButton("Строить по площади");
        areaBuilder.setPreferredSize(new Dimension(width, 20));
        areaBuilder.setMinimumSize(new Dimension(width, 20));
        areaBuilder.setMaximumSize(new Dimension(width, 20));
        panel.add(areaBuilder, constraints);
        constraints.gridy ++;
        JButton edit = new UnfocusedButton("Редактировать");
        edit.setPreferredSize(new Dimension(width, 20));
        edit.setMinimumSize(new Dimension(width, 20));
        edit.setMaximumSize(new Dimension(width, 20));
        panel.add(edit, constraints);
        constraints.gridy ++;

        JLabel thingBlocks = new JLabel("Вещи: ");
        panel.add(thingBlocks, constraints);
        constraints.gridy ++;


        build.addActionListener(e -> {
            toolMode.setToolModeEnum(ToolModeEnum.BUILD);
            build.setBackground(new Color(0,255,0));
            areaBuilder.setBackground(new Color(255,255,255));
            edit.setBackground(new Color(255,255,255));
        });
        areaBuilder.addActionListener(e -> {
            constructorField.setClickAreaBuilderCheck(false);
            constructorField.setSecondClickAreaBuilderIdX(-1);
            constructorField.setFirstClickAreaBuilderIdX(-1);
            constructorField.setSecondClickAreaBuilderIdY(-1);
            constructorField.setFirstClickAreaBuilderIdY(-1);
            toolMode.setToolModeEnum(ToolModeEnum.AREABUILDER);
            areaBuilder.setBackground(new Color(0,255,0));
            edit.setBackground(new Color(255,255,255));
            build.setBackground(new Color(255,255,255));
        });
        edit.addActionListener(e -> {
            toolMode.setToolModeEnum(ToolModeEnum.EDITOR);
            edit.setBackground(new Color(0,255,0));
            areaBuilder.setBackground(new Color(255,255,255));
            build.setBackground(new Color(255,255,255));
        });

        for (GodCreature creature : blockListNotLive){
            JPanel blockPanel = new JPanel(new BorderLayout());
            blockPanel.setPreferredSize(new Dimension(width, 20));
            blockPanel.setMinimumSize(new Dimension(width, 20));
            blockPanel.setMaximumSize(new Dimension(width, 20));

            JLabel blockName = new JLabel(creature.getName());

            JButton blockButton = new UnfocusedButton("Выбрать");

            if(creature == block.getBlock()){
                blockButton.setBackground(new Color(0,255,0));
            } else {

                blockButton.setBackground(new Color(255,255,255));
            }

            blockButton.addActionListener(e -> {
                block.setEditable(creature instanceof Chest);
                block.setBlock(creature);
                block.setBlockType(BlockType.THING);
                drawWindow();
            });

            blockPanel.add(blockName, BorderLayout.LINE_START);
            blockPanel.add(blockButton, BorderLayout.LINE_END);

            panel.add(blockPanel, constraints);
            constraints.gridy ++;
        }

        JLabel personsBlocks = new JLabel("Персонажи:");
        panel.add(personsBlocks, constraints);
        constraints.gridy ++;

        for (GodCreature creature : blockListLive){
            JPanel blockPanel = new JPanel(new BorderLayout());
            blockPanel.setPreferredSize(new Dimension(width, 20));
            blockPanel.setMinimumSize(new Dimension(width, 20));
            blockPanel.setMaximumSize(new Dimension(width, 20));

            JLabel blockName = new JLabel(creature.getName());

            JButton blockButton = new UnfocusedButton("Выбрать");

            if(creature == block.getBlock()){
                blockButton.setBackground(new Color(0,255,0));
            } else {
                blockButton.setBackground(new Color(255,255,255));
            }


            blockButton.addActionListener(e -> {
                block.setEditable(creature instanceof LiveCreature);
                block.setBlock(creature);
                block.setBlockType(BlockType.LIVECREATURE);
                drawWindow();
            });

            blockPanel.add(blockName, BorderLayout.LINE_START);
            blockPanel.add(blockButton, BorderLayout.LINE_END);

            panel.add(blockPanel, constraints);
            constraints.gridy ++;
        }

        JLabel fileNameLabel = new JLabel("Название файла:");
        fileNameLabel.setPreferredSize(new Dimension(width, 20));
        fileNameLabel.setMinimumSize(new Dimension(width, 20));
        fileNameLabel.setMaximumSize(new Dimension(width, 20));

        panel.add(fileNameLabel, constraints);
        constraints.gridy ++;

        JTextArea fileNameTextArea = new JTextArea();
        fileNameTextArea.setPreferredSize(new Dimension(width, 20));
        fileNameTextArea.setMinimumSize(new Dimension(width, 20));
        fileNameTextArea.setMaximumSize(new Dimension(width, 20));

        panel.add(fileNameTextArea, constraints);
        constraints.gridy ++;

        JButton saveButton = new UnfocusedButton("Сохранить");
        saveButton.setPreferredSize(new Dimension(width, 20));
        saveButton.setMinimumSize(new Dimension(width, 20));
        saveButton.setMaximumSize(new Dimension(width, 20));

        saveButton.addActionListener(e -> {
            try{
                Map newMap = new Map(new Player(0,0,"",0,0), 20,20);
                newMap.setMapLowerObjects(constructorField.getMap());
                FileOutputStream fos = new FileOutputStream("./" + fileNameTextArea.getText() + ".txt");
                ObjectOutputStream outStream = new ObjectOutputStream(fos);
                outStream.writeObject(newMap);
                outStream.flush();
                outStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        panel.add(saveButton, constraints);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
