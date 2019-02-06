package Conversations;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
    protected ArrayList<ArrayList<Conversation>> conversationTree = new ArrayList<>();
    protected String Title;
    protected int branchNumber;

    public ArrayList<ArrayList<Conversation>> getConversationTree() {
        return conversationTree;
    }

    public void addConversationBranch(Conversation conversation, int branchNumber){
        branchNumber --;
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

    public void setTitle(String title) {
        Title = title;
    }
}
