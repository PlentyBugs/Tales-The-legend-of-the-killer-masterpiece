package Items;

public class Helmet extends Armor {
    public Helmet(Material material, Rarity rarity, Grade grade, int protection){
        name = "шлем";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.protection = protection;
        quality = 100;

        switch (material){
            case LEATHER: name = "Кожаный " + name; break;
            case STUDDEDLEATHER: name = "Шлем из шипованной кожи"; break;
            case CHAIN: name = "Кальчужный " + name; break;
            case COPPER: name = "Кожанный " + name; break;
            case IRON: name = "Железный " + name; break;
            case BRONZE: name = "Бронзовый " + name; break;
            case STEEL: name = "Стальной " + name; break;
            case MYTHRIL: name = "Мифриловый " + name; break;
            case ADAMANTINE: name = "Адамантиновый " + name; break;
            case ELVENMYTHRIL: name = "Шлем из эльфийского мифрила"; break;
            case CRYSTAL: name = "Хрустальный " + name; break;
            case DEEP: name = "Шлем бездны"; break;
            case GODSHEART: name = "Шлем из сердца бога"; break;
            case ABSOLUTEZERO: name = "Шлем начала и конца"; break;
        }
    }
}
