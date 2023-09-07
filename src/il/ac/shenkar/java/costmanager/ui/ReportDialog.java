package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.domain.model.Cost;
import il.ac.shenkar.java.costmanager.viewmodel.ReportViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReportDialog extends JDialog {
    private final ReportViewModel reportViewModel;
    private final JTextField dateField;

    public ReportDialog(Frame owner, ReportViewModel reportViewModel) {
        super(owner, "Cost Report", true);
        this.reportViewModel = reportViewModel;
        setSize(600, 400);
        dateField = new JTextField();

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Date:"));
        panel.add(dateField);

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
        String date = dateField.getText().trim();

        if (date.isEmpty()) {
            showMessage("Please enter a valid date.");
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
            ReportDialog reportDialog = new ReportDialog(frame, reportViewModel);
            reportDialog.setVisible(true);
        });
    }
}
