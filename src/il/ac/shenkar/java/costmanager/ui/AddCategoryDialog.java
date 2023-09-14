package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.domain.usecase.implementations.AddCategoryUseCaseImpl;
import il.ac.shenkar.java.costmanager.viewmodel.CategoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class AddCategoryDialog extends JDialog {
    private final CategoryViewModel categoryViewModel = new CategoryViewModel(new AddCategoryUseCaseImpl());
    private JTextField nameField;

    public AddCategoryDialog(Frame owner, CategoryViewModel categoryViewModel) throws SQLException, IOException {
        super(owner, "Add Category", true);
        setSize(300, 200);

        initUI();
    }

    public CategoryViewModel getCategoryViewModel() {
        return categoryViewModel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        setNameField(new JTextField());

        panel.add(new JLabel("Name:"));
        panel.add(getNameField());

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
        String categoryName = getNameField().getText().trim();

        if (categoryName.isEmpty()) {
            showMessage("Category name cannot be empty.");
            return;
        }

        if (getCategoryViewModel().categoryExists(categoryName)) {
            showMessage("Category with the same name already exists.");
            return;
        }

        getCategoryViewModel().addCategory(categoryName);
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
            CategoryViewModel categoryViewModel = null;
            try {
                categoryViewModel = new CategoryViewModel(new AddCategoryUseCaseImpl());
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            AddCategoryDialog addCategoryDialog = null;
            try {
                addCategoryDialog = new AddCategoryDialog(frame, categoryViewModel);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            addCategoryDialog.setVisible(true);
        });
    }
}
