package creature.peaceful.npc;

import creature.Player;
import creature.peaceful.BlackSmithCraftMan;
import creature.peaceful.Dealer;
import creature.peaceful.Inhabitant;

public class NPC {
    public static Inhabitant inhabitantDanil;
    public static Dealer dealerShutep;
    public static Dealer dealerPetush;
    public static BlackSmithCraftMan blacksmithDroghan;

    public NPC(Player player){
        dealerPetush = new PetushNPC(player);
        dealerPetush.setX(1);
        dealerPetush.setY(1);

        dealerShutep = new ShutepNPC(player);
        dealerShutep.setX(3);
        dealerShutep.setY(3);

        inhabitantDanil = new DanilNPC(player);
        inhabitantDanil.setX(2);
        inhabitantDanil.setY(2);

        blacksmithDroghan = new DroghanNPC(player);
        blacksmithDroghan.setX(7);
        blacksmithDroghan.setY(7);
    }
}
