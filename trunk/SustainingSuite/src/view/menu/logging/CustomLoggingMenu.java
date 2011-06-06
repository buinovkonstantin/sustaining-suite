package view.menu.logging;

import view.CentraStarAnalyzer;
import view.menu.logging.dialog.CustomLoggingFrame;

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
public class CustomLoggingMenu extends JMenuItem {
    public CustomLoggingMenu() {
        super("Custom Logging");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CentraStarAnalyzer.link.add(new CustomLoggingFrame());
            }
        });
    }
}
