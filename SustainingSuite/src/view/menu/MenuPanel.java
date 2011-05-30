package view.menu;

import org.omg.CORBA.INTERNAL;
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
 * Time: 2:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuPanel extends JPanel {
    JMenuBar menuBar = new JMenuBar();
    ClusterMenu clusterMenu = new ClusterMenu();
    LoggingMenu loggingMenu = new LoggingMenu();
    AnalyzeMenu analyzeMenu = new AnalyzeMenu();
    HelpMenu helpMenu = new HelpMenu();

    public MenuPanel() {
        super();
        //setMaximumSize(new Dimension(400, 30));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(menuBar);
        menuBar.add(clusterMenu);
        menuBar.add(loggingMenu);
        menuBar.add(analyzeMenu);
        menuBar.add(helpMenu);
        menuBar.add(Box.createHorizontalGlue());
    }
}
