package client.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import client.view.CentraStarAnalyzer;
import client.view.menu.analyze.dialog.Base64Dialog;
import client.view.menu.analyze.dialog.TextFrame;
import client.view.menu.analyze.dialog.ZLibCodingDialog;


/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/27/11
 * Time: 2:53 AM
 * To change this template use File | Settings | File Templates.
 */

public class AnalyzeMenu extends JMenu {

    public AnalyzeMenu() {
        super("Analyze");

        // Base64 Menu item
        JMenuItem base64MenuItem = new JMenuItem("Base64 encode/decode...");
        base64MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Base64Dialog();
            }
        });
        add(base64MenuItem);
        
        // Smart Packet Menu item
        JMenuItem smartpacketMenuItem = new JMenuItem("Smart Packet decode");
        smartpacketMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CentraStarAnalyzer.link.getDesktop().add(new TextFrame("Smart packet content"));
            }
        });
        add(smartpacketMenuItem);
        
        // ZLib Menu item
        JMenuItem zlibMenuItem = new JMenuItem("ZLib compress/decompress");
        zlibMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	int retVal = fileChooser.showOpenDialog(CentraStarAnalyzer.link);
            	if(retVal == JFileChooser.APPROVE_OPTION)
            		new ZLibCodingDialog();
            }
        });
        add(zlibMenuItem);
    }
}
