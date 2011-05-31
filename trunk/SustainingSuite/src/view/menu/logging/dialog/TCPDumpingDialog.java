package view.menu.logging.dialog;

import view.CentraStarAnalyzer;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 8:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class TCPDumpingDialog extends JDialog {
    public TCPDumpingDialog() {
        super(CentraStarAnalyzer.link, "TCP dumping", false);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
