package view.menu.logging;

import view.menu.logging.dialog.ExtendLoggingDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExtendLoggingMenu extends JMenuItem {
    public ExtendLoggingMenu() {
        super("Extend Logging");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ExtendLoggingDialog();
            }
        });
    }
}
