package il.ac.shenkar.java.costmanager.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        // Set the title and initial size of the main frame
        setTitle("Cost Manager App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create a title label
        JLabel titleLabel = new JLabel("Cost Manager App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(titleLabel);

        // Create spacing between the title and buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create Add Cost button
        JButton addCostButton = createButton("Add Cost");
        addCostButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddCostDialog();
            }
        });
        buttonPanel.add(addCostButton);

        // Create spacing between Add Cost and Add Category buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create Add Category button
        JButton addCategoryButton = createButton("Add Category");
        addCategoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddCategoryDialog();
            }
        });
        buttonPanel.add(addCategoryButton);

        // Create spacing between Add Category and View Report buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create View Report button
        JButton viewReportButton = createButton("View Report");
        viewReportButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openViewReportDialog();
            }
        });
        buttonPanel.add(viewReportButton);

        getContentPane().add(buttonPanel);
        centerFrame(); // Center the frame on the screen
    }

    /**
     * Create a button with the specified label.
     *
     * @param label The label text for the button.
     * @return The created JButton.
     */
    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }

    /**
     * Center the frame on the screen.
     */
    private void centerFrame() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        setLocation(x, y);
    }

    /**
     * Open the "Add Cost" dialog.
     */
    private void openAddCostDialog() {
        try {
            new AddCostDialog(this, null).setVisible(true);
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Open the "Add Category" dialog.
     */
    private void openAddCategoryDialog() {
        try {
            new AddCategoryDialog(this, null).setVisible(true);
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Open the "View Report" dialog.
     */
    private void openViewReportDialog() {
        try {
            new ReportDialog(this, null).setVisible(true);
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
