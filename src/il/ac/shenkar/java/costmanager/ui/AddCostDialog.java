package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.domain.model.Category;
import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.implementations.AddCostUseCaseImpl;
import il.ac.shenkar.java.costmanager.viewmodel.CostViewModel;
import il.ac.shenkar.java.costmanager.domain.util.ConfigurationManager;
import il.ac.shenkar.java.costmanager.domain.repository.implementations.CategoryRepositoryImpl;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AddCostDialog extends JDialog {
    private final CostViewModel costViewModel = new CostViewModel(new AddCostUseCaseImpl());
    private JComboBox<String> categoryField;
    private JTextField sumField;
    private JTextField descriptionField;
    private JComboBox<String> currencyField;


    public AddCostDialog(Frame owner, CostViewModel costViewModel) throws SQLException, IOException {
        super(owner, "Add Cost", true);
        setSize(400, 300);

        setCategoryField(new JComboBox<String>());
        setSumField(new JTextField());
        setCategoryField(new JComboBox<String>());
        setDescriptionField(new JTextField());

        initComponents();
    }

    public JComboBox<String> getCategoryField() {
        return categoryField;
    }

    public void setCategoryField(JComboBox<String> categoryField) {
        this.categoryField = categoryField;
    }

    public JTextField getSumField() {
        return sumField;
    }

    public void setSumField(JTextField sumField) {
        this.sumField = sumField;
    }

    public void setDescriptionField(JTextField descriptionField) {
        this.descriptionField = descriptionField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public JComboBox<String> getCurrencyField() {
        return currencyField;
    }

    public void setCurrencyField(JComboBox<String> currencyField) {
        this.currencyField = currencyField;
    }

    private void initComponents() throws SQLException, IOException {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        addLabelAndField(panel, "Sum:", getSumField());
        addLabelAndField(panel, "Description:", getDescriptionField());

        CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
        List<Category> categories = categoryRepository.getAllCategories();
        String[] categoryNames = new String[categories.size()];
        int index = 0;
        for(Category cat : categories){
            categoryNames[index++] = cat.getName();
        }
        setCategoryField(new JComboBox<>(categoryNames));
        addLabelAndComboBox(panel, "Category:", getCategoryField());

        ConfigurationManager configurationManager = new ConfigurationManager();
        String[] currencies = configurationManager.getSupportedCurrencies();
        setCurrencyField(new JComboBox<>(currencies));
        addLabelAndComboBox(panel, "Currency:", getCurrencyField());

        JButton saveButton = createButton("Save", this::saveAction);
        JButton cancelButton = createButton("Cancel", this::cancelAction);

        panel.add(saveButton);
        panel.add(cancelButton);

        getContentPane().add(panel);
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        panel.add(new JLabel(labelText));
        panel.add(textField);
    }

    private void addLabelAndComboBox(JPanel panel, String labelText, JComboBox<String> comboBox) {
        panel.add(new JLabel(labelText));
        panel.add(comboBox);
    }

    private JButton createButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        return button;
    }

    private void saveAction(ActionEvent e) {
        String categoryText = (String) getCategoryField().getSelectedItem();
        String sumText = getSumField().getText().trim();
        String currency = (String) getCurrencyField().getSelectedItem();
        String description = getDescriptionField().getText().trim();

        if (categoryText.isEmpty() || sumText.isEmpty()) {
            showMessage("Category and Sum fields are required.");
            return;
        }

        try {
            Category category = new Category(categoryText);
            double sum = Double.parseDouble(sumText);
            Date date = new Date();
            costViewModel.addCost(new Cost(category, sum, currency, description, date));
            dispose();
        } catch (NumberFormatException ex) {
            showMessage("Sum must be a valid number.");
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void cancelAction(ActionEvent e) {
        dispose();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            CostViewModel costViewModel = null;
            try {
                costViewModel = new CostViewModel(new AddCostUseCaseImpl());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AddCostDialog addCostDialog = null;
            try {
                addCostDialog = new AddCostDialog(frame, costViewModel);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            addCostDialog.setVisible(true);
        });
    }
}

