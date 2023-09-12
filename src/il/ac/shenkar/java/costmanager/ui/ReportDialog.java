package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.domain.usecase.implementations.GetCostReportUseCaseImpl;
import il.ac.shenkar.java.costmanager.domain.util.DateLabelFormatter;
import il.ac.shenkar.java.costmanager.viewmodel.ReportViewModel;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ReportDialog extends JDialog {
    private final ReportViewModel reportViewModel = new ReportViewModel(new GetCostReportUseCaseImpl());
    private JDatePicker datePicker;

    public ReportDialog(Frame owner, ReportViewModel reportViewModel) throws SQLException, IOException {
        super(owner, "Cost Report", true);
        setSize(600, 400);
        datePicker = createDatePicker();
        initComponents();
    }

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




    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Date:"));
        panel.add((JComponent) datePicker);

        JButton generateButton = createButton("Generate Report", this::generateAction);
        JButton cancelButton = createButton("Cancel", this::cancelAction);

        panel.add(generateButton);
        panel.add(cancelButton);

        getContentPane().add(panel);
    }

    private JButton createButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        return button;
    }

    private void generateAction(ActionEvent e) {
        Date date = (Date) datePicker.getModel().getValue();

        if (date == null) {
            showMessage("Please select a valid date.");
            return;
        }

        List<Cost> reportData = reportViewModel.getCostsByDate(date);

        if (reportData.isEmpty()) {
            showMessage("No data found for the specified date.");
            return;
        }

        JTextArea reportTextArea = new JTextArea();
        for (Cost cost : reportData) {
            reportTextArea.append(cost.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Report", JOptionPane.INFORMATION_MESSAGE);
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
            ReportViewModel reportViewModel = new ReportViewModel(null);
            ReportDialog reportDialog = null;
            try {
                reportDialog = new ReportDialog(frame, reportViewModel);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            reportDialog.setVisible(true);
   });
}
}