package Things;

import Items.BlackSmith.Resource.Resource;

import java.awt.*;

public class Ore extends Stone {

    protected Resource resource;

    public Ore(Resource resource){
        this.resource = resource;
        name = resource.getName();
        color = Color.LIGHT_GRAY;
        isStep = true;
        color = resource.getColor();
    }

    public Resource getResource() {
        return resource;
    }
}
