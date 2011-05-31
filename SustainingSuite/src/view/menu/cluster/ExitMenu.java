package view.menu.cluster;

import view.menu.cluster.dialog.ExitDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExitMenu extends JMenuItem {

    public ExitMenu() {
        super("Exit");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExitDialog exitDialog = new ExitDialog();
                exitDialog.setVisible(true);

                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
