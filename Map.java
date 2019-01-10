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
        for(int i = 0; i < mapHeight; i++){
            for(int j = 0; j < mapWidth; j++){
                Thing[] randomThingList = {new Grass(), new Stone(), new Tree()};
                Human[] randomHumanList = {new Bandit(), new Goblin()};

                int chance = (int) Math.ceil(Math.random() * 100);

                /*

                Старый прототип генерации с большим количеством врагов

                GodCreature[][] list = {randomHumanList, randomThingList};
                GodCreature[] random = list[(int)(list.length*Math.random())];
                GodCreature randomGodCreature = random[(int)(random.length*Math.random())];
                */
                GodCreature randomGodCreature = new GodCreature();
                if (chance >= 1 && chance <= 5){
                    randomGodCreature = randomHumanList[(int) (randomHumanList.length * Math.random())];
                } else {
                    randomGodCreature = randomThingList[(int)(randomThingList.length*Math.random())];
                }
                randomGodCreature.setX(j);
                randomGodCreature.setY(i);

                map[i][j] = randomGodCreature;
            }
        }
    }

    public GodCreature[][] getMap(int x, int y){
        GodCreature[][] currentMap = new GodCreature[playerVision][playerVision];
        int vision = (playerVision-1)/2;
        for (int i = 0; i < playerVision; i++){
            for (int j = 0; j < playerVision; j++){
                if (i + y-vision >= 0 && j + x-vision >= 0 && i + y-vision < mapHeight && j + x-vision < mapWidth) {
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
