package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.menu.cluster.dialog.ExitDialog;
import view.menu.cluster.dialog.SessionsDialog;

public class ClusterMenu extends JMenu {
    public ClusterMenu() {
        super("Cluster");
        
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
                new ExitDialog();
            }
        });
        add(exitMenuItem);
    }
}
