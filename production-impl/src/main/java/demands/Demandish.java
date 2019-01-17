package demands;

import api.AdjustDemandDto;
import entities.DemandEntity;
import entities.ManualAdjustmentEntity;
import enums.DeliverySchema;

public class Demandish {

    private DemandEntity demand;
    private DemandEvents events;

    public Demandish(DemandEntity demand, DemandEvents events) {
        this.demand = demand;
        this.events = events;
    }

    public void adjustDemand(AdjustDemandDto adjustment) {
        ManualAdjustmentEntity manualAdjustment = new ManualAdjustmentEntity();
        manualAdjustment.setLevel(adjustment.getLevel());
        manualAdjustment.setNote(adjustment.getNote());
        manualAdjustment.setDeliverySchema(adjustment.getDeliverySchema());

        demand.getAdjustment().add(manualAdjustment);

        events.emit(new DemandChanged(
                demand.getProductRefNo(),
                demand.getDay(),
                getLevel(),
                getDeliverySchema()
        ));
    }

    private long getLevel() {
        if (demand.getAdjustment().isEmpty()) {
            return demand.getOriginal().getLevel();
        } else {
            return demand.getAdjustment().get(demand.getAdjustment().size() - 1).getLevel();
        }
    }

    private DeliverySchema getDeliverySchema() {
        DeliverySchema deliverySchema;
        if (demand.getAdjustment().isEmpty()) {
            deliverySchema = demand.getOriginal().getDeliverySchema();
        } else {
            deliverySchema = demand.getAdjustment().get(demand.getAdjustment().size() - 1).getDeliverySchema();
        }
        if (deliverySchema == null) {
            return Util.defaultFor(demand.getProductRefNo());
        }
        return deliverySchema;
    }
}
