package client.view.menu.logging.dialog;


import javax.swing.*;

import client.view.CentraStarAnalyzer;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 7:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileSelectionDialog extends JDialog {
    public FileSelectionDialog() {
        super(CentraStarAnalyzer.link, "Select files...", true);

        setMinimumSize(new Dimension(100, 100));
        add(new JPanel());

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
