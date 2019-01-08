package JGame;

public class Sword extends Weapon {

    public Sword(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        name = "меч";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType = weaponType;
        this.damage = damage;
        quality = 100;

        switch (material){
            case COPPER: name = "Медный " + name; break;
            case IRON: name = "Железный " + name; break;
            case BRONZE: name = "Бронзовый " + name; break;
            case STEEL: name = "Стальной " + name; break;
            case MYTHRIL: name = "Мифриловый " + name; break;
            case ADAMANTINE: name = "Адамантиновый " + name; break;
            case ELVENMYTHRIL: name = "Меч из эльфийского мифрила"; break;
            case CRYSTAL: name = "Хрустальный " + name; break;
            case DEEP: name = "Меч бездны"; break;
            case GODSHEART: name = "Меч из сердца бога"; break;
            case ABSOLUTEZERO: name = "Меч начала и конца"; break;
        }
    }
}
