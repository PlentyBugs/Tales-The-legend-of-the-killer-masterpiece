package Things.Craft;

import Items.BlackSmith.Resource.Resource;

public interface BlackSmithCraftTable extends CraftTable {
    void create(Resource ... Resource);
}
