package il.ac.shenkar.java.costmanager.ui;

import il.ac.shenkar.java.costmanager.domain.usecase.interfaces.AddCategoryUseCase;
import il.ac.shenkar.java.costmanager.viewmodel.CategoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        addCostButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    new AddCostDialog(MainFrame.this, null).setVisible(true);
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addCategoryButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    new AddCategoryDialog(MainFrame.this, null).setVisible(true);
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        viewReportButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new ReportDialog(MainFrame.this, null).setVisible(true);
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
