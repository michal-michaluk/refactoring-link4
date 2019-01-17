package services.impl;

import api.AdjustDemandDto;
import dao.DemandDao;
import demands.DemandEvents;
import demands.Demandish;
import entities.DemandEntity;

class DemandRepository {

    private DemandDao demandDao;
    private DemandEvents events;

    public Demandish get(AdjustDemandDto adjustment) {
        DemandEntity demand = demandDao.getCurrent(adjustment.getProductRefNo(), adjustment.getAtDay());

        return new Demandish(demand, events);
    }
}
