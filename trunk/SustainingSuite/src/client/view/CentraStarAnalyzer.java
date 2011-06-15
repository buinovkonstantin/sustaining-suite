package client.view;


import javax.swing.*;

import client.view.menu.AnalyzeMenu;
import client.view.menu.ClusterMenu;
import client.view.menu.HelpMenu;
import client.view.menu.LoggingMenu;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

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
        JMenuBar menuBar = new JMenuBar();
        ClusterMenu clusterMenu = new ClusterMenu();
        LoggingMenu loggingMenu = new LoggingMenu();
        AnalyzeMenu analyzeMenu = new AnalyzeMenu();
        HelpMenu helpMenu = new HelpMenu();
        menuBar.add(clusterMenu);
        menuBar.add(loggingMenu);
        menuBar.add(analyzeMenu);
        menuBar.add(helpMenu);
        menuBar.add(new JMenu("Windows"));
        setJMenuBar(menuBar);
        
        desktop = new JDesktopPane();
        desktop.setDragMode(JDesktopPane.DRAG_LAYER);
        JLabel statusBarLabel = new JLabel("Connected as admin to Lab_cluster4 [192.168.4.3]");
        
        FormLayout formLayout = new FormLayout("100px:grow(1)", "100px:grow(1), 20px");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.add(desktop, c.xy(1, 1, CC.FILL, CC.FILL));
        panelBuilder.add(statusBarLabel, c.xy(1, 2));
        setContentPane(panelBuilder.getPanel());
        
        pack();
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        link = this;
    }

    public static void main(String[] args) {
        new CentraStarAnalyzer();
    }
    
    public Container getDesktop() {
    	return desktop;
    }
}
