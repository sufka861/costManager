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

/**
 * The `AddCostDialog` class represents a dialog for adding a new cost entry.
 */
public class AddCostDialog extends JDialog {
    private final CostViewModel costViewModel = new CostViewModel(new AddCostUseCaseImpl());
    private JComboBox<String> categoryField;
    private JTextField sumField;
    private JTextField descriptionField;
    private JComboBox<String> currencyField;

    /**
     * Constructs a new `AddCostDialog` with the specified owner and cost view model.
     *
     * @param owner         The owner frame.
     * @param costViewModel The cost view model.
     * @throws SQLException If there's an issue with SQL.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public AddCostDialog(Frame owner, CostViewModel costViewModel) throws SQLException, IOException {
        super(owner, "Add Cost", true);
        setSize(400, 300);

        setCategoryField(new JComboBox<String>());
        setSumField(new JTextField());
        setCategoryField(new JComboBox<String>());
        setDescriptionField(new JTextField());

        initComponents();
    }

    /**
     * Gets the category field.
     *
     * @return The category field.
     */
    public JComboBox<String> getCategoryField() {
        return categoryField;
    }

    /**
     * Sets the category field.
     *
     * @param categoryField The category field to set.
     */
    public void setCategoryField(JComboBox<String> categoryField) {
        this.categoryField = categoryField;
    }

    /**
     * Gets the sum field.
     *
     * @return The sum field.
     */
    public JTextField getSumField() {
        return sumField;
    }

    /**
     * Sets the sum field.
     *
     * @param sumField The sum field to set.
     */
    public void setSumField(JTextField sumField) {
        this.sumField = sumField;
    }

    /**
     * Gets the description field.
     *
     * @return The description field.
     */
    public JTextField getDescriptionField() {
        return descriptionField;
    }

    /**
     * Sets the description field.
     *
     * @param descriptionField The description field to set.
     */
    public void setDescriptionField(JTextField descriptionField) {
        this.descriptionField = descriptionField;
    }

    /**
     * Gets the currency field.
     *
     * @return The currency field.
     */
    public JComboBox<String> getCurrencyField() {
        return currencyField;
    }

    /**
     * Sets the currency field.
     *
     * @param currencyField The currency field to set.
     */
    public void setCurrencyField(JComboBox<String> currencyField) {
        this.currencyField = currencyField;
    }

    /**
     * Initializes the components of the dialog.
     */
    private void initComponents() throws SQLException, IOException {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        addLabelAndField(panel, "Sum:", getSumField());
        addLabelAndField(panel, "Description:", getDescriptionField());

        CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
        List<Category> categories = categoryRepository.getAllCategories();
        String[] categoryNames = new String[categories.size()];
        int index = 0;
        for (Category cat : categories) {
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

    /**
     * Adds a label and a text field to the panel.
     *
     * @param panel      The panel to add components to.
     * @param labelText  The label text.
     * @param textField  The text field.
     */
    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        panel.add(new JLabel(labelText));
        panel.add(textField);
    }

    /**
     * Adds a label and a combo box to the panel.
     *
     * @param panel       The panel to add components to.
     * @param labelText   The label text.
     * @param comboBox    The combo box.
     */
    private void addLabelAndComboBox(JPanel panel, String labelText, JComboBox<String> comboBox) {
        panel.add(new JLabel(labelText));
        panel.add(comboBox);
    }

    /**
     * Creates a JButton with the specified label and ActionListener.
     *
     * @param label    The label of the button.
     * @param listener The ActionListener for the button.
     * @return The created JButton.
     */
    private JButton createButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        return button;
    }

    /**
     * Handles the action when the "Save" button is clicked.
     *
     * @param e The ActionEvent.
     */
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

    /**
     * Handles the action when the "Cancel" button is clicked.
     *
     * @param e The ActionEvent.
     */
    private void cancelAction(ActionEvent e) {
        dispose();
    }

    /**
     * Displays a message dialog with the specified message.
     *
     * @param message The message to display.
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
