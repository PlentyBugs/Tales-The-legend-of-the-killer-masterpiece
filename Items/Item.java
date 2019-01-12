package Items;

public class Item {
    protected String name;
    protected Grade grade;
    protected int quality;
    protected Rarity rarity;
    protected Material material;

    public Grade getGrade() {
        return grade;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getName() {
        return name;
    }

    public int getQuality() {
        return quality;
    }
}
