package infrastructure;

import demands.DemandReadModel;
import demands.DemandsQuery;
import enums.DeliverySchema;
import shortages.Demands;
import shortages.LevelOnDeliveryStrategy;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemandsRepository {

    private DemandsQuery demandSource;
    private final Map<DeliverySchema, LevelOnDeliveryStrategy> mapping = init();

    public Demands get(String productRefNo, LocalDateTime date) {
        List<DemandReadModel> demands = demandSource.find(productRefNo, date);

        return new Demands(demands.stream().collect(Collectors.toMap(
                DemandReadModel::getDay,
                demand -> new Demands.Demand(
                        demand.getLevel(),
                        pickVariant(demand.getDeliverySchema())
                ))
        ));
    }

    private LevelOnDeliveryStrategy pickVariant(DeliverySchema deliverySchema) {
        return mapping.getOrDefault(deliverySchema, (level, demand, produced) -> {
            throw new NotImplementedException();
        });
    }

    private Map<DeliverySchema, LevelOnDeliveryStrategy> init() {
        Map<DeliverySchema, LevelOnDeliveryStrategy> mapping = new HashMap<>();
        mapping.put(DeliverySchema.atDayStart, LevelOnDeliveryStrategy.atDayStart);
        mapping.put(DeliverySchema.tillEndOfDay, LevelOnDeliveryStrategy.tillEndOfDay);
        return Collections.unmodifiableMap(mapping);
    }
}
