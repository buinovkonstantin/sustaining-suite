package client.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import client.view.CentraStarAnalyzer;
import client.view.menu.logging.CustomLoggingFrame;
import client.view.menu.logging.DownloadDateDialog;
import client.view.menu.logging.SearchInLogsDialog;
import client.view.menu.logging.TCPDumpingFrame;


/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/27/11
 * Time: 2:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoggingMenu extends JMenu {
    public LoggingMenu() {
        super("Logging");
        
        // Search in Logs Menu item
        JMenuItem searchMenuItem = new JMenuItem("Search in Logs...");
        searchMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchInLogsDialog();
            }
        });
        add(searchMenuItem);
        
        // Search in Logs Menu item
        JMenuItem customMenuItem = new JMenuItem("Custom Logging...");
        customMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CentraStarAnalyzer.link.getDesktop().add(new CustomLoggingFrame());
            }
        });
        add(customMenuItem);
        
        // TCPDumping in Logs Menu item
        JMenuItem tcpdumpingMenuItem = new JMenuItem("TCP dumping...");
        tcpdumpingMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CentraStarAnalyzer.link.getDesktop().add(new TCPDumpingFrame());
            }
        });
        add(tcpdumpingMenuItem);
        
        // Download Menu item
        JMenuItem downloadMenuItem = new JMenuItem("Download...");
        downloadMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DownloadDateDialog();
            }
        });
        add(downloadMenuItem);    }
}
