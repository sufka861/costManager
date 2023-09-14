package il.ac.shenkar.java.costmanager.domain.usecase.implementations;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.repository.implementations.CostRepositoryImpl;
import il.ac.shenkar.java.costmanager.domain.repository.interfaces.CostRepository;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.GetCostReportUseCase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class GetCostReportUseCaseImpl implements GetCostReportUseCase {

    private final CostRepository costRepository = new CostRepositoryImpl();

    public GetCostReportUseCaseImpl() throws SQLException, IOException {}

    @Override
    public List<Cost> getCostsByDate(Date date) {
        List<Cost> result = costRepository.getCosts(date);
        return result;
    }
}
