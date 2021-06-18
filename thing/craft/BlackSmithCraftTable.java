package thing.craft;

import item.blacksmith.resource.Resource;

public interface BlackSmithCraftTable extends CraftTable {
    void create(Resource ... Resource);
}
