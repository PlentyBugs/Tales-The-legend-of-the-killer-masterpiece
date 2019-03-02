package Things.Craft;

import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Items.Alchemy.Potions.Potion;
import Things.Thing;
import Windows.CraftWindow.AlchemyTableWindow;

import java.awt.*;
import java.util.HashMap;

public class AlchemyTable extends Thing implements CraftTable{

    private AlchemyTableWindow alchemyTableWindow;
    private boolean isAlchemyWindowOpen;
    private Potion createdPotion;
    private Player player;

    public AlchemyTable(){
        name = "Стол Алхимии";
        color = new Color(202, 0, 255);
        isStep = false;
        alchemyTableWindow = new AlchemyTableWindow(this);
        setCraftTableWindow(false);
        setCraftTableWindowOpen(false);
    }

    @Override
    public void setPlayer(Player player) {
        alchemyTableWindow.setPlayer(player);
        this.player = player;
    }
    public void setCraftTableWindow(boolean isVisible) { alchemyTableWindow.setIsVisible(isVisible);}

    public void setCraftTableWindowOpen(boolean isAlchemyWindowOpen) {
        createdPotion = null;
        this.isAlchemyWindowOpen = isAlchemyWindowOpen;
    }

    @Override
    public boolean getCraftTableWindowOpen() {
        return isAlchemyWindowOpen;
    }

    @Override
    public <T extends Ingredient> void create(T ... ingredients) {
        HashMap<Potion, Integer> usage = new HashMap<>();
        int count = 0;
        for(Ingredient ingredient : ingredients){
            if(ingredient == null)
                continue;
            count ++;
            for(Potion potion : ingredient.getUsage()){
                if(usage.containsKey(potion)){
                    usage.put(potion, usage.get(potion) + 1);
                } else {
                    usage.put(potion, 1);
                }
            }
        }
        Object[] objects = maxKey(usage);
        Potion key = (Potion)objects[0];

        if(key != null){
            key.getEffect().setPowerAlchemy(player.getStats().alchemy*((int)objects[1]));
            createdPotion = key;
        }
    }

    public Potion getCreatedPotion() {
        return createdPotion;
    }

    private Object[] maxKey(HashMap<Potion, Integer> table){
        Potion key = null;
        int max = -1;
        for(Potion str : table.keySet()){
            if(max < table.get(str)){
                key = str;
                max = table.get(str);
            }
        }
        return new Object[]{key, max};
    }
}
