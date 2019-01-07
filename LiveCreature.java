package JGame;

public abstract class LiveCreature extends GodCreature {
    protected int x;
    protected int y;
    protected int hp;
    public int[] Move(int direction){
        if (direction == 1) {
            y = y+1;
        } else if (direction == 2) {
            x = x+1;
            y = y+1;
        } else if (direction == 3) {
            x = x+1;
        } else if (direction == 4) {
            x = x+1;
            y = y-1;
        } else if (direction == 5) {
            y = x-1;
        } else if (direction == 6) {
            x = x-1;
            y = y-1;
        } else if (direction == 7) {
            x = x-1;
        } else if (direction == 8) {
            x = x-1;
            y = y+1;
        }
        int[] position = {x,y};
        return position;
    }
}
