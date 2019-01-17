package demands;

import enums.DeliverySchema;

import java.time.LocalDate;

public class DemandReadModel {
    private final LocalDate date;
    private final long level;
    private final DeliverySchema deliverySchema;

    public DemandReadModel(LocalDate date, long level, DeliverySchema deliverySchema) {
        this.date = date;
        this.level = level;
        this.deliverySchema = deliverySchema;
    }

    public long getLevel() {
        return level;
    }

    public DeliverySchema getDeliverySchema() {
        return deliverySchema;
    }

    public LocalDate getDay() {
        return date;
    }
}
