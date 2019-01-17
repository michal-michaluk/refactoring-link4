package shortages;

import entities.DemandEntity;
import enums.DeliverySchema;
import tools.Util;

public class Demand {
    private final DemandEntity entity;

    public Demand(DemandEntity entity) {
        this.entity = entity;
    }

    public boolean hasDeliverySchema(DeliverySchema deliverySchema) {
        return Util.getDeliverySchema(entity) == deliverySchema;
    }

    public long getLevel() {
        return Util.getLevel(entity);
    }
}
