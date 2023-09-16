package il.ac.shenkar.java.costmanager.viewmodel;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.GetCostReportUseCase;

import java.util.Date;
import java.util.List;

/**
 * The `ReportViewModel` class represents the view model for generating cost reports.
 */
public class ReportViewModel {

    private GetCostReportUseCase getCostReportUseCase;

    /**
     * Constructs a new `ReportViewModel`.
     *
     * @param getCostReportUseCase The use case for getting cost reports.
     */
    public ReportViewModel(GetCostReportUseCase getCostReportUseCase)
    {
        setGetCostReportUseCase(getCostReportUseCase);
    }

    /**
     * Gets the use case for getting cost reports.
     *
     * @return The use case for getting cost reports.
     */
    public GetCostReportUseCase getGetCostReportUseCase() {
        return getCostReportUseCase;
    }

    /**
     * Sets the use case for getting cost reports.
     *
     * @param getCostReportUseCase The use case for getting cost reports to set.
     */
    public void setGetCostReportUseCase(GetCostReportUseCase getCostReportUseCase) {
        this.getCostReportUseCase = getCostReportUseCase;
    }

    /**
     * Gets a list of costs for a specific date.
     *
     * @param date The date for which to retrieve costs.
     * @return A list of costs for the specified date.
     */
    public List<Cost> getCostsByDate(Date date)
    {
        return getGetCostReportUseCase().getCostsByDate(date);
    }
}
