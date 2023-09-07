package il.ac.shenkar.java.costmanager;

import il.ac.shenkar.java.costmanager.ui.MainFrame;

public class CostManagerApp {
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(() ->
        {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
