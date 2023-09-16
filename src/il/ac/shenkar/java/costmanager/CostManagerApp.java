package il.ac.shenkar.java.costmanager;

import il.ac.shenkar.java.costmanager.ui.MainFrame;

/**
 * The main application class for the Cost Manager program.
 * This class initializes the Swing user interface and starts the application.
 */
public class CostManagerApp {
    /**
     * The main method that starts the Cost Manager application.
     * It initializes the Swing user interface and sets the main frame as visible.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure that Swing components are created and updated on the Event Dispatch Thread.
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Create the main application frame
            MainFrame mainFrame = new MainFrame();

            // Set the main frame as visible
            mainFrame.setVisible(true);
        });
    }
}
