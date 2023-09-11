package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.domain.model.Category;
import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.implementations.AddCostUseCaseImpl;
import il.ac.shenkar.java.costmanager.viewmodel.CostViewModel;
import il.ac.shenkar.java.costmanager.domain.util.ConfigurationManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class AddCostDialog extends JDialog {
    private final CostViewModel costViewModel = new CostViewModel(new AddCostUseCaseImpl());

    private final JTextField categoryField;
    private final JTextField sumField;
    private final JTextField descriptionField;
    private JComboBox<String> currencyField;






    public AddCostDialog(Frame owner, CostViewModel costViewModel) throws SQLException, IOException {
        super(owner, "Add Cost", true);
        setSize(400, 300);

        categoryField = new JTextField();
        sumField = new JTextField();
        currencyField = new JComboBox<String>();
        descriptionField = new JTextField();

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        addLabelAndField(panel, "Category:", categoryField);
        addLabelAndField(panel, "Sum:", sumField);
        addLabelAndField(panel, "Description:", descriptionField);

        ConfigurationManager configurationManager = new ConfigurationManager();
        String[] currencies = configurationManager.getSupportedCurrencies();
        currencyField = new JComboBox<>(currencies);
        addLabelAndComboBox(panel, "Currency:", currencyField);

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
        String categoryText = categoryField.getText().trim();
        String sumText = sumField.getText().trim();
//        String currency = currencyField.getText().trim();
        String currency = (String) currencyField.getSelectedItem(); // Get the selected currency
        String description = descriptionField.getText().trim();

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

