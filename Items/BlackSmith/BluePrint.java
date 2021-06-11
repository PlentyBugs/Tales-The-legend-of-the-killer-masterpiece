package Items.BlackSmith;

import Items.BlackSmith.Resource.Resource;
import Items.Item;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class BluePrint extends Item  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Item.propertyList);
        propertyList.add(Property.BLUEPRINT);
    }

    private ItemCraftType itemType;
    private final ArrayList<Resource> resources;
    private int temperature;

    public BluePrint(){
        resources = new ArrayList<>();
    }

    public BluePrint setType(ItemCraftType type){
        this.itemType = type;
        return this;
    }

    public BluePrint addResource(Resource resource){
        resources.add(resource);
        return this;
    }

    public BluePrint setName(String name){
        this.name = name;
        return this;
    }

    public BluePrint setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public BluePrint setTemperature(int temperature) {
        this.temperature = temperature;
        return this;
    }

    public int getTemperature() {
        return temperature;
    }

    public ItemCraftType getItemType() {
        return itemType;
    }

    public boolean hasResource(Resource resource){
        for(Resource res : resources){
            if(res.getClass() == resource.getClass()){
                return true;
            }
        }
        return false;
    }
}
