package shortages;

import entities.DemandEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demands {

    private final Map<LocalDate, DemandEntity> demandsPerDay;

    public Demands(List<DemandEntity> demands) {
        demandsPerDay = new HashMap<>();
        for (DemandEntity demand1 : demands) {
            demandsPerDay.put(demand1.getDay(), demand1);
        }

    }

    public Demand get(LocalDate day) {
        DemandEntity entity = demandsPerDay.get(day);
        return new Demand(entity);
    }
}
