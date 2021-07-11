package thing.craft;

import creature.Player;
import window.MultiWindow;

public interface CraftTable {
    void setPlayer(Player player);
    void drawWindow(MultiWindow multiWindow);
}
