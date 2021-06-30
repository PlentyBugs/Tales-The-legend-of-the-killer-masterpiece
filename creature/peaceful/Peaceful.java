package creature.peaceful;

import abilities.Ability;
import conversation.CatalogStock;
import conversation.DialogConversation;
import conversation.Shop;
import conversation.TrainShop;
import item.Item;
import creature.Human;
import support.Property;
import support.CreatureProperty;

import java.util.ArrayList;
import java.util.List;

public class Peaceful extends Human {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Human.propertyList);
        propertyList.add(CreatureProperty.PEACEFUL);
    }

    protected CatalogStock catalogStock;
    protected String starterPhrase;

    public Peaceful(){
        this(0,0,"Мирный",1,100);
    }

    public Peaceful(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);
    }

    public Peaceful addConversationShop(int branchNumber){
        Shop shop = new Shop();
        shop.setSeller(this);
        shop.setTitle("Магазин");

        conversation.addConversationBranch(shop, branchNumber);
        return this;
    }

    public Peaceful addConversationTrain(int branchNumber, String title, Object[] ... objects){
        TrainShop shop = new TrainShop();
        shop.setTitle(title);

        CatalogStock catalogStock = new CatalogStock();
        for (Object[] object : objects){
            try{
                catalogStock.addToStock((Item) object[0], (int)object[1], (int)object[2]);
            } catch (Exception ex){
                catalogStock.addToStock((Ability) object[0], (int)object[1], (int)object[2]);
            }
        }
        shop.setCatalog(catalogStock);
        conversation.addConversationBranch(shop, branchNumber);
        return this;
    }

    public Peaceful addConversationDialog(int branchNumber, String title, String text, String playerText){
        DialogConversation dialogConversation = new DialogConversation();
        dialogConversation.setTitle(title);
        dialogConversation.setText(text);
        dialogConversation.setPlayerText(playerText);
        conversation.addConversationBranch(dialogConversation, branchNumber);
        return this;
    }

    public Peaceful addConversationDialog(int branchNumber, DialogConversation dialogConversation){
        conversation.addConversationBranch(dialogConversation, branchNumber);
        return this;
    }

    public String getStarterPhrase() {
        return starterPhrase;
    }

    public Peaceful setStarterPhrase(String starterPhrase) {
        this.starterPhrase = starterPhrase;
        return this;
    }

    @Override
    public Peaceful clone() throws CloneNotSupportedException
    {
        return (Peaceful) super.clone();
    }

    @Override
    public Peaceful getClearCopy() {
        return new Peaceful();
    }
}
