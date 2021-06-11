package Items;

import Abilities.Enchants.Enchant;
import Creatures.Copying;
import support.Property;
import support.PropertyProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item implements Serializable, Cloneable, Copying<Item>, PropertyProvider {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.add(Property.ITEM);
    }

    protected String name;
    protected int cost;
    protected Grade grade;
    protected double quality;
    protected Rarity rarity;
    protected Material material;
    protected String stockName;
    protected boolean stackable;
    protected double selfForgedBonus = 1;
    protected ArrayList<Enchant> enchants;
    private long id;
    public static int number = 0;

    public Item(){
        grade = Grade.COMMON;
        rarity = Rarity.COMMON;
        stackable = false;
        enchants = new ArrayList<>();
        cost = 0;
        quality = 100;
        id = number++;
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

    public int compareTo(Item o) {
        if(getName().equals(o.getName()) && getGrade() == o.getGrade() && getQuality() == o.getQuality() && getRarity() == o.getRarity()){
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        StringBuilder desc = new StringBuilder("<html>Зачарования:<br>");
        for(Enchant enchant : enchants){
            desc.
                    append("<b>Название:</b> ").
                    append(enchant.getName()).
                    append("<br><b>Описание:</b> ").
                    append(enchant.getDescription()).
                    append("<br><b>Мощность:</b> ").
                    append(enchant.getPower()).
                    append("<br><br>");
        }
        desc.append("</html>");
        return desc.toString();
    }

    public Item setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public void countCost(){}

    public void setSelfForgedBonus(double selfForgedBonus) {
        this.selfForgedBonus = selfForgedBonus;
    }

    public void decreaseQuality(double decQ){
        quality -= decQ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Item getClearCopy() {
        return new Item();
    }
}
