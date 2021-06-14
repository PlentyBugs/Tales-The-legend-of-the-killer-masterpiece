package AI;

import Creatures.AggressiveNPC.Aggressive;
import Creatures.GodCreature;
import Creatures.LiveCreature;
import Windows.BattleWindows.FightWindow;
import Windows.FieldWindow;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Primitive NPC controller
public class NPCController extends Thread implements Serializable {

    private Set<LiveCreature> NPC = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private FieldWindow fieldWindow;
    private boolean waiting = false;

    public void run(){/*
        while (true){
            if(!waiting){
                move();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }


    public void addNPC(LiveCreature liveCreature){
        if(liveCreature != null){
            NPC.add(liveCreature);
        }
    }

    public void clear(){
        NPC.clear();
    }

    private void move(){
        for(LiveCreature liveCreature : NPC){
            GodCreature[][] field = fieldWindow.getCurrentMap().getMap(fieldWindow.getPlayer().getX(), fieldWindow.getPlayer().getY());
            GodCreature elementByCoordinates = fieldWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY());
            if(elementByCoordinates instanceof LiveCreature creature && creature.getHp() <= 0){
                fieldWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
            }
            if(liveCreature instanceof Aggressive
                    && (
                        liveCreature.getX() == fieldWindow.getPlayer().getX() && liveCreature.getY()+1 == fieldWindow.getPlayer().getY() ||
                        liveCreature.getX() == fieldWindow.getPlayer().getX() && liveCreature.getY()-1 == fieldWindow.getPlayer().getY() ||
                        liveCreature.getX()+1 == fieldWindow.getPlayer().getX() && liveCreature.getY() == fieldWindow.getPlayer().getY() ||
                        liveCreature.getX()-1 == fieldWindow.getPlayer().getX() && liveCreature.getY() == fieldWindow.getPlayer().getY()
                        )){
                FightWindow fightWindow = new FightWindow(fieldWindow.getPlayer(), liveCreature, fieldWindow);
                waiting = true;
                break;
            } else {int chance = (int)(Math.random()*100); // Primitive. Better price-system will be added later
                if(chance < 25){
                    if(fieldWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY()+1) != null && fieldWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY()+1).getIsStep()){
                        fieldWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
                        fieldWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY()+1, liveCreature);
                        liveCreature.setY(liveCreature.getY()+1);
                    }
                } else if(chance < 50){
                    if(fieldWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY()-1) != null && fieldWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY()-1).getIsStep()){
                        fieldWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
                        fieldWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY()-1, liveCreature);
                        liveCreature.setY(liveCreature.getY()-1);
                    }
                } else if(chance < 75){
                    if(fieldWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX()+1, liveCreature.getY()) != null && fieldWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX()+1, liveCreature.getY()).getIsStep()){
                        fieldWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
                        fieldWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX()+1, liveCreature.getY(), liveCreature);
                        liveCreature.setX(liveCreature.getX()+1);
                    }
                } else{
                    if(fieldWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX()-1, liveCreature.getY()) != null && fieldWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX()-1, liveCreature.getY()).getIsStep()){
                        fieldWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
                        fieldWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX()-1, liveCreature.getY(), liveCreature);
                        liveCreature.setX(liveCreature.getX()-1);
                    }
                }
//                fieldWindow.drawMap(true);
            }
        }
    }

    public void setWindowInterface(FieldWindow fieldWindow) {
        this.fieldWindow = fieldWindow;
    }

    public void setWaiting(boolean w){
        waiting = w;
    }

    public boolean getWaiting(){
        return waiting;
    }
}
