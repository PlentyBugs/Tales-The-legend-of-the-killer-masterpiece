package Conversations;

import Windows.SupportWindows.Console;

public class DialogConversation extends Conversation{
    protected String text;
    protected String playerText;
    protected Console console;
    protected String opponentName = "";
    protected String playerName = "";

    public void run() {
        try {
            console.writeToConsole( playerName + ": " + playerText);
            console.writeToConsole(opponentName + ": " + text);
        } catch (InterruptedException ex){}
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public void setPlayerText(String playerText) {
        this.playerText = playerText;
    }

    public String getPlayerText() {
        return playerText;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
