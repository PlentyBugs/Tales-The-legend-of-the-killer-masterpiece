package utils;

import java.awt.*;

public class PanelProvider {

    public static GridBagConstraints getEmptyGBC() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        return constraints;
    }
}
