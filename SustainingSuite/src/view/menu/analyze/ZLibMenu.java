package view.menu.analyze;

import view.menu.analyze.dialog.ZLibCodingDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ZLibMenu extends JMenuItem {
    public ZLibMenu() {
        super("ZLib enc/dec");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ZLibCodingDialog();
            }
        });
    }
}
