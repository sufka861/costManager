package il.ac.shenkar.java.costmanager.viewmodel;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.GetCostReportUseCase;

import java.util.Date;
import java.util.List;

public class ReportViewModel {

    private GetCostReportUseCase getCostReportUseCase;

    public ReportViewModel(GetCostReportUseCase getCostReportUseCase)
    {
        setGetCostReportUseCase(getCostReportUseCase);
    }

    public GetCostReportUseCase getGetCostReportUseCase() {
        return getCostReportUseCase;
    }

    public void setGetCostReportUseCase(GetCostReportUseCase getCostReportUseCase) {
        this.getCostReportUseCase = getCostReportUseCase;
    }

    public List<Cost> getCostsByDate(Date date)
    {
        return getGetCostReportUseCase().getCostsByDate(date);
    }
}
