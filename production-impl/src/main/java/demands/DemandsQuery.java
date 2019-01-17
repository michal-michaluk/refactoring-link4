package demands;

import dao.DemandDao;
import tools.Util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// Component
public class DemandsQuery {

    // Injected
    private DemandDao dao;

    public List<DemandReadModel> find(String productRefNo, LocalDateTime date) {
        return dao.findFrom(date, productRefNo).stream()
                .map(entity -> new DemandReadModel(
                        entity.getDay(),
                        Util.getLevel(entity),
                        Util.getDeliverySchema(entity)
                )).collect(Collectors.toList());
    }
}
