package thing.craft;

import creature.Player;
import item.alchemy.ingredient.Ingredient;
import item.alchemy.potion.Potion;
import support.GeneralProperty;
import support.Property;
import thing.Thing;
import utils.Pair;
import window.MultiWindow;
import window.Screen;
import window.craft.AlchemyTableWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlchemyTable extends Thing implements AlchemyCraftTable {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.ALCHEMY_TABLE);
    }

    private final AlchemyTableWindow alchemyTableWindow;
    private Potion createdPotion;
    private Player player;

    public AlchemyTable() {
        alchemyTableWindow = new AlchemyTableWindow(this);
        color = new Color(202, 0, 255);
        name = "Стол Алхимии";
        isStep = false;
    }

    public void setPlayer(Player player) {
        this.player = player;
        alchemyTableWindow.setPlayer(player);
    }

    @Override
    public void drawWindow(MultiWindow multiWindow) {
        alchemyTableWindow.setMultiWindow(multiWindow);
        alchemyTableWindow.drawWindow();
        multiWindow.newWindow(alchemyTableWindow, Screen.ALCHEMY);
        multiWindow.switchScreen(Screen.ALCHEMY);
    }

    @SafeVarargs
    @Override
    public final <T extends Ingredient> void create(T ... ingredients) {
        HashMap<Long, Pair<Potion, Integer>> usage = new HashMap<>();
        for(Ingredient ingredient : ingredients){
            if(ingredient == null)
                continue;
            for(Potion potion : ingredient.getUsage()){
                if(usage.containsKey(potion.getId())){
                    usage.put(potion.getId(), new Pair<>(
                            usage.get(potion.getId()).first(),
                            usage.get(potion.getId()).second() + 1
                    ));
                } else {
                    usage.put(potion.getId(), new Pair<>(potion, 1));
                }
            }
        }

        Pair<Potion, Integer> objects = maxKey(usage);
        Potion key = objects.first().getClearCopy();

        if(key != null){
            key.setEffect(key.getEffect().getClearCopy());
            key.getEffect().setPowerAlchemy(
                    (int) (player.getStats().getAlchemy() * Math.pow(objects.second(), 1.0 + (objects.second() / 10.0)))
            );
            key.countCost();
            createdPotion = key;
        }
    }

    public Potion getCreatedPotion() {
        return createdPotion;
    }

    private Pair<Potion, Integer> maxKey(HashMap<Long, Pair<Potion, Integer>> table) {
        Potion key = null;
        int max = -1;
        for(Long str : table.keySet()){
            if(max < table.get(str).second()){
                key = table.get(str).first();
                max = table.get(str).second();
            }
        }
        return new Pair<>(key, max);
    }

    public void clearCreatedPotion(){
        createdPotion = null;
    }
}
