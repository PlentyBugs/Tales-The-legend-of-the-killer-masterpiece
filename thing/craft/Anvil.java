package thing.craft;

import creature.Player;
import creature.StatsEnum;
import item.armor.Helmet;
import item.armor.Ring;
import item.armor.Torso;
import item.blacksmith.BluePrint;
import item.blacksmith.ItemCraftType;
import item.blacksmith.resource.Resource;
import item.Grade;
import item.Item;
import item.Material;
import item.Rarity;
import item.weapon.bow.Bow;
import item.weapon.bow.LongBow;
import item.weapon.bow.ShortBow;
import item.weapon.chop.Axe;
import item.weapon.staff.Staff;
import item.weapon.sword.Sword;
import item.weapon.WeaponType;
import thing.Thing;
import window.craft.AnvilTableWindow;
import window.WindowInterface;
import support.AbilityProperty;
import support.GeneralProperty;
import support.Property;
import support.ResourceProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Anvil extends Thing implements BlackSmithCraftTable {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.ANVIL);
    }


    private boolean isCraftTableWindowOpen;
    private final AnvilTableWindow anvilTableWindow;
    private Player player;
    private BluePrint bluePrint;

    public Anvil(){
        name = "Наковальня";
        color = new Color(48, 48, 48);
        isStep = false;
        anvilTableWindow = new AnvilTableWindow(this);
        setCraftTableWindow(false);
        setCraftTableWindowOpen(false);
    }

    @Override
    public void setPlayer(Player player) {
        anvilTableWindow.setPlayer(player);
        this.player = player;
    }

    @Override
    public void setCraftTableWindow(boolean isVisible) {
        anvilTableWindow.setVisible(isVisible);
    }

    public void setCraftTableWindowOpen(boolean isCraftTableWindowOpen) {
        this.isCraftTableWindowOpen = isCraftTableWindowOpen;
    }

    @Override
    public boolean getCraftTableWindowOpen() {
        return isCraftTableWindowOpen;
    }

    @Override
    public void create(Resource ... resources) {
        for (Resource resource : resources) {
            int rank = player.getAbility(AbilityProperty.BLACKSMITH).getLevel()*player.getStats().getBlacksmith()/player.getLvl();

            Item item = switch (bluePrint.getItemType()) {
                case AXE -> new Axe();
                case STAFF -> new Staff();
                case BOW -> new Bow();
                case SHORT_BOW -> new ShortBow();
                case LONGBOW -> new LongBow();
                case SWORD_ONE_HANDED -> new Sword().setWeaponType(WeaponType.ONE_HANDED);
                case SWORD_TWO_HANDED -> new Sword().setWeaponType(WeaponType.TWO_HANDED);
                case TORSO -> new Torso();
                case HELMET -> new Helmet();
                case RING -> new Ring();
            };

            item.setSelfForgedBonus(1 + rank/100.0);

            if(rank <= 15) {
                item.setGrade(Grade.COMMON).setRarity(Rarity.COMMON);
            } else if(rank <= 15*2) {
                item.setGrade(Grade.MAGIC).setRarity(Rarity.UNCOMMON);
            } else if(rank <= 15*3 ){
                item.setGrade(Grade.CURSE).setRarity(Rarity.RARE);
            } else if(rank <= 15*4) {
                item.setGrade(Grade.ARTIFACT).setRarity(Rarity.MYSTICAL);
            } else if(rank <= 15*5) {
                item.setGrade(Grade.ARTIFACT).setRarity(Rarity.LEGENDARY);
            } else if(rank <= 15*6) {
                item.setGrade(Grade.HEROIC).setRarity(Rarity.DRAGON);
            } else {
                item.setGrade(Grade.ABOVE_THE_GODS).setRarity(Rarity.DIVINE);
            }

            if (resource.getLastProperty() instanceof ResourceProperty lastProperty) {
                switch (lastProperty) {
                    case LEATHER -> item.setMaterial(Material.LEATHER);
                    case STUDDED_LEATHER -> item.setMaterial(Material.STUDDEDLEATHER);
                    case CHAIN -> item.setMaterial(Material.CHAIN);
                    case COPPER -> item.setMaterial(Material.COPPER);
                    case IRON -> item.setMaterial(Material.IRON);
                    case BRONZE -> item.setMaterial(Material.BRONZE);
                    case STEEL -> item.setMaterial(Material.STEEL);
                    case MYTHRIL -> item.setMaterial(Material.MYTHRIL);
                    case ADAMANTINE -> item.setMaterial(Material.ADAMANTINE);
                    case ELVEN_MYTHRIL -> item.setMaterial(Material.ELVENMYTHRIL);
                    case CRYSTAL -> item.setMaterial(Material.CRYSTAL);
                    case DEEP -> item.setMaterial(Material.DEEP);
                    case GODS_HEART -> item.setMaterial(Material.GODSHEART);
                    case ABSOLUTE_ZERO -> item.setMaterial(Material.ABSOLUTEZERO);
                }
            }

            if (bluePrint.getItemType() == ItemCraftType.RING && item instanceof Ring ring){
                ring.setStat(new StatsEnum[]{
                        StatsEnum.STRENGTH,
                        StatsEnum.SPEED,
                        StatsEnum.AGILITY,
                        StatsEnum.INTELLIGENCE,
                        StatsEnum.LUCK,
                        StatsEnum.ELOQUENCE,
                        StatsEnum.BLACKSMITH,
                        StatsEnum.THEFT,
                        StatsEnum.ALCHEMY,
                        StatsEnum.ONE_HANDED_WEAPON,
                        StatsEnum.TWO_HANDED_WEAPON,
                        StatsEnum.POLE_WEAPON,
                        StatsEnum.CHOPPING_WEAPON,
                        StatsEnum.LONG_RANGE_WEAPON,
                        StatsEnum.KNOWLEDGE,
                        StatsEnum.ENERGY
                }[(int)(Math.random()*16)]);
            }

            item.countProperty();

            player.addItemToInventory(item);
            WindowInterface windowInterface = player.getWindowInterface();
            windowInterface.drawAllPlayerWindow(player, windowInterface);
        }
    }

    public void setBluePrint(BluePrint bluePrint) {
        this.bluePrint = bluePrint;
    }
}
