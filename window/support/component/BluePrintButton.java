package window.support.component;

import item.blacksmith.BluePrint;

import javax.swing.*;

public class BluePrintButton extends JButton {
    private String nameStandard;
    private BluePrint bluePrint;
    private int countOfBluePrints;

    public BluePrintButton(String text){
        this.nameStandard = text;
        countOfBluePrints = 0;
        setText(text);
    }

    public void setCountOfBluePrints(int countOfBluePrints) {
        this.countOfBluePrints = countOfBluePrints;
    }

    public int getCountOfBluePrints() {
        return countOfBluePrints;
    }

    public void setNameStandard(String nameStandard) {
        this.nameStandard = nameStandard;
    }

    public BluePrint getBluePrint() {
        return bluePrint;
    }

    public void setBluePrint(BluePrint bluePrint) {
        setNameStandard(bluePrint.getName());
        this.bluePrint = bluePrint;
    }

    public boolean writeText(){
        if(countOfBluePrints > 0){
            setText(nameStandard);
            return true;
        } else {
            setText("Ресурс");
            return false;
        }
    }
}
