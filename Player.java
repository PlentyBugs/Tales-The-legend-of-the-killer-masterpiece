package JGame;

public class Player extends Human {

    protected int money = 0;
    private int vision;
    protected String name;
    private String difficulty;

    public Player(int x, int y, String name){
        super(x, y, name);
        vision = 3;
    }

    public int getVision(){
        return vision;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public String getDifficulty(){
        return difficulty;
    }
}
