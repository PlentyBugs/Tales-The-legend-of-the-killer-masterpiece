package Quests;

public class ReachQuest extends Quest {
    private String locationName;
    private String currentLocation;

    public ReachQuest(String locationName) {
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
