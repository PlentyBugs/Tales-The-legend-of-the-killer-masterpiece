package window.support.component;

import constructortool.ConstructorConversationWindow;
import conversation.Conversation;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScrollPanels {

    private List<Conversation> tree = new ArrayList<>();
    private final ArrayList<String> numberOfLayers = new ArrayList<>();
    private JScrollPaneDialog scroll = new JScrollPaneDialog();
    private JScrollPane scrollPane = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private ConstructorConversationWindow constructorConversationWindow;

    public void fillMainPanel(){
        scroll = new JScrollPaneDialog();

        for (int i = 0; i < numberOfLayers.size() + 1; i++){
            loop(i);
        }
    }

    public void setTree(List<Conversation> tree) {
        this.tree = tree;
    }

    public JScrollPane getScroll() {
        return scrollPane;
    }

    public void countPanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800,620));
        scrollPane.setMinimumSize(new Dimension(800,620));
        scrollPane.setMaximumSize(new Dimension(800,620));

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        fillMainPanel();
        for (JScrollPane jScrollPane : scroll.getScroll()){
            panel.add(jScrollPane, constraints);
            constraints.gridx ++;
        }
    }

    private void loop(int i){

        List<Conversation> tree = getChildTree(i);

        JPanel convPanel = new JPanel(new GridBagLayout());
        JScrollPane convScrollPane = new JScrollPane(convPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        convScrollPane.setPreferredSize(new Dimension(150,600));
        convScrollPane.setMinimumSize(new Dimension(150,600));
        convScrollPane.setMaximumSize(new Dimension(150,600));
        GridBagConstraints convConstraints = new GridBagConstraints();

        convConstraints.anchor = GridBagConstraints.WEST;
        convConstraints.insets = new Insets(5, 5, 5, 5);
        convConstraints.gridx = 0;
        convConstraints.gridy = 0;

        for(int s = 0; s < tree.size(); s++) {
            List<Conversation> conversationTree = tree.get(s).getConversationTree();
            for (int k = 0; k < conversationTree.size(); k++) {

                JButton button = new UnfocusedButton(conversationTree.get(k).getTitle());
                int finalS = s;
                int finalK = k;
                button.addActionListener(e -> {
                    constructorConversationWindow.setChosenConversation(conversationTree.get(finalK));
                    addToNumberOfLayers(finalS, finalK, i);
                    constructorConversationWindow.drawWindow();
                });
                button.setPreferredSize(new Dimension(120,40));
                button.setMinimumSize(new Dimension(120,40));
                button.setMaximumSize(new Dimension(120,40));
                convPanel.add(button, convConstraints);
                convConstraints.gridy ++;
            }
        }

        scroll.setScrollByS(i, convScrollPane);
    }

    private List<Conversation> getChildTree(int layer){
        List<Conversation> returnChildTree = tree;
        /*
        layer --;
        if(layer != -1){
            while(returnChildTree.size() > Integer.parseInt(numberOfLayers.get(layer).split("-")[0]) && returnChildTree.get(Integer.parseInt(numberOfLayers.get(layer).split("-")[0])).size() > Integer.parseInt(numberOfLayers.get(layer).split("-")[1]) && returnChildTree.size() != 0 && returnChildTree.get(Integer.parseInt(numberOfLayers.get(layer).split("-")[0])).get(Integer.parseInt(numberOfLayers.get(layer).split("-")[1])).getLayerNumber() - 2 != layer){
                returnChildTree = returnChildTree.get(Integer.parseInt(numberOfLayers.get(layer).split("-")[0])).get(Integer.parseInt(numberOfLayers.get(layer).split("-")[1])).getConversationTree();
            }
        }
        */
        for (int i = 0; i < layer; i++){
            if(returnChildTree.size() != 0){
                returnChildTree = returnChildTree.get(Integer.parseInt(numberOfLayers.get(i).split("-")[0])).getConversationTree().get(Integer.parseInt(numberOfLayers.get(i).split("-")[1])).getConversationTree();
            }
        }

        return returnChildTree;
    }

    private void addToNumberOfLayers(int s, int k, int layer){
        boolean check = true;
        for(String str : numberOfLayers){
            if(str.split("-")[2].equals(Integer.toString(layer))){
                check = false;
                break;
            }
        }
        if(!numberOfLayers.contains(s + "-" + k + "-" + layer) && check) {
            numberOfLayers.add(s + "-" + k + "-" + layer);
        }
        if(!check){
            ArrayList<String> old = (ArrayList<String>) numberOfLayers.clone();
            numberOfLayers.clear();
            for (int i = 0; i < layer; i++){
                numberOfLayers.add(old.get(i));
            }
        }
    }

    public void setConstructorConversationWindow(ConstructorConversationWindow constructorConversationWindow) {
        this.constructorConversationWindow = constructorConversationWindow;
    }
}
