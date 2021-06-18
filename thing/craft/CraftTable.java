package thing.craft;

import creature.Player;

public interface CraftTable {
    void setPlayer(Player player);
    void setCraftTableWindow(boolean isVisible);
    void setCraftTableWindowOpen(boolean isCraftWindowOpen);
    boolean getCraftTableWindowOpen();
}
