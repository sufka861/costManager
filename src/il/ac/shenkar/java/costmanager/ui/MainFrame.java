package il.ac.shenkar.java.costmanager.ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    public MainFrame()
    {
        setTitle("Cost Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addCostButton = new JButton("Add Cost");
        JButton addCategoryButton = new JButton("Add Category");
        JButton viewReportButton = new JButton("View Report");

        addCostButton.addActionListener(e -> {
            try {
                new AddCostDialog(MainFrame.this, null).setVisible(true);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addCategoryButton.addActionListener(e -> {
            try {
                new AddCategoryDialog(MainFrame.this, null).setVisible(true);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        viewReportButton.addActionListener(e -> {
            try {
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
