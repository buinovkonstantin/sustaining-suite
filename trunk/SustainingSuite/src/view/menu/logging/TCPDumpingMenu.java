package view.menu.logging;

import view.CentraStarAnalyzer;
import view.menu.logging.dialog.CreateTCPDumpingDialog;
import view.menu.logging.dialog.TCPDumpingFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class TCPDumpingMenu extends JMenuItem {
    public TCPDumpingMenu() {
        super("TCP Dumping");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CentraStarAnalyzer.link.add(new TCPDumpingFrame());
            }
        });
    }
}
