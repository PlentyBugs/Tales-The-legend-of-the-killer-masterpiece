package Things;

import LiveCreatures.Player;

import java.awt.*;

public class HealBlock extends Thing {
    public HealBlock(int x, int y){
        this.x = x;
        this.y = y;
        name = "Лужица Жизни";
        color = new Color(255,255,255);
        isStep = true;
    }

    public void heal(Player player){
        int healCount = player.getLvl()*50 + 100;
        if (healCount+player.getHp() > player.getMaxHp()){
            player.setHp(player.getMaxHp());
        } else {
            player.setHp(player.getHp() + healCount);
        }
    }
}
