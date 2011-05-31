package view.menu.analyze;

import view.menu.analyze.dialog.Base64Dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Base64Menu extends JMenuItem {
    public Base64Menu() {
        super("Base64 enc/dec");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Base64Dialog();
            }
        });
    }
}
