package view.menu.logging;

import view.menu.logging.dialog.DownloadDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadMenu extends JMenuItem {
    public DownloadMenu() {
        super("Download");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DownloadDialog();
            }
        });
    }
}
