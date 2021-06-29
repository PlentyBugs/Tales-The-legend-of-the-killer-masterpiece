package window;

public interface WindowInterface extends KeyController, AIProvider {

    void writeToConsole(String message);

    void returnKeyControl();

    void setIsVisible(boolean isVisible);
}
