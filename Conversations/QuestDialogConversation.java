package Conversations;

import Creatures.PeacefulNPC.Peaceful;
import Creatures.Player;
import Quests.Quest;

public class QuestDialogConversation extends DialogConversation {

    private Quest quest;
    private Player player;
    private Peaceful peaceful;
    private static final long serialVersionUID = 4385856357851843299L;

    public void run() {
        super.run();
        if (player != null && quest != null){
            player.addQuest(quest);
        }
    }

    public QuestDialogConversation setQuest(Quest quest) {
        this.quest = quest;
        return this;
    }

    public Quest getQuest() {
        return quest;
    }

    public QuestDialogConversation setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public QuestDialogConversation setPeaceful(Peaceful peaceful) {
        this.peaceful = peaceful;
        return this;
    }
}
