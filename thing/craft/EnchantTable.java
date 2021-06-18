package thing.craft;

import creature.Player;
import thing.Thing;
import window.craft.EnchantTableWindow;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class EnchantTable extends Thing implements CraftTable {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.ENCHANT_TABLE);
    }

    private boolean isCraftTableWindowOpen;
    private final EnchantTableWindow enchantTableWindow;

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
}
