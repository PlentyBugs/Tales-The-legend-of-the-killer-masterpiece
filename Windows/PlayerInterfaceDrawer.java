package Windows;

import Creatures.Player;

public interface PlayerInterfaceDrawer {

    default void drawAllPlayerWindow(Player player, MapDrawer mapDrawer) {
        player.getInventoryWindow().drawInventory();
        player.getUpStatsWindow().drawWindow();
        player.getEquipmentWindow().drawEquipment();
        player.getPlayerInfoWindow().drawInfo();
        player.getPlayerAbilityWindow().drawWindow();
        player.getPlayerQuestWindow().drawWindow();
        player.getDiseasesWindow().drawWindow();
        mapDrawer.drawMap();
    }
}
