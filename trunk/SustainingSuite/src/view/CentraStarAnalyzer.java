package view;

import view.menu.analyze.AnalyzeMenu;
import view.menu.cluster.ClusterMenu;
import view.menu.help.HelpMenu;
import view.menu.logging.LoggingMenu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/27/11
 * Time: 2:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class CentraStarAnalyzer extends JFrame {
    public static CentraStarAnalyzer link;
    private JDesktopPane desktop;
    public CentraStarAnalyzer() throws HeadlessException {
        super("CentraStarAnalyzer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 300));
        setSize(600, 400);
        JMenuBar menuBar = new JMenuBar();
        ClusterMenu clusterMenu = new ClusterMenu();
        LoggingMenu loggingMenu = new LoggingMenu();
        AnalyzeMenu analyzeMenu = new AnalyzeMenu();
        HelpMenu helpMenu = new HelpMenu();
        menuBar.add(clusterMenu);
        menuBar.add(loggingMenu);
        menuBar.add(analyzeMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        desktop = new JDesktopPane();
        desktop.setDragMode(JDesktopPane.DRAG_LAYER);
        setContentPane(desktop);
        setLocationRelativeTo(null);
        setVisible(true);
        link = this;
    }

    public static void main(String[] args) {
        new CentraStarAnalyzer();
    }
}
