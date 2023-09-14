package il.ac.shenkar.java.costmanager.domain.repository.interfaces;

import il.ac.shenkar.java.costmanager.domain.model.Cost;

import java.util.Date;
import java.util.List;

public interface CostRepository {
    void addCost(Cost cost);
    List<Cost> getCosts(Date date);
}
