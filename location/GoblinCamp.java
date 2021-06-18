package location;

import creature.GodCreature;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GoblinCamp{

    private Map map;
    private int mapWidth;
    private int mapHeight;

    public GoblinCamp() {
        try {
            FileInputStream fis = new FileInputStream("location/part/GoblinCamp.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            map = (Map) inputStream.readObject();
            GodCreature[][] mapLowerObjects = map.getMapLowerObjects();
            map.setMapHeight(mapLowerObjects.length);
            map.setMapWidth(mapLowerObjects[0].length);
            inputStream.close();
        } catch (Exception e) {
            try{
                FileInputStream fis = new FileInputStream("./src/Locations/LocationParts/GoblinCamp.txt");
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                map = (Map) inputStream.readObject();
                GodCreature[][] mapLowerObjects = map.getMapLowerObjects();
                map.setMapHeight(mapLowerObjects.length);
                map.setMapWidth(mapLowerObjects[0].length);
                inputStream.close();
            } catch (IOException ex){
                ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    public Map getMap() {
        return map;
    }
}
