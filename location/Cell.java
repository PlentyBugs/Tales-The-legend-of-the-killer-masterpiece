package location;

import creature.GodCreature;

public class Cell {

    protected GodCreature lowerObject;
    protected GodCreature upperObject;
    protected boolean visited;

    public GodCreature getLowerObject() {
        return lowerObject;
    }

    public void setLowerObject(GodCreature lowerObject) {
        this.lowerObject = lowerObject;
    }

    public GodCreature getUpperObject() {
        return upperObject;
    }

    public void setUpperObject(GodCreature upperObject) {
        this.upperObject = upperObject;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "lowerObject=" + lowerObject +
                ", upperObject=" + upperObject +
                ", visited=" + visited +
                '}';
    }
}
