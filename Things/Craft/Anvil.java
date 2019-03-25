package Things.Craft;

import Abilities.Passive.Professions.BlackSmith;
import Creatures.Player;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.BlackSmith.BluePrint;
import Items.BlackSmith.Resource.Resource;
import Items.Grade;
import Items.Item;
import Items.Material;
import Items.Rarity;
import Items.Weapons.Bows.Bow;
import Items.Weapons.Bows.LongBow;
import Items.Weapons.Bows.ShortBow;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Staffs.Staff;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;
import Things.Thing;
import Windows.CraftWindow.AnvilTableWindow;

import java.awt.*;

public class Anvil extends Thing implements BlackSmithCraftTable{

    private boolean isCraftTableWindowOpen;
    private AnvilTableWindow anvilTableWindow;
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
    public <T extends Resource> void create(T... resource) {
        Item item = new Item();

        int rank = player.getAbility(new BlackSmith()).getLevel()*player.getStats().getBlacksmith()/player.getLvl();

        switch (bluePrint.getItemType()){
            case AXE: item = new Axe(); break;
            case STAFF: item = new Staff(); break;
            case BOW: item = new Bow(); break;
            case SHORTBOW: item = new ShortBow(); break;
            case LONGBOW: item = new LongBow(); break;
            case SWORDONEHANDED: item = new Sword().setWeaponType(WeaponType.ONEHANDED); break;
            case SWORDTWOHANDED: item = new Sword().setWeaponType(WeaponType.TWOHANDED); break;
            case TORSO: item = new Torso(); break;
            case HELMET: item = new Helmet(); break;
            case RING: item = new Ring(); break;
        }

        item.setSelfForgedBonus(1 + rank/100.0);

        if(rank <= 15){
            item.setGrade(Grade.COMMON).setRarity(Rarity.COMMON);
        } else if(rank <= 15*2){
            item.setGrade(Grade.MAGIC).setRarity(Rarity.UNCOMMON);
        } else if(rank <= 15*3){
            item.setGrade(Grade.CURSE).setRarity(Rarity.RARE);
        } else if(rank <= 15*4){
            item.setGrade(Grade.ARTIFACT).setRarity(Rarity.MYSTICAL);
        } else if(rank <= 15*5){
            item.setGrade(Grade.ARTIFACT).setRarity(Rarity.LEGENDARY);
        } else if(rank <= 15*6){
            item.setGrade(Grade.HEROIC).setRarity(Rarity.DRAGON);
        } else if(rank <= 15*7){
            item.setGrade(Grade.ABOVETHEGODS).setRarity(Rarity.DIVINE);
        }

        switch (resource[0].getClass().getSimpleName()){
            case "Leather": item.setMaterial(Material.LEATHER); break;
            case "StuddedLeather": item.setMaterial(Material.STUDDEDLEATHER); break;
            case "Chain": item.setMaterial(Material.CHAIN); break;
            case "Copper": item.setMaterial(Material.COPPER); break;
            case "Iron": item.setMaterial(Material.IRON); break;
            case "Bronze": item.setMaterial(Material.BRONZE); break;
            case "Steel": item.setMaterial(Material.STEEL); break;
            case "Mythril": item.setMaterial(Material.MYTHRIL); break;
            case "Adamantine": item.setMaterial(Material.ADAMANTINE); break;
            case "ElvenMythril": item.setMaterial(Material.ELVENMYTHRIL); break;
            case "Crystal": item.setMaterial(Material.CRYSTAL); break;
            case "Deep": item.setMaterial(Material.DEEP); break;
            case "GodHeart": item.setMaterial(Material.GODSHEART); break;
            case "AbsoluteZero": item.setMaterial(Material.ABSOLUTEZERO); break;
        }
        item.countProperty();

        player.addItemToInventory(item);
        player.getFieldWindow().drawAllPlayerWindow();
    }

    public void setBluePrint(BluePrint bluePrint) {
        this.bluePrint = bluePrint;
    }
}
