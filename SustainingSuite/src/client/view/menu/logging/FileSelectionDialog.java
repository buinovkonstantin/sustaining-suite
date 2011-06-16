package client.view.menu.logging;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;

import client.view.MainFrame;

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
