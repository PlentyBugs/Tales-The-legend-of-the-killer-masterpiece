package location;

public interface GenerationHelper {

    int getMapWidth();

    int getMapHeight();

    void incrementMapWidth();

    void incrementMapHeight();

    int[][] getMapKeys();

    void setMapKeys(int[][] mapKeys);

    default void addToUp() {
        incrementMapHeight();

        int[][] oldMap = getMapKeys();
        int mapWidth = getMapWidth();
        int mapHeight = getMapHeight();
        int[][] mapKeys = new int[mapHeight][mapWidth];

        for(int j = 0; j < mapWidth; j++) {
            mapKeys[0][j] = 0;
        }

        for (int i = 1; i < mapHeight; i++) {
            if (mapWidth >= 0)
                System.arraycopy(oldMap[i - 1], 0, mapKeys[i], 0, mapWidth);
        }

        setMapKeys(mapKeys);
    }

    default void addToDown() {

        incrementMapHeight();

        int[][] oldMap = getMapKeys();
        int mapWidth = getMapWidth();
        int mapHeight = getMapHeight();
        int[][] mapKeys = new int[mapHeight][mapWidth];

        for(int j = 0; j < mapWidth; j++) {
            mapKeys[mapHeight-1][j] = 0;
        }

        for (int i = 0; i < mapHeight-1; i++) {
            if (mapWidth >= 0)
                System.arraycopy(oldMap[i], 0, mapKeys[i], 0, mapWidth);
        }

        setMapKeys(mapKeys);
    }

    default void addToLeft() {

        incrementMapWidth();

        int[][] oldMap = getMapKeys();

        int mapWidth = getMapWidth();
        int mapHeight = getMapHeight();
        int[][] mapKeys = new int[mapHeight][mapWidth];

        for(int j = 0; j < mapHeight; j++) {
            mapKeys[j][0] = 0;
        }

        for (int i = 0; i < mapHeight; i++) {
            if (mapWidth - 1 >= 0)
                System.arraycopy(oldMap[i], 0, mapKeys[i], 1, mapWidth - 1);
        }

        setMapKeys(mapKeys);
    }

    default void addToRight() {

        incrementMapWidth();

        int[][] oldMap = getMapKeys();

        int mapWidth = getMapWidth();
        int mapHeight = getMapHeight();
        int[][] mapKeys = new int[mapHeight][mapWidth];

        for(int j = 0; j < mapHeight; j++) {
            mapKeys[j][mapWidth -1] = 0;
        }

        for (int i = 0; i < mapHeight; i++) {
            System.arraycopy(oldMap[i], 0, mapKeys[i], 0, mapWidth - 1);
        }

        setMapKeys(mapKeys);
    }
}
