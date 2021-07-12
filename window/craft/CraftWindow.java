package window.craft;

import creature.Player;
import window.MultiWindow;
import window.Screen;
import window.menu.AbstractMenu;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

public class CraftWindow extends AbstractMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = 6273872975006011103L;
    protected MultiWindow multiWindow;
    protected Player player;

    protected void close(Screen screen){
        multiWindow.removeWindow(this);
        multiWindow.switchScreen(screen);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMultiWindow(MultiWindow multiWindow) {
        this.multiWindow = multiWindow;
    }
}
