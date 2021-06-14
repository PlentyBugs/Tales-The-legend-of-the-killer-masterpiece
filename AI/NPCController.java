package AI;

import Creatures.AggressiveNPC.Aggressive;
import Creatures.GodCreature;
import Creatures.LiveCreature;
import Windows.BattleWindows.FightWindow;
import Windows.GameWindow;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Primitive NPC controller
public class NPCController extends Thread implements Serializable {

    private Set<LiveCreature> NPC = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private GameWindow gameWindow;
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
            GodCreature[][] field = gameWindow.getCurrentMap().getMap(gameWindow.getPlayer().getX(), gameWindow.getPlayer().getY());
            GodCreature elementByCoordinates = gameWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY());
            if(elementByCoordinates instanceof LiveCreature creature && creature.getHp() <= 0){
                gameWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
            }
            if(liveCreature instanceof Aggressive
                    && (
                        liveCreature.getX() == gameWindow.getPlayer().getX() && liveCreature.getY()+1 == gameWindow.getPlayer().getY() ||
                        liveCreature.getX() == gameWindow.getPlayer().getX() && liveCreature.getY()-1 == gameWindow.getPlayer().getY() ||
                        liveCreature.getX()+1 == gameWindow.getPlayer().getX() && liveCreature.getY() == gameWindow.getPlayer().getY() ||
                        liveCreature.getX()-1 == gameWindow.getPlayer().getX() && liveCreature.getY() == gameWindow.getPlayer().getY()
                        )){
                FightWindow fightWindow = new FightWindow(gameWindow.getPlayer(), liveCreature, gameWindow);
                waiting = true;
                break;
            } else {int chance = (int)(Math.random()*100); // Primitive. Better price-system will be added later
                if(chance < 25){
                    if(gameWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY()+1) != null && gameWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY()+1).getIsStep()){
                        gameWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
                        gameWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY()+1, liveCreature);
                        liveCreature.setY(liveCreature.getY()+1);
                    }
                } else if(chance < 50){
                    if(gameWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY()-1) != null && gameWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX(), liveCreature.getY()-1).getIsStep()){
                        gameWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
                        gameWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY()-1, liveCreature);
                        liveCreature.setY(liveCreature.getY()-1);
                    }
                } else if(chance < 75){
                    if(gameWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX()+1, liveCreature.getY()) != null && gameWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX()+1, liveCreature.getY()).getIsStep()){
                        gameWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
                        gameWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX()+1, liveCreature.getY(), liveCreature);
                        liveCreature.setX(liveCreature.getX()+1);
                    }
                } else{
                    if(gameWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX()-1, liveCreature.getY()) != null && gameWindow.getCurrentMap().getElementByCoordinates(liveCreature.getX()-1, liveCreature.getY()).getIsStep()){
                        gameWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX(), liveCreature.getY(), null);
                        gameWindow.getCurrentMap().setElementByCoordinatesUpper(liveCreature.getX()-1, liveCreature.getY(), liveCreature);
                        liveCreature.setX(liveCreature.getX()-1);
                    }
                }
//                fieldWindow.drawMap(true);
            }
        }
    }

    public void setWindowInterface(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void setWaiting(boolean w){
        waiting = w;
    }

    public boolean getWaiting(){
        return waiting;
    }
}
