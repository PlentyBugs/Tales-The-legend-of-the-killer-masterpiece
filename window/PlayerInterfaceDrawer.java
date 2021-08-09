package window;

import creature.Player;

public interface PlayerInterfaceDrawer {

    default void drawAllPlayerWindow(Player player, MapDrawer mapDrawer) {
        player.getInventoryWindow().printItems();
        player.getUpStatsWindow().drawWindow();
        player.getEquipmentWindow().drawWindow();
        player.getPlayerInfoWindow().drawWindow();
        player.getPlayerAbilityWindow().drawWindow();
        player.getPlayerQuestWindow().drawWindow();
        player.getDiseasesWindow().drawWindow();
        mapDrawer.drawMap();
    }
}
