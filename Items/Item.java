package Items;

import Abilities.Enchants.Enchant;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable, Cloneable, Comparable {
    protected String name;
    protected int cost;
    protected Grade grade;
    protected double quality;
    protected Rarity rarity;
    protected Material material;
    protected String stockName;
    protected boolean stackable;
    protected ArrayList<Enchant> enchants;
    private int id;

    public Item(){
        grade = Grade.COMMON;
        rarity = Rarity.COMMON;
        stackable = false;
        enchants = new ArrayList<>();
        cost = 0;
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
        if(getName().equals(((Item) o).getName()) && getGrade() == ((Item) o).getGrade() && getQuality() == ((Item) o).getQuality() && getRarity() == ((Item) o).getRarity()){
            return 0;
        }
        return -1;
    }

    public boolean getStackable(){
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Enchant> getEnchants() {
        return enchants;
    }

    public void addEnchant(Enchant enchant){
        enchants.add(enchant);
    }

    public String getEnchantDescription(){
        String desc = "<html>Зачарования:<br>";
        for(Enchant enchant : enchants){
            desc += "<b>Название:</b> " + enchant.getName() + "<br><b>Описание:</b> " + enchant.getDescription() + "<br><b>Мощность:</b> " + enchant.getPower() + "<br><br>";
        }
        desc += "</html>";
        return desc;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
