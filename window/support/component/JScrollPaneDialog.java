package window.support.component;

import javax.swing.*;
import java.util.ArrayList;

public class JScrollPaneDialog extends JScrollPane {

    private ArrayList<JScrollPane> scroll = new ArrayList<>();
    private ArrayList<ArrayList<JPanel>> panels = new ArrayList<>();
    protected int numberOfCurrentLayer;


    public void addScrollBranch(JScrollPane scrollPane, int branchNumber){
        branchNumber --;
        if(branchNumber < scroll.size()){
            scroll.get(branchNumber).add(scrollPane);
        } else {
            JScrollPane scr = new JScrollPane();
            scr.add(scrollPane);
            scroll.add(scr);
        }
    }

    public void addPanelsBranch(JPanel panel, int branchNumber){
        branchNumber --;
        if(branchNumber < scroll.size()){
            panels.get(branchNumber).add(panel);
        } else {
            ArrayList<JPanel> pnls = new ArrayList<>();
            pnls.add(panel);
            panels.add(pnls);
        }
    }

    public ArrayList<JScrollPane> getScroll() {
        return scroll;
    }

    public int getNumberOfCurrentLayer() {
        return numberOfCurrentLayer;
    }

    public void setNumberOfCurrentLayer(int numberOfCurrentLayer) {
        this.numberOfCurrentLayer = numberOfCurrentLayer;
    }

    public ArrayList<ArrayList<JPanel>> getPanels() {
        return panels;
    }

    public void setPanelBySK(int s, int k, JPanel panel){
        if (s > -1 && k > -1 && panel != null && s < panels.size() && k < panels.get(s).size()){
            panels.get(s).add(k, panel);
        }
    }

    public void setScrollByS(int s, JScrollPane scrollPane){
        scroll.add(s, scrollPane);
    }
}
