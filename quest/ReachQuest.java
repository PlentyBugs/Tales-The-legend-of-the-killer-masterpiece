package quest;

import support.Property;

import java.util.ArrayList;
import java.util.List;

public class ReachQuest extends Quest {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Quest.propertyList);
    }

    private String locationName;
    private String currentLocation;

    public ReachQuest(String locationName) {
        super();
        this.locationName = locationName;
    }

    public boolean check(){
        if (currentLocation.equals(locationName)){
            return true;
        }
        return false;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ReachQuest addAutoNextQuest(Quest quest){
        if(quest != null)
            autoNextQuest.add(quest);
        return this;
    }
}
