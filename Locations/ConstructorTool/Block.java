package Locations.ConstructorTool;

import LiveCreatures.GodCreature;

public class Block {
    private BlockType blockType;
    private GodCreature block;
    private boolean isEditable;

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public GodCreature getBlock() {
        return block;
    }

    public void setBlock(GodCreature block) {
        this.block = block;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }
    public boolean getEditable(){
        return isEditable;
    }
}
