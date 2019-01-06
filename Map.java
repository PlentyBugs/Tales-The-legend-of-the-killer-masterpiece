package JGame;

public class Map {

    private int playerVision;
    private String[][] map;

    Map(int vision){
        playerVision = vision*2+1;
        map = new String[playerVision][playerVision];
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                Thing[] randomThing = {new Grass(), new Stone(), new Tree()};
                map[i][j] = randomThing[(int)(randomThing.length*Math.random())].getName();
            }
        }
    }

    public String[][] getMap(){
        return map;
    }
}
