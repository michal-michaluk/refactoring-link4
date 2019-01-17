package shortages;

import java.time.LocalDate;
import java.util.Map;

public class Demands {

    private final Map<LocalDate, Demand> demandsPerDay;

    public Demands(Map<LocalDate, Demand> demandsPerDay) {
        this.demandsPerDay = demandsPerDay;
    }

    public Demand get(LocalDate day) {
        return demandsPerDay.getOrDefault(day, null);
    }

    public static class Demand {
        private final long level;
        private final LevelOnDeliveryStrategy strategy;

        public Demand(long level, LevelOnDeliveryStrategy strategy) {
            this.level = level;
            this.strategy = strategy;
        }

        public long getLevel() {
            return level;
        }

        public long calculate(long level, long produced) {
            return strategy.calculate(level, this, produced);
        }
    }
}
