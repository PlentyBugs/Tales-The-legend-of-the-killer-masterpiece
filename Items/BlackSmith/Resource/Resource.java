package Items.BlackSmith.Resource;

import Items.Item;

import java.awt.*;

public class Resource extends Item {
    protected int maxTemperature;
    protected int temperature = 0;
    protected boolean hotTreatment;
    protected Color color;

    public Resource(){
        name = getClass().getSimpleName();
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public boolean getTreatment(){
        return hotTreatment;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass() != getClass()){
            return -1;
        }
        if(maxTemperature == ((Resource)o).getMaxTemperature() && hotTreatment == ((Resource) o).getTreatment()){
            return 0;
        }
        if(maxTemperature > ((Resource)o).getMaxTemperature())
            return 1;
        else
            return -1;
    }

    public Color getColor() {
        return color;
    }
}
