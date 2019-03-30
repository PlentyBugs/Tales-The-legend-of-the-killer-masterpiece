package Diseases;

public class Disease implements Comparable{
    protected String name;
    protected int danger;
    protected Disease evolution;

    @Override
    public int compareTo(Object o) {
        if(o instanceof Disease)
            return 0;
        return -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDanger() {
        return danger;
    }

    public void evolve(){}
}
