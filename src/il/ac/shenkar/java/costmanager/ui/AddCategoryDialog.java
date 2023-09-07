package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.viewmodel.CategoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryDialog extends JDialog {
    private final CategoryViewModel categoryViewModel;
    private JTextField nameField;

    public AddCategoryDialog(Frame owner, CategoryViewModel categoryViewModel) {
        super(owner, "Add Category", true);
        this.categoryViewModel = categoryViewModel;
        setSize(300, 200);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        nameField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        JButton saveButton = createButton("Save", this::saveAction);
        JButton cancelButton = createButton("Cancel", this::cancelAction);

        panel.add(saveButton);
        panel.add(cancelButton);

        getContentPane().add(panel);
    }

    private JButton createButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        return button;
    }

    private void saveAction(ActionEvent e) {
        String categoryName = nameField.getText().trim();

        if (categoryName.isEmpty()) {
            showMessage("Category name cannot be empty.");
            return;
        }

        if (categoryViewModel.categoryExists(categoryName)) {
            showMessage("Category with the same name already exists.");
            return;
        }

        categoryViewModel.addCategory(categoryName);
        dispose();
    }

    private void cancelAction(ActionEvent e) {
        dispose();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            CategoryViewModel categoryViewModel = new CategoryViewModel(null);
            AddCategoryDialog addCategoryDialog = new AddCategoryDialog(frame, categoryViewModel);
            addCategoryDialog.setVisible(true);
        });
    }
}
