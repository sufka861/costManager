package il.ac.shenkar.java.costmanager.domain.usecase.interfaces;

import il.ac.shenkar.java.costmanager.domain.model.Cost;

import java.util.List;

public interface GetCostReportUseCase {
    List<Cost> getCostsByDate(String date);

}
