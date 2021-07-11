package window;

import javax.swing.*;

public interface Controllable {
    InputMap getInputMap(int condition);

    ActionMap getActionMap();
}
