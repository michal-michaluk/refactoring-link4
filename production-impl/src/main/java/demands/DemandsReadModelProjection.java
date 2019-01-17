package demands;

// Component
public class DemandsReadModelProjection {

    // Injected
    private DemandReadModelDao dao;

    void apply(DemandChanged event) {
        dao.saveOrUpdate(
                new DemandReadModel(
                        event.getDay(),
                        event.getLevel(),
                        event.getDeliverySchema())
        );
    }
}
