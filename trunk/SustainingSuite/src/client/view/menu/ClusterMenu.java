package client.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import common.controller.ModulesController;

import client.view.menu.cluster.ExitDialog;
import client.view.menu.cluster.SessionsDialog;


public class ClusterMenu extends JMenu {
	
    private ModulesController controller;

	public ClusterMenu(ModulesController controller) {
        super("Cluster");
        
        this.controller = controller;
        
        // Reconnect Menu item
        add(new JMenuItem("Reconnect"));
        
        // Sessions Menu item
        JMenuItem sessionsMenuItem = new JMenuItem("Sessions");
        sessionsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SessionsDialog();
            }
        });
        add(sessionsMenuItem);
        
        // Exit Menu item
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ExitDialog(ClusterMenu.this.controller);
            }
        });
        add(exitMenuItem);
    }
}
