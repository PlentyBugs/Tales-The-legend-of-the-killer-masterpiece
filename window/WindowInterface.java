package window;

import java.awt.event.KeyListener;

public interface WindowInterface extends KeyController, AIProvider {

    void writeToConsole(String message);

    void addKeyListener(KeyListener l);

    void removeKeyListener(KeyListener l);

    void setIsVisible(boolean isVisible);
}
