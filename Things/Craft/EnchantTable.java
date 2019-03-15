package Things.Craft;

import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Things.Thing;
import Windows.CraftWindow.EnchantTableWindow;

import java.awt.*;

public class EnchantTable extends Thing implements CraftTable{

    private boolean isCraftTableWindowOpen;
    private EnchantTableWindow enchantTableWindow;
    private Player player;

    public EnchantTable(){
        name = "Стол зачарования";
        color = new Color(58, 228, 255);
        isStep = false;
        enchantTableWindow = new EnchantTableWindow(this);
        setCraftTableWindow(false);
        setCraftTableWindowOpen(false);
    }

    @Override
    public void setPlayer(Player player) {
        enchantTableWindow.setPlayer(player);
        this.player = player;
    }

    @Override
    public void setCraftTableWindow(boolean isVisible) {
        enchantTableWindow.setVisible(isVisible);
    }

    public void setCraftTableWindowOpen(boolean isCraftTableWindowOpen) {
        this.isCraftTableWindowOpen = isCraftTableWindowOpen;
    }

    @Override
    public boolean getCraftTableWindowOpen() {
        return isCraftTableWindowOpen;
    }

    @Override
    public <T extends Ingredient> void create(T... ingredient) {

    }
}
