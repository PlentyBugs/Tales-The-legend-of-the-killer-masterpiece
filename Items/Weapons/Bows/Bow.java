package Items.Weapons.Bows;

import Creatures.LiveCreature;
import Creatures.Player;
import Items.*;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;
import Windows.BattleWindows.FightWindow;

public class Bow extends Weapon {
    private static final long serialVersionUID = -8079067509250088207L;

    public Bow(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "Лук";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.damage = damage;
        quality = 100;
    }

    public Bow(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.LONGRANGE);
        name = "Лук";
    }
    @Override
    public void weaponSkill(LiveCreature enemy, FightWindow fightWindow, LiveCreature attacker){
        steal(attacker, enemy, fightWindow);
    }

    public void steal(LiveCreature attacker, LiveCreature enemy, FightWindow fightWindow){
        if(Math.random()*100 < 5){
            double count = 0;
            if(getDamage()*7 > enemy.getMoney()){
                attacker.addMoney(enemy.getMoney());
                count = enemy.getMoney();
                enemy.reduceMoney(enemy.getMoney());
            } else {
                attacker.addMoney(getDamage()*7);
                count = getDamage()*7;
                enemy.reduceMoney(getDamage()*7);
            }
            if(attacker instanceof Player){
                fightWindow.writeToPlayerConsole(attacker.getName() + " украл у " + enemy.getName() + count + " золотых");
            } else {
                fightWindow.writeToEnemyActionConsole(attacker.getName() + " украл у " + enemy.getName() + count + " золотых");
            }
        }
    }
}
