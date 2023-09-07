package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.domain.model.Category;
import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.viewmodel.CostViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddCostDialog extends JDialog {
    private final CostViewModel costViewModel;
    private final JTextField categoryField;
    private final JTextField sumField;
    private final JTextField currencyField;
    private final JTextField descriptionField;

    public AddCostDialog(Frame owner, CostViewModel costViewModel) {
        super(owner, "Add Cost", true);
        this.costViewModel = costViewModel;
        setSize(400, 300);

        categoryField = new JTextField();
        sumField = new JTextField();
        currencyField = new JTextField();
        descriptionField = new JTextField();

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        addLabelAndField(panel, "Category:", categoryField);
        addLabelAndField(panel, "Sum:", sumField);
        addLabelAndField(panel, "Currency:", currencyField);
        addLabelAndField(panel, "Description:", descriptionField);

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

    private JButton createButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        return button;
    }

    private void saveAction(ActionEvent e) {
        String categoryText = categoryField.getText().trim();
        String sumText = sumField.getText().trim();
        String currency = currencyField.getText().trim();
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
            CostViewModel costViewModel = new CostViewModel();
            AddCostDialog addCostDialog = new AddCostDialog(frame, costViewModel);
            addCostDialog.setVisible(true);
        });
    }
}

