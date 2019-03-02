package Items;

import java.io.Serializable;

public class Item implements Serializable, Cloneable, Comparable {
    protected String name;
    protected Grade grade;
    protected double quality;
    protected Rarity rarity;
    protected Material material;
    protected String stockName;

    public Item(){
        grade = Grade.COMMON;
        rarity = Rarity.COMMON;
    }

    public Grade getGrade() {
        return grade;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getName() {
        return name;
    }

    public double getQuality() {
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

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public void countProperty(){}

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    @Override
    public int compareTo(Object o) {
        if(getName() == ((Item) o).getName() && getGrade() == ((Item) o).getGrade() && getQuality() == ((Item) o).getQuality() && getRarity() == ((Item) o).getRarity()){
            return 0;
        }
        return -1;
    }
}
