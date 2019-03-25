package Things.Craft;

import Items.BlackSmith.Resource.Resource;

public interface BlackSmithCraftTable extends CraftTable {
    <T extends Resource> void create(T ... Resource);
}
