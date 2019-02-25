package Things;

import Creatures.GodCreature;

import java.awt.*;

public class GreatWallNullerField extends GodCreature {
    public GreatWallNullerField(){
        name = "Великая стена";
        color = Color.BLACK;
        isStep = false;
    }
    @Override
    public GreatWallNullerField getClearCopy() {
        return new GreatWallNullerField();
    }
}
