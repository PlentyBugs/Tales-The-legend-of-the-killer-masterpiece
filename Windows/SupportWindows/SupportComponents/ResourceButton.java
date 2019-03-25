package Windows.SupportWindows.SupportComponents;

import Items.BlackSmith.Resource.Resource;

import javax.swing.*;

public class ResourceButton extends JButton {
    private String nameStandard;
    private Resource resource;
    private int countOfResources;

    public ResourceButton(String text){
        this.nameStandard = text;
        countOfResources = 0;
        setText(text);
    }

    public void setCountOfResources(int countOfResources) {
        this.countOfResources = countOfResources;
    }

    public int getCountOfResources() {
        return countOfResources;
    }

    public void setNameStandard(String nameStandard) {
        this.nameStandard = nameStandard;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource ingredient) {
        setNameStandard(ingredient.getName());
        this.resource = ingredient;
    }

    public boolean writeText(){
        if(countOfResources > 0){
            setText(nameStandard);
            return true;
        } else {
            setText("Ресурс");
            return false;
        }
    }
}
