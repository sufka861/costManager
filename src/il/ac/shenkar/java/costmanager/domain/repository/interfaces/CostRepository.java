package il.ac.shenkar.java.costmanager.domain.repository.interfaces;

import il.ac.shenkar.java.costmanager.domain.model.Cost;

import java.util.Date;
import java.util.List;

public interface CostRepository {
    void addCost(Cost cost);
    Cost getCostById(int costId);
    List<Cost> getCostsByDate(Date date);
    List<Cost> getAllCosts();
    void updateCost(Cost cost);
    void deleteCost(int costId);

}
