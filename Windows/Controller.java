package Windows;

import Creatures.GodCreature;
import Creatures.LiveCreature;
import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Items.BlackSmith.Resource.Resource;
import Items.Tools.Pickaxe;
import Locations.Cave.Cave;
import Locations.Dungeon.Dungeon;
import Locations.Map;
import Things.AlchemyThings.IngredientThing;
import Things.ChestLike.Chest;
import Things.Craft.CraftTable;
import Things.Doors.CaveDoor;
import Things.Doors.Door;
import Things.Doors.DoorToUpperLevelLocation;
import Things.Grass;
import Things.HealBlock;
import Things.Ore;
import Things.Stone;
import support.AbilityProperty;

import java.io.Serializable;

public interface Controller extends WindowProvider, MapProvider, MapDrawer, PlayerInterfaceDrawer {

    default void step(GodCreature creature) {

        final Map map = getMap();
        Player player = map.getPlayer();

        boolean isStep = creature.getIsStep();
        Step step;
        int X = creature.getX();
        int Y = creature.getY();

        boolean isLiveCreature = creature instanceof LiveCreature;
        boolean isAlchemyThings = creature.getClass().toString().contains("AlchemyThings");
        boolean isOre = creature instanceof Ore;
        boolean isHealBlock = creature instanceof HealBlock;
        boolean isDoorToUpperLevel = creature instanceof Door;
        boolean isChest = creature instanceof Chest;
        boolean isCraft = creature instanceof CraftTable;

//        if(!(creature instanceof Player) && creature instanceof LiveCreature){
//            npcController.addNPC((LiveCreature)creature);
//        }

        if(player.hasAbility(AbilityProperty.LITTLE_FOOL) && creature.getClass().toString().contains("Tree")){
            isStep = true;
        }

        WindowInterface windowInterface = getWindow();
        if (isHealBlock){
            step = (Step & Serializable)() -> {
                player.setWindowInterface(windowInterface);
                map.setElementByCoordinates(creature.getX(), creature.getY(), new Grass(creature.getX(), creature.getY()));
                ((HealBlock) creature).heal(player);
                int healBlockY = (int)(Math.random()*(map.getMapHeight()-1));
                int healBlockX = (int)(Math.random()*(map.getMapWidth()-1));
                map.setElementByCoordinates(healBlockX, healBlockY, new HealBlock(healBlockX, healBlockY));
                drawMap();
                drawAllPlayerWindow(player, this);
            };
        } else if (isDoorToUpperLevel) {
            step = (Step & Serializable)() -> {
                //todo add function to the Door class to check the key
                if(!((Door)creature).getIsLocked() || (((Door)creature).getIsLocked() && player.hasItem(((Door)creature).getKey()))){
                    if(((Door)creature).getIsLocked()){
                        player.removeItem(((Door)creature).getKey());
                        ((Door)creature).setIsLocked(false);
                    }
                    map.setPlayerX(player.getX());
                    map.setPlayerY(player.getY());
                    ((Door)creature).setIn(map);
                    if(creature instanceof DoorToUpperLevelLocation){
                        ((Door)creature).setGeneration(() -> {
                            Map newMap;
                            if(((Door)creature).getOut() == null){
                                newMap = new Map();

                                Dungeon dungeon = new Dungeon(player);
                                GodCreature[][][] zxc = dungeon.getMap();
                                newMap.setMapLowerObjects(zxc[0]);
                                newMap.setMapUpperObjects(zxc[1]);
                                newMap.setLocationName(dungeon.getLocationName());
                                ((Door)creature).setOut(newMap);
                                player.setX(dungeon.getPlayerXSafety());
                                player.setY(dungeon.getPlayerYSafety());
                                DoorToUpperLevelLocation door = new DoorToUpperLevelLocation();
                                door.setOut(map);
                                door.setIsLocked(false);
                                newMap.setElementByCoordinates(player.getX(), player.getY(), door);
                            } else {
                                newMap = ((Door)creature).getOut();
                                player.setX(newMap.getPlayerX());
                                player.setY(newMap.getPlayerY());
                            }
                            newMap.setPlayer(player);
                            return newMap;
                        });
                    } else if(creature instanceof CaveDoor){
                        ((Door)creature).setGeneration(() -> {
                            Map newMap;
                            if(((Door)creature).getOut() == null){
                                newMap = new Map();
                                Cave cave = new Cave();

                                newMap.setMapLowerObjects(cave.getCave());
                                ((Door)creature).setOut(newMap);

                                player.setX(cave.getPlayerSafeX());
                                player.setY(cave.getPlayerSafeY());

                                CaveDoor door = new CaveDoor();
                                door.setOut(map);
                                door.setIsLocked(false);
                                newMap.setElementByCoordinates(player.getX(), player.getY(), door);
                            } else {
                                newMap = ((Door)creature).getOut();
                                player.setX(newMap.getPlayerX());
                                player.setY(newMap.getPlayerY());
                            }
                            newMap.setPlayer(player);
                            return newMap;
                        });
                    }
                    setMap(((Door) creature).generate());
                    player.setWindowInterface(windowInterface);
                    drawMap();
                }
                drawAllPlayerWindow(player, this);
            };
        } else if(isCraft){
            step = (Step & Serializable)() -> {
                ((CraftTable) creature).setPlayer(player);
                if(!((CraftTable) creature).getCraftTableWindowOpen()){
                    ((CraftTable) creature).setCraftTableWindow(true);
                    ((CraftTable) creature).setCraftTableWindowOpen(true);
                } else {
                    ((CraftTable) creature).setCraftTableWindow(false);
                    ((CraftTable) creature).setCraftTableWindowOpen(false);
                }
            };
        }else if (isChest) {
            step = (Step & Serializable)() -> {
                player.setWindowInterface(windowInterface);
                if(!((Chest) creature).getIsInventoryChestOpen()){
                    ((Chest) creature).setPlayer(player);
                    ((Chest) creature).setInventoryWindow();
                    ((Chest) creature).setInventoryWindowChestIsVisible(true);
                    ((Chest) creature).setInventoryChestOpen(true);
                } else {
                    ((Chest) creature).getInventoryWindow().close();
                    ((Chest) creature).setInventoryWindowChestIsVisible(false);
                    ((Chest) creature).setInventoryChestOpen(false);
                }
                drawAllPlayerWindow(player, this);
            };
        } else {
            boolean finalIsStep = isStep;
            step = (Step & Serializable)() -> {
                if (finalIsStep) {
                    player.setWindowInterface(windowInterface);
                    player.setX(X);
                    player.setY(Y);
                    if (isAlchemyThings) {
                        player.addItemToInventory((Ingredient) ((IngredientThing) creature).getIngredient());
                        GodCreature godCreature = (GodCreature) ((IngredientThing) creature).getParent();
                        godCreature.setX(X);
                        godCreature.setY(Y);
                        map.setElementByCoordinates(X, Y, godCreature);
                    }
                    if (isOre && player.hasItem(new Pickaxe())) {
                        player.addItemToInventory((Resource) ((Ore) creature).getResource());
                        GodCreature godCreature = new Stone();
                        godCreature.setX(X);
                        godCreature.setY(Y);
                        map.setElementByCoordinates(X, Y, godCreature);
                    }
                    drawMap();
                } else if (isLiveCreature) {
                    player.setWindowInterface(windowInterface);
                    if (((LiveCreature) creature).getTalkative()) {
                        ((LiveCreature) creature).setConversationWindowPlayer(player);
                        if (!((LiveCreature) creature).getIsConversationWindowOpen()) {
                            ((LiveCreature) creature).setConversationWindowIsVisible(true);
                            ((LiveCreature) creature).setConversationWindowOpen(true);
                        } else {
                            ((LiveCreature) creature).setConversationWindowIsVisible(false);
                            ((LiveCreature) creature).setConversationWindowOpen(false);
                        }
                    } else {
                        player.setWindowInterface(windowInterface);
                        if (((LiveCreature) creature).getHp() == 0) {
                            ((LiveCreature) creature).countStatsAfterBorn();
                        }
                        if (creature.getChooseEnemyWindow() == null) {
                            creature.setChooseEnemyWindow(player, windowInterface, (LiveCreature) creature);
                        }
                        if (!creature.getIsChooseEnemyWindowOpen()) {
                            creature.setChooseEnemyWindowIsVisible(true);
                            creature.setChooseEnemyWindowOpen(true);
                        } else {
                            creature.setChooseEnemyWindowIsVisible(false);
                            creature.setChooseEnemyWindowOpen(false);
                        }
                    }
                }
                drawAllPlayerWindow(player, this);
            };
        }
        step.step();
    }
}
