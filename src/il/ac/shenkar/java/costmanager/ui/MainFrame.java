package il.ac.shenkar.java.costmanager.ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The `MainFrame` class represents the main frame of the Cost Manager application.
 */
public class MainFrame extends JFrame {

    /**
     * Constructs a new `MainFrame`.
     */
    public MainFrame() {
        setTitle("Cost Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addCostButton = new JButton("Add Cost");
        JButton addCategoryButton = new JButton("Add Category");
        JButton viewReportButton = new JButton("View Report");

        // ActionListener for the "Add Cost" button
        addCostButton.addActionListener(e -> {
            try {
                // Open the "Add Cost" dialog
                new AddCostDialog(MainFrame.this, null).setVisible(true);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // ActionListener for the "Add Category" button
        addCategoryButton.addActionListener(e -> {
            try {
                // Open the "Add Category" dialog
                new AddCategoryDialog(MainFrame.this, null).setVisible(true);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // ActionListener for the "View Report" button
        viewReportButton.addActionListener(e -> {
            try {
                // Open the "View Report" dialog
                new ReportDialog(MainFrame.this, null).setVisible(true);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(addCostButton);
        panel.add(addCategoryButton);
        panel.add(viewReportButton);

        getContentPane().add(panel);
    }
}
