package il.ac.shenkar.java.costmanager.domain.usecase.implementations;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.repository.implementations.CostRepositoryImpl;
import il.ac.shenkar.java.costmanager.domain.repository.interfaces.CostRepository;
import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCostUseCase;

import java.io.IOException;
import java.sql.SQLException;

public class AddCostUseCaseImpl implements AddCostUseCase {
    private final CostRepository costRepository = new CostRepositoryImpl();

    public AddCostUseCaseImpl() throws SQLException, IOException {}

    @Override
    public void addCost(Cost cost) {
        costRepository.addCost(cost);
    }
}