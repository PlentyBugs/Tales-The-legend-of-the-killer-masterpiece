package window;

import window.provider.AIProvider;

public interface WindowInterface extends KeyController, AIProvider {

    void writeToConsole(String message);

    void returnKeyControl();

    void setIsVisible(boolean isVisible);
}
