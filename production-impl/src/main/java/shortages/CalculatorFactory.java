package shortages;

import entities.DemandEntity;
import entities.ProductionEntity;
import external.CurrentStock;
import infrastructure.DemandsRepository;
import infrastructure.ProductionPlanFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class CalculatorFactory {
    private LocalDate today;
    private int daysAhead;
    private CurrentStock stock;
    private List<ProductionEntity> productions;
    private List<DemandEntity> demands;


    private DemandsRepository demandsRepository;
    private ProductionPlanFactory planFactory;

    public CalculatorFactory(LocalDate today, int daysAhead, CurrentStock stock, List<ProductionEntity> productions, List<DemandEntity> demands) {
        this.today = today;
        this.daysAhead = daysAhead;
        this.stock = stock;
        this.productions = productions;
        this.demands = demands;
    }

    public ShortageCalculator create(String productRefNo) {
        List<LocalDate> dates = Stream.iterate(today, date -> date.plusDays(1))
                .limit(daysAhead)
                .collect(toList());

        ProductionOutputs outputs = planFactory.create(productions);
        Demands demandsPerDay = demandsRepository.get(productRefNo, today.atStartOfDay());
        long level = stock.getLevel();
        return new ShortageCalculator(dates, outputs, demandsPerDay, level);
    }
}
