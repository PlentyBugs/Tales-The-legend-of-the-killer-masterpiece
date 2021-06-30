package creature.peaceful.npc;

import abilities.passive.professions.BlackSmith;
import creature.Player;
import creature.peaceful.BlackSmithCraftMan;
import item.blacksmith.BluePrint;
import item.blacksmith.ItemCraftType;
import item.blacksmith.resource.Adamantine;
import item.blacksmith.resource.Mythril;
import item.tool.Pickaxe;
import support.CreatureProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class DroghanNPC extends BlackSmithCraftMan {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(BlackSmithCraftMan.propertyList);
        propertyList.add(CreatureProperty.DROGHAN);
    }

    public DroghanNPC(Player player) {
        super(7,7,"Дрогхан", 15623, 8461315);
        addItemToInventory(
                new BluePrint()
                        .setType(ItemCraftType.SWORD_ONE_HANDED)
                        .addResource(new Adamantine())
                        .setName("Чертеж адамантинового меча")
                        .setCost(892000)
                        .setTemperature(3000),
                new BluePrint()
                        .setType(ItemCraftType.SWORD_ONE_HANDED)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового меча")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.AXE)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Топора")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.BOW)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Лука")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.LONGBOW)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Длинного лука")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.SHORT_BOW)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Короткого лука")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.STAFF)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Посоха")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.RING)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Кольца")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.HELMET)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Шлема")
                        .setCost(150000)
                        .setTemperature(1300),
                new BluePrint()
                        .setType(ItemCraftType.TORSO)
                        .addResource(new Mythril())
                        .setName("Чертеж мифрилового Доспеха")
                        .setCost(150000)
                        .setTemperature(1300),
                new Pickaxe().setCost(10000)
        );
        addConversationShop(1);
        addConversationTrain(2, "Тренировка",
                new Object[] {new BlackSmith(), 99000, 1}
        );
        getConversationWindow().setPlayer(player);
    }
}
