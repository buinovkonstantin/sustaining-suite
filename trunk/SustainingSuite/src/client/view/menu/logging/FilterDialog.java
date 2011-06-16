package client.view.menu.logging;


import javax.swing.JDialog;

import client.view.MainFrame;

public class FilterDialog extends JDialog {
    public FilterDialog() {
        super(MainFrame.link, " Filter", true);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
