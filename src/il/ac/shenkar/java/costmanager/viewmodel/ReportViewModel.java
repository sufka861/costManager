package il.ac.shenkar.java.costmanager.viewmodel;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.GetCostReportUseCase;

import java.util.Date;
import java.util.List;

public class ReportViewModel {
    private GetCostReportUseCase getCostReportUseCase;

    public ReportViewModel(GetCostReportUseCase getCostReportUseCase)
    {
        this.getCostReportUseCase = getCostReportUseCase;
    }

    public List<Cost> getCostsByDate(Date date)
    {
        return getCostReportUseCase.getCostsByDate(date);
    }
}
