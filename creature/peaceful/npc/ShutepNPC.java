package creature.peaceful.npc;

import abilities.active.DamageUp;
import abilities.active.DecreaseDamage;
import abilities.active.Rage;
import abilities.auras.Vision;
import conversation.CatalogItem;
import creature.Player;
import creature.peaceful.Dealer;
import item.Grade;
import item.Material;
import item.Rarity;
import item.weapon.WeaponType;
import item.weapon.sword.Sword;
import support.CreatureProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class ShutepNPC extends Dealer {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Dealer.propertyList);
        propertyList.add(CreatureProperty.SHUTEP);
    }

    public ShutepNPC(Player player) {
        super(3,3,"Шутеп", 15623, 8461315);
        Sword shutepSwordForSale = new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED);
        shutepSwordForSale.countProperty();
        addItemToInventory(new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED).countProperty(), new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED).countProperty(), new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED).countProperty(), new Sword(Material.CRYSTAL, Rarity.RARE, Grade.CURSE, 3, WeaponType.ONE_HANDED).countProperty());
        addConversationShop(1);
        addConversationTrain(
                2,
                    "Тренировка",
                    new CatalogItem(new DamageUp(), 99000, 1),
                    new CatalogItem(new DecreaseDamage(), 99000, 1),
                    new CatalogItem(new Vision(), 99000, 1),
                    new CatalogItem(new Rage(), 852000, 1)
                );
        getConversationWindow().setPlayer(player);
    }
}
