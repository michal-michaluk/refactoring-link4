package shortages;

public interface LevelOnDeliveryStrategy {

    LevelOnDeliveryStrategy atDayStart = (level, demand, produced) -> level - demand.getLevel();
    LevelOnDeliveryStrategy tillEndOfDay = (level, demand, produced) -> level - demand.getLevel() + produced;

    long calculate(long level, Demands.Demand demand, long produced);
}
