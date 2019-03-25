package Things.Craft;

import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Items.Alchemy.Potions.Potion;
import Things.Thing;
import Windows.CraftWindow.AlchemyTableWindow;

import java.awt.*;
import java.util.HashMap;

public class AlchemyTable extends Thing implements AlchemyCraftTable{

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
        HashMap<String, Object[]> usage = new HashMap<>();
        int count = 0;
        for(Ingredient ingredient : ingredients){
            if(ingredient == null)
                continue;
            count ++;
            for(Potion potion : ingredient.getUsage()){
                if(usage.containsKey(potion.getClass().toString())){
                    usage.put(potion.getClass().toString(), new Object[]{(Potion)usage.get(potion.getClass().toString())[0], (int)usage.get(potion.getClass().toString())[1] + 1});
                } else {
                    usage.put(potion.getClass().toString(), new Object[]{potion, 1});
                }
            }
        }
        Object[] objects = maxKey(usage);
        Potion key = (Potion)objects[0];

        if(key != null){
            key.setEffect(key.getEffect().getClearCopy());
            key.getEffect().setPowerAlchemy((int)(player.getStats().getAlchemy()*Math.pow((int)objects[1], 1.0 + ((int)objects[1])/10.0)));
            createdPotion = key;
        }
    }

    public Potion getCreatedPotion() {
        return createdPotion;
    }

    private Object[] maxKey(HashMap<String, Object[]> table){
        Potion key = null;
        int max = -1;
        for(String str : table.keySet()){
            if(max < (int)table.get(str)[1]){
                key = (Potion)table.get(str)[0];
                max = (int)table.get(str)[1];
            }
        }
        return new Object[]{key, max};
    }

    public void clearCreatedPotion(){
        createdPotion = null;
    }
}
