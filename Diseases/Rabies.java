package Diseases;

public class Rabies extends Disease {

    public Rabies(){
        name = "Бешенство";
        danger = Danger.DEADLY;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Rabies)
            return 0;
        return -1;
    }
}
