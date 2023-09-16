package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.implementations.GetCostReportUseCaseImpl;
import il.ac.shenkar.java.costmanager.domain.util.DateLabelFormatter;
import il.ac.shenkar.java.costmanager.viewmodel.ReportViewModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * The `ReportDialog` class represents the dialog for generating a cost report.
 */
public class ReportDialog extends JDialog {
    private final ReportViewModel reportViewModel = new ReportViewModel(new GetCostReportUseCaseImpl());
    private JDatePicker datePicker;
    private JTable reportTable;

    /**
     * Constructs a new `ReportDialog`.
     *
     * @param owner           The owner frame.
     * @param reportViewModel The report view model.
     * @throws SQLException If there's an issue with SQL.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public ReportDialog(Frame owner, ReportViewModel reportViewModel) throws SQLException, IOException {
        super(owner, "Cost Report", true);
        setSize(800, 600);
        setDatePicker(createDatePicker());
        initComponents();
    }

    /**
     * Gets the date picker component.
     *
     * @return The date picker component.
     */
    public JDatePicker getDatePicker() {
        return datePicker;
    }

    /**
     * Sets the date picker component.
     *
     * @param datePicker The date picker component to set.
     */
    public void setDatePicker(JDatePicker datePicker) {
        this.datePicker = datePicker;
    }

    /**
     * Creates and configures the date picker component.
     *
     * @return The configured date picker component.
     */
    private JDatePicker createDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
        JDatePicker datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        model.setSelected(true);
        return datePicker;
    }

    /**
     * Initializes and configures the components of the dialog.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title Label
        JLabel titleLabel = new JLabel("Generate Cost Report");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Date Label
        JLabel dateLabel = new JLabel("Select Date:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(dateLabel, gbc);

        // Date Picker
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add((JComponent) getDatePicker(), gbc);

        // Generate Button
        JButton generateButton = createButton("Generate Report", this::generateAction);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(generateButton, gbc);

        // Cancel Button
        JButton cancelButton = createButton("Cancel", this::cancelAction);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(cancelButton, gbc);

        getContentPane().add(panel);
    }

    /**
     * Creates a button with the specified label and ActionListener.
     *
     * @param label    The label of the button.
     * @param listener The ActionListener for the button.
     * @return The configured button.
     */
    private JButton createButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        return button;
    }

    /**
     * Action to generate the cost report based on the selected date.
     *
     * @param e The ActionEvent.
     */
    private void generateAction(ActionEvent e) {
        Date date = (Date) getDatePicker().getModel().getValue();

        if (date == null) {
            showMessage("Please select a valid date.");
            return;
        }

        List<Cost> reportData = reportViewModel.getCostsByDate(date);

        if (reportData.isEmpty()) {
            showMessage("No data found for the specified date.");
            return;
        }

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Category");
        tableModel.addColumn("Sum");
        tableModel.addColumn("Currency");
        tableModel.addColumn("Description");
        tableModel.addColumn("Date");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Cost cost : reportData) {
            Object[] rowData = {
                    cost.getCategory().getName(),
                    cost.getSum(),
                    cost.getCurrency(),
                    cost.getDescription(),
                    dateFormat.format(cost.getDate())
            };
            tableModel.addRow(rowData);
        }

        reportTable = new JTable(tableModel);
        reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(reportTable);

        JOptionPane.showMessageDialog(this, scrollPane, "Report", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Action to cancel the operation and close the dialog.
     *
     * @param e The ActionEvent.
     */
    private void cancelAction(ActionEvent e) {
        dispose();
    }

    /**
     * Displays a validation error message dialog with the specified message.
     *
     * @param message The error message to display.
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}
