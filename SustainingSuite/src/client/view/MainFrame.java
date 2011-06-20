package client.view;


import client.controller.ConnectionController;
import client.view.menu.AnalyzeMenu;
import client.view.menu.ClusterMenu;
import client.view.menu.HelpMenu;
import client.view.menu.LoggingMenu;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import common.controller.Module;
import common.controller.ModuleException;
import common.controller.ModulesController;

import java.awt.*;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainFrame extends JFrame implements Module {
    public static MainFrame link;
    private JDesktopPane desktop;
    private ModulesController controller;
	private ConnectionController connectionController;
    
    public MainFrame(ModulesController controller, ConnectionController connectionController) throws HeadlessException {
        super("Centera Sustaining Suite");
        
    	this.controller = controller;
    	this.connectionController = connectionController;
    }

    public Container getDesktop() {
    	return desktop;
    }
    
	@Override
	public void start() throws ModuleException {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(300, 300));
        JMenuBar menuBar = new JMenuBar();
        ClusterMenu clusterMenu = new ClusterMenu(controller);
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
        
        new ConnectDialog(this, connectionController);
	}

	@Override
	public void stop() throws ModuleException {
		dispose();
	}
}
