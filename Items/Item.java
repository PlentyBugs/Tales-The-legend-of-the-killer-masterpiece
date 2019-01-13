package Items;

public class Item {
    protected String name;
    protected Grade grade;
    protected int quality;
    protected Rarity rarity;
    protected Material material;
    protected String stockName;

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

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public void countProperty(){}
}
