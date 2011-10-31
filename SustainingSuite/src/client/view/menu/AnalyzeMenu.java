package client.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import client.view.MainFrame;
import client.view.menu.analyze.Base64Dialog;
import client.view.menu.analyze.RootBlobIDTranslatorDialog;
import client.view.menu.analyze.TextFrame;
import client.view.menu.analyze.ZLibCodingDialog;

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
        
        // RootBlobID translator Menu item
        JMenuItem rootBlobIDTranslatorMenuItem = new JMenuItem("RootBlobID translation...");
        rootBlobIDTranslatorMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RootBlobIDTranslatorDialog();
            }
        });
        add(rootBlobIDTranslatorMenuItem);
        
        // Smart Packet Menu item
        JMenuItem smartpacketMenuItem = new JMenuItem("Smart Packet decode");
        smartpacketMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.link.getDesktop().add(new TextFrame("Smart packet content"));
            }
        });
        add(smartpacketMenuItem);
        
        // ZLib Menu item
        JMenuItem zlibMenuItem = new JMenuItem("ZLib compress/decompress");
        zlibMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	int retVal = fileChooser.showOpenDialog(MainFrame.link);
            	if(retVal == JFileChooser.APPROVE_OPTION)
            		new ZLibCodingDialog();
            }
        });
        add(zlibMenuItem);
    }
}
