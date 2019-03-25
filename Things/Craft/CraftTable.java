package Things.Craft;

import Creatures.Player;

public interface CraftTable {
    void setPlayer(Player player);
    void setCraftTableWindow(boolean isVisible);
    void setCraftTableWindowOpen(boolean isCraftWindowOpen);
    boolean getCraftTableWindowOpen();
}
