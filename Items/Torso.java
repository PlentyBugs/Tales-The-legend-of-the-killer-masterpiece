package Items;

public class Torso extends Armor {
    public Torso(Material material, Rarity rarity, Grade grade, int protection){
        name = "броня";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.protection = protection;
        quality = 100;

        switch (material){
            case LEATHER: name = "Кожаная " + name; break;
            case STUDDEDLEATHER: name = "Броня из шипованной кожи"; break;
            case CHAIN: name = "Кальчужная " + name; break;
            case COPPER: name = "Кожанная " + name; break;
            case IRON: name = "Железная " + name; break;
            case BRONZE: name = "Бронзовая " + name; break;
            case STEEL: name = "Стальная " + name; break;
            case MYTHRIL: name = "Мифриловая " + name; break;
            case ADAMANTINE: name = "Адамантиновая " + name; break;
            case ELVENMYTHRIL: name = "Броня из эльфийского мифрила"; break;
            case CRYSTAL: name = "Хрустальная " + name; break;
            case DEEP: name = "Броня бездны"; break;
            case GODSHEART: name = "Броня из сердца бога"; break;
            case ABSOLUTEZERO: name = "Броня начала и конца"; break;
        }
    }
}
