package demands;

import java.time.LocalDateTime;
import java.util.List;

public interface DemandReadModelDao {
    List<DemandReadModel> findFrom(LocalDateTime date, String productRefNo);

    void saveOrUpdate(DemandReadModel demandReadModel);
}
