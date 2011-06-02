package view.menu.logging;

import view.menu.logging.dialog.SearchInLogsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class SearchInLogsMenu extends JMenuItem {
    public SearchInLogsMenu() {
        super("Search in Logs");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchInLogsDialog();
            }
        });
    }
}
