package window;

import java.awt.event.KeyListener;

public interface WindowInterface extends KeyController, AIProvider {

    void writeToConsole(String message);

    void getKeyControl(KeyListener controller);

    void returnKeyControl();

    void setIsVisible(boolean isVisible);
}
