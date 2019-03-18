package Conversations;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
    protected ArrayList<ArrayList<Conversation>> conversationTree = new ArrayList<>();
    protected String Title;
    protected int layerNumber = 0;
    protected boolean visible = true;
    protected int branchNumber;

    public ArrayList<ArrayList<Conversation>> getConversationTree() {
        return conversationTree;
    }

    public void addConversationBranch(Conversation conversation, int branchNumber){
        branchNumber --;
        conversation.setLayerNumber(layerNumber+1);
        if(branchNumber < conversationTree.size()){
            conversationTree.get(branchNumber).add(conversation);
        } else {
            ArrayList<Conversation> conv = new ArrayList<>();
            conv.add(conversation);
            conversationTree.add(conv);
        }
    }

    public void run() {}

    public String getTitle() {
        return Title;
    }

    public Conversation setTitle(String title) {
        Title = title;
        return this;
    }

    public Conversation setIsVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean getIsVisible(){
        return visible;
    }

    public int getBranchNumber() {
        return branchNumber;
    }

    public Conversation setBranchNumber(int branchNumber) {
        this.branchNumber = branchNumber;
        return this;
    }

    public int getLayerNumber() {
        return layerNumber;
    }

    public Conversation setLayerNumber(int layerNumber) {
        this.layerNumber = layerNumber;
        return this;
    }
}
