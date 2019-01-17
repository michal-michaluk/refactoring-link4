package demands;

import enums.DeliverySchema;

import java.time.LocalDate;

public class DemandChanged {
    private final String productRefNo;
    private final LocalDate day;
    private final long level;
    private final DeliverySchema deliverySchema;

    public DemandChanged(String productRefNo, LocalDate day, long level, DeliverySchema deliverySchema) {
        this.productRefNo = productRefNo;
        this.day = day;
        this.level = level;
        this.deliverySchema = deliverySchema;
    }

    public String getProductRefNo() {
        return productRefNo;
    }

    public LocalDate getDay() {
        return day;
    }

    public long getLevel() {
        return level;
    }

    public DeliverySchema getDeliverySchema() {
        return deliverySchema;
    }
}
