package JGame;

public class Map {

    private int playerVision;
    private GodCreature[][] map;
    protected int mapWidth;
    protected int mapHeight;
    private Player player;

    Map(Player player, int mapWidth, int mapHeight){
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        this.player = player;

        playerVision = player.getVision()*2+1;
        map = new GodCreature[mapHeight][mapWidth];
        Thing[] randomThingList = {new Grass(), new Stone(), new Tree()};
        for(int i = 0; i < mapHeight; i++){
            for(int j = 0; j < mapWidth; j++){
                Thing randomThing = randomThingList[(int)(randomThingList.length*Math.random())];
                randomThing.setX(j);
                randomThing.setY(i);
                map[i][j] = randomThing;
            }
        }
    }

    public GodCreature[][] getMap(int x, int y){
        GodCreature[][] currentMap = new GodCreature[playerVision][playerVision];
        int vision = (playerVision-1)/2;
        for (int i = 0; i < playerVision; i++){
            for (int j = 0; j < playerVision; j++){
                if (i + y-vision >= 0 && j + x-vision >= 0) {
                    currentMap[i][j] = map[i + y-vision][j + x-vision];
                    if (i == j && i == player.getVision()){
                        currentMap[i][j] = player;
                    }
                } else {
                    currentMap[i][j] = new GreatWallNullerField();
                }
            }
        }
        return currentMap;
    }
}
