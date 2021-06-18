package constructortool;

import creature.GodCreature;
import thing.Grass;
import thing.Tree;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConstructorField extends JFrame {

    private final Block block;
    private JPanel panel = new JPanel(new BorderLayout());
    private GodCreature[][] map;
    private boolean[][] isEditableMap;
    private JPanel centerFieldPanel = new JPanel(new GridBagLayout());
    private JScrollPane centerFieldScroll = new JScrollPane(centerFieldPanel);
    private int mapWidth;
    private int mapHeight;
    private final ToolMode toolMode;
    private int firstClickAreaBuilderIdX = -1;
    private int secondClickAreaBuilderIdX = -1;
    private int firstClickAreaBuilderIdY = -1;
    private int secondClickAreaBuilderIdY = -1;
    private boolean ClickAreaBuilderCheck = false;


    public ConstructorField(Block block, ToolMode toolMode){
        super("Поле");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.block = block;
        this.toolMode = toolMode;

        this.block.setBlock(new Grass());

        int width = 1024;
        int height = 720;
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setResizable(false);

        mapHeight = 15;
        mapWidth = 11;

        map = new GodCreature[mapHeight][mapWidth];
        isEditableMap = new boolean[mapHeight][mapWidth];

        for (int i = 0; i < mapHeight; i++){
            for (int j = 0; j < mapWidth; j++){
                map[i][j] = new Tree();
                isEditableMap[i][j] = false;
            }
        }
        drawWindow();
    }

    public void drawWindow(){
        getContentPane().remove(panel);

        panel = new JPanel(new BorderLayout());

        JButton addUp = new UnfocusedButton("+");

        JButton addLeft = new UnfocusedButton("+");

        JButton addDown = new UnfocusedButton("+");

        JButton addRight = new UnfocusedButton("+");

        addUp.addActionListener(e -> {
            addToUp();
            drawWindow();
        });

        addLeft.addActionListener(e -> {
            addToLeft();
            drawWindow();
        });

        addDown.addActionListener(e -> {
            addToDown();
            drawWindow();
        });

        addRight.addActionListener(e -> {
            addToRight();
            drawWindow();
        });

        centerFieldPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for (int i = 0; i < mapHeight; i++){
            for (int j = 0; j < mapWidth; j++){
                constraints.gridx = j;
                constraints.gridy = i;
                JButton blockButton = new UnfocusedButton(map[i][j].getName());
                blockButton.setBackground(map[i][j].getColor());
                blockButton.setFont(new Font("TimesRoman", Font.BOLD, 8));

                int finalI = i;
                int finalJ = j;
                blockButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(toolMode.getToolModeEnum() == ToolModeEnum.BUILD){
                            GodCreature godCreature = block.getBlock().getClearCopy();
                            godCreature.setX(finalJ);
                            godCreature.setY(finalI);
                            map[finalI][finalJ] = godCreature;
                            isEditableMap[finalI][finalJ] = block.getEditable();
                            drawWindow();
                        } else if(toolMode.getToolModeEnum() == ToolModeEnum.EDITOR && isEditableMap[finalI][finalJ]){
                            EditorWindow editorWindow = new EditorWindow(map[finalI][finalJ]);
                        }else if(toolMode.getToolModeEnum() == ToolModeEnum.AREABUILDER){
                            Thread myThready = new Thread(() -> {

                                if (!ClickAreaBuilderCheck){
                                    ClickAreaBuilderCheck = true;
                                    firstClickAreaBuilderIdX = finalJ;
                                    firstClickAreaBuilderIdY = finalI;
                                } else {
                                    ClickAreaBuilderCheck = false;
                                    secondClickAreaBuilderIdX = finalJ;
                                    secondClickAreaBuilderIdY = finalI;

                                    if (firstClickAreaBuilderIdX > secondClickAreaBuilderIdX){
                                        firstClickAreaBuilderIdX = firstClickAreaBuilderIdX + secondClickAreaBuilderIdX;
                                        secondClickAreaBuilderIdX = firstClickAreaBuilderIdX - secondClickAreaBuilderIdX;
                                        firstClickAreaBuilderIdX = firstClickAreaBuilderIdX - secondClickAreaBuilderIdX;
                                    }

                                    if (firstClickAreaBuilderIdY > secondClickAreaBuilderIdY){
                                        firstClickAreaBuilderIdY = firstClickAreaBuilderIdY + secondClickAreaBuilderIdY;
                                        secondClickAreaBuilderIdY = firstClickAreaBuilderIdY - secondClickAreaBuilderIdY;
                                        firstClickAreaBuilderIdY = firstClickAreaBuilderIdY - secondClickAreaBuilderIdY;
                                    }

                                    for (int z = firstClickAreaBuilderIdY; z <= secondClickAreaBuilderIdY; z++){
                                        for (int k = firstClickAreaBuilderIdX; k <= secondClickAreaBuilderIdX; k++){
                                            GodCreature godCreature = block.getBlock().getClearCopy();
                                            godCreature.setX(k);
                                            godCreature.setY(z);
                                            map[z][k] = godCreature;
                                            isEditableMap[z][k] = block.getEditable();
                                        }
                                    }
                                    drawWindow();
                                }
                            });
                            myThready.start();
                        }
                    }
                });

                blockButton.setPreferredSize(new Dimension(70, 32));
                blockButton.setMinimumSize(new Dimension(70, 32));
                blockButton.setMaximumSize(new Dimension(70, 32));
                centerFieldPanel.add(blockButton, constraints);
            }
        }
        Point point = centerFieldScroll.getViewport().getViewPosition();
        centerFieldScroll = new JScrollPane(centerFieldPanel);
        centerFieldScroll.getViewport().setViewPosition(point);

        panel.add(addUp, BorderLayout.NORTH);
        panel.add(addDown, BorderLayout.SOUTH);
        panel.add(addRight, BorderLayout.EAST);
        panel.add(addLeft, BorderLayout.WEST);
        panel.add(centerFieldScroll, BorderLayout.CENTER);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    private void addToUp(){

        mapHeight += 1;

        GodCreature[][] oldMap = map;
        boolean[][] oldIsEditableMap = isEditableMap;
        map = new GodCreature[mapHeight][mapWidth];
        isEditableMap = new boolean[mapHeight][mapWidth];

        for(int j = 0; j < mapWidth; j++){
            map[0][j] = new Tree();
            isEditableMap[0][j] = false;
        }

        for (int i = 1; i < mapHeight; i++){
            for (int j = 0; j < mapWidth; j++){
                map[i][j] = oldMap[i-1][j];
                isEditableMap[i][j] = oldIsEditableMap[i-1][j];
            }
        }
    }

    private void addToDown(){

        mapHeight += 1;

        GodCreature[][] oldMap = map;
        boolean[][] oldIsEditableMap = isEditableMap;
        map = new GodCreature[mapHeight][mapWidth];
        isEditableMap = new boolean[mapHeight][mapWidth];

        for(int j = 0; j < mapWidth; j++){
            map[mapHeight-1][j] = new Tree();
            isEditableMap[mapHeight-1][j] = false;
        }

        for (int i = 0; i < mapHeight-1; i++){
            for (int j = 0; j < mapWidth; j++){
                map[i][j] = oldMap[i][j];
                isEditableMap[i][j] = oldIsEditableMap[i][j];
            }
        }
    }

    private void addToLeft(){

        mapWidth += 1;

        GodCreature[][] oldMap = map;
        boolean[][] oldIsEditableMap = isEditableMap;
        map = new GodCreature[mapHeight][mapWidth];
        isEditableMap = new boolean[mapHeight][mapWidth];

        for(int j = 0; j < mapHeight; j++){
            map[j][0] = new Tree();
            isEditableMap[j][0] = false;
        }

        for (int i = 0; i < mapHeight; i++){
            for (int j = 1; j < mapWidth; j++){
                map[i][j] = oldMap[i][j-1];
                isEditableMap[i][j] = oldIsEditableMap[i][j-1];
            }
        }
    }

    private void addToRight(){

        mapWidth += 1;

        GodCreature[][] oldMap = map;
        boolean[][] oldIsEditableMap = isEditableMap;

        map = new GodCreature[mapHeight][mapWidth];
        isEditableMap = new boolean[mapHeight][mapWidth];

        for(int j = 0; j < mapHeight; j++){
            map[j][mapWidth-1] = new Tree();
            isEditableMap[j][mapWidth-1] = false;
        }

        for (int i = 0; i < mapHeight; i++){
            for (int j = 0; j < mapWidth-1; j++){
                map[i][j] = oldMap[i][j];
                isEditableMap[i][j] = oldIsEditableMap[i][j];
            }
        }
    }

    public GodCreature[][] getMap() {
        return map;
    }

    public void setClickAreaBuilderCheck(boolean clickAreaBuilderCheck) {
        ClickAreaBuilderCheck = clickAreaBuilderCheck;
    }

    public void setFirstClickAreaBuilderIdX(int firstClickAreaBuilderIdX) {
        this.firstClickAreaBuilderIdX = firstClickAreaBuilderIdX;
    }

    public void setFirstClickAreaBuilderIdY(int firstClickAreaBuilderIdY) {
        this.firstClickAreaBuilderIdY = firstClickAreaBuilderIdY;
    }

    public void setSecondClickAreaBuilderIdX(int secondClickAreaBuilderIdX) {
        this.secondClickAreaBuilderIdX = secondClickAreaBuilderIdX;
    }

    public void setSecondClickAreaBuilderIdY(int secondClickAreaBuilderIdY) {
        this.secondClickAreaBuilderIdY = secondClickAreaBuilderIdY;
    }
}
