package ConstructorTool;

public class Constructor {

    public static void main(String[] args){
        Block choosedBlock = new Block();
        ToolMode toolMode = new ToolMode();
        toolMode.setToolModeEnum(ToolModeEnum.BUILD);

        ConstructorField constructorField = new ConstructorField(choosedBlock, toolMode);
        BlockChooser blockChooser = new BlockChooser(choosedBlock, constructorField, toolMode);
    }
}
