package Items.Alchemy.Ingredients;

import Effects.EffectType;
import Effects.Heal;

import java.awt.*;

public class GreenHerb extends Ingredient{

    public GreenHerb(){
        super();
        name = "Зеленая трава";
        color = new Color(114, 255, 155);
        effect = new Heal(EffectType.MOMENT);
    }
}
