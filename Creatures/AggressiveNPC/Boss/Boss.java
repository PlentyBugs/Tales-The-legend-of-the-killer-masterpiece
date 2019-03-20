package Creatures.AggressiveNPC.Boss;

import Creatures.LiveCreature;
import Items.Item;
import Items.Key;

public class Boss extends LiveCreature {

    protected Item[] dropItems;
    protected Key key;

    public Item[] getDropItems() {
        return dropItems;
    }

    public Boss getRandomBoss(){
        return new Boss[]{new Frank(), new HigherGhost(), new DeadGuardian()}[(int)(Math.random()*3)];
    }

    public Key getKey() {
        return key;
    }
}
