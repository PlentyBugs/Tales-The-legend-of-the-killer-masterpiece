package Items.Weapons.Swords;

import Abilities.Buffs.DamageUpBuff;
import Abilities.Buffs.DecreaseDamageBuff;
import Creatures.LiveCreature;
import Items.Grade;
import Items.Material;
import Items.Rarity;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;
import Windows.BattleWindows.FightWindow;

public class Sword extends Weapon {
    private static final long serialVersionUID = -1973203379601612951L;

    public Sword(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "меч";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.damage = damage;
        quality = 100;
    }

    public Sword(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.ONEHANDED);
        name = "меч";
    }

    @Override
    public void weaponSkill(LiveCreature enemy, FightWindow fightWindow, LiveCreature attacker){
        if(weaponType.contains(WeaponType.TWOHANDED))
            injury(enemy, fightWindow);
        else if(weaponType.contains(WeaponType.ONEHANDED))
            battleTendency(attacker, fightWindow);
    }

    private void injury(LiveCreature enemy, FightWindow fightWindow){
        if(Math.random()*100 < 20)
            enemy.addBuffs(new DecreaseDamageBuff(30, 3));
    }

    private void battleTendency(LiveCreature attacker, FightWindow fightWindow){
        if(Math.random()*100 < 1)
            attacker.addBuffs(new DamageUpBuff(150, 2));
    }

    public Sword setWeaponType(WeaponType weaponType) {
        this.weaponType.add(weaponType);
        return this;
    }
}
