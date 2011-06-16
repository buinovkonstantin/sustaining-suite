package client.view.menu.logging;


import javax.swing.*;

import client.view.MainFrame;

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
        super(MainFrame.link, "Select files...", true);

        setMinimumSize(new Dimension(100, 100));
        add(new JPanel());

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
