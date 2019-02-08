package LiveCreatures.PeacefulNPC;

import Abilities.Ability;
import Conversations.CatalogStock;
import Conversations.DialogConversation;
import Conversations.Shop;
import Items.Item;
import LiveCreatures.Human;

public class Peaceful extends Human {
    protected CatalogStock catalogStock;
    protected String starterPhrase;

    public Peaceful(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);
    }

    public void addConversationShop(int branchNumber, String title, Object[] ... objects){
        Shop shop = new Shop();
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
    }

    public void addConversationDialog(int branchNumber, String title, String text, String playerText){
        DialogConversation dialogConversation = new DialogConversation();
        dialogConversation.setTitle(title);
        dialogConversation.setText(text);
        dialogConversation.setPlayerText(playerText);
        dialogConversation.setConsole(getConversationWindow().getDialog());
        conversation.addConversationBranch(dialogConversation, branchNumber);
    }

    public void addConversationDialog(int branchNumber, DialogConversation dialogConversation){
        dialogConversation.setConsole(getConversationWindow().getDialog());
        conversation.addConversationBranch(dialogConversation, branchNumber);
    }

    public String getStarterPhrase() {
        return starterPhrase;
    }

    public void setStarterPhrase(String starterPhrase) {
        this.starterPhrase = starterPhrase;
    }
}
