package Windows.CraftWindow;

import javax.swing.*;
import java.io.Serializable;

public class CraftWindow extends JFrame implements Serializable {
    private static final long serialVersionUID = 6273872975006011103L;

    public CraftWindow(){
        super("Окно создания");
    }

    public CraftWindow(String craftWindowName){
        super(craftWindowName);
    }
}
