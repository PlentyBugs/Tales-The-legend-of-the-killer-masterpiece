package JGame;

public class Player extends Human {

    protected int money = 0;
    private int vision;
    protected String name;
    private Difficulty difficulty;

    public Player(int x, int y, String name){
        super(x, y, name);
        vision = 3;
    }

    public int getVision(){
        return vision;
    }

    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }
}
