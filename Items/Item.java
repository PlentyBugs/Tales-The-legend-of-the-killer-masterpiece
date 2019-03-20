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
    private static int id;

    public Item(){
        grade = Grade.COMMON;
        rarity = Rarity.COMMON;
        stackable = false;
        enchants = new ArrayList<>();
        cost = 0;
        id ++;
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

    public Item setGrade(Grade grade) {
        this.grade = grade;
        return this;
    }

    public Item setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Item setQuality(double quality) {
        this.quality = quality;
        return this;
    }

    public Item setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public Item countProperty(){
        return this;
    }

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

    public Item addEnchant(Enchant enchant){
        enchant.setItem(this);
        enchants.add(enchant);
        return this;
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

    public void countCost(){}
}
