package infrastructure;

import entities.ProductionEntity;
import shortages.ProductionOutputs;

import java.util.List;
import java.util.stream.Collectors;

public class ProductionPlanFactory {
    public ProductionOutputs create(List<ProductionEntity> productions) {

        return new ProductionOutputs(productions.stream()
                .collect(Collectors.groupingBy(production -> production.getStart().toLocalDate(),
                        Collectors.summingLong(ProductionEntity::getOutput))),
                productions.stream()
                        .map(e -> e.getForm().getRefNo())
                        .findFirst()
                        .orElse(null)
        );
    }
}
