package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.domain.usecase.implementations.AddCategoryUseCaseImpl;
import il.ac.shenkar.java.costmanager.viewmodel.CategoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The `AddCategoryDialog` class represents a dialog for adding a new category.
 */
public class AddCategoryDialog extends JDialog {
    private final CategoryViewModel categoryViewModel = new CategoryViewModel(new AddCategoryUseCaseImpl());
    private JTextField nameField;

    /**
     * Constructs a new `AddCategoryDialog` with the specified owner and view model.
     *
     * @param owner            The owner frame.
     * @param categoryViewModel The category view model.
     * @throws SQLException If there's an issue with SQL.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public AddCategoryDialog(Frame owner, CategoryViewModel categoryViewModel) throws SQLException, IOException {
        super(owner, "Add Category", true);
        setSize(300, 200);

        initUI();
    }

    /**
     * Gets the category view model.
     *
     * @return The category view model.
     */
    public CategoryViewModel getCategoryViewModel() {
        return categoryViewModel;
    }

    /**
     * Gets the name field.
     *
     * @return The name field.
     */
    public JTextField getNameField() {
        return nameField;
    }

    /**
     * Sets the name field.
     *
     * @param nameField The name field to set.
     */
    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    /**
     * Initializes the user interface components.
     */
    private void initUI() {
        // Creating the panel layout
        JPanel panel = new JPanel(new GridLayout(2, 2));
        setNameField(new JTextField());

        // Adding components to the panel
        panel.add(new JLabel("Name:"));
        panel.add(getNameField());

        JButton saveButton = createButton("Save", this::saveAction);
        JButton cancelButton = createButton("Cancel", this::cancelAction);

        panel.add(saveButton);
        panel.add(cancelButton);

        getContentPane().add(panel);
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

    /**
     * Handles the action when the "Cancel" button is clicked.
     *
     * @param e The ActionEvent.
     */
    private void cancelAction(ActionEvent e) {
        dispose();
    }

    /**
     * Displays an error message dialog with the specified message.
     *
     * @param message The error message to display.
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * The main method to launch the AddCategoryDialog.
     *
     * @param args The command-line arguments.
     */
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
