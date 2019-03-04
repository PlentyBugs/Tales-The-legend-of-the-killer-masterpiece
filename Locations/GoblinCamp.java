package Locations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GoblinCamp{

    private Map map;
    private int mapWidth;
    private int mapHeight;

    public GoblinCamp() {
        try {
            FileInputStream fis = new FileInputStream("./Locations/LocationParts/GoblinCamp.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            map = (Map) inputStream.readObject();
            map.setMapHeight();
            map.setMapWidth();
            inputStream.close();
        } catch (Exception e) {
            try{
                FileInputStream fis = new FileInputStream("./src/Locations/LocationParts/GoblinCamp.txt");
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                map = (Map) inputStream.readObject();
                map.setMapHeight();
                map.setMapWidth();
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
