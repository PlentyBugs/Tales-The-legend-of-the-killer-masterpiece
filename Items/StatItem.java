package Items;

import Creatures.StatsEnum;

public interface StatItem<T extends StatsEnum> {
    T getStat();
    int getStatPower();
}
