package conversation;

import window.support.component.Console;

public class DialogConversation extends Conversation {
    protected String text;
    protected String playerText;
    protected String opponentName = "";
    protected String playerName = "";

    public void writeToConsole(Console console) {
        console.writeToConsole( playerName + ": " + playerText);
        console.writeToConsole(opponentName + ": " + text);
    }

    public String getText() {
        return text;
    }

    public DialogConversation setText(String text) {
        this.text = text;
        return this;
    }

    public DialogConversation setPlayerText(String playerText) {
        this.playerText = playerText;
        return this;
    }

    public DialogConversation setOpponentName(String opponentName) {
        this.opponentName = opponentName;
        return this;
    }

    public DialogConversation setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }
}
