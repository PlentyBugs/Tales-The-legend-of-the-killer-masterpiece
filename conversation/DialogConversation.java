package conversation;

import window.support.component.Console;

public class DialogConversation extends Conversation {
    protected String text;
    protected String playerText;
    protected Console console;
    protected String opponentName = "";
    protected String playerName = "";

    public void run() {
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

    public Console getConsole() {
        return console;
    }

    public DialogConversation setConsole(Console console) {
        this.console = console;
        return this;
    }

    public DialogConversation setPlayerText(String playerText) {
        this.playerText = playerText;
        return this;
    }

    public String getPlayerText() {
        return playerText;
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
