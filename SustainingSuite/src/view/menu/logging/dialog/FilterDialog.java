package view.menu.logging.dialog;

import view.CentraStarAnalyzer;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class FilterDialog extends JDialog {
    public FilterDialog() {
        super(CentraStarAnalyzer.link, " Filter", true);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
