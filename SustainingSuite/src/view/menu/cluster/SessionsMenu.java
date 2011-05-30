package view.menu.cluster;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class SessionsMenu extends JMenuItem {
    public SessionsMenu() {
        super("Sessions");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SessionsDialog().setVisible(true);
            }
        });
    }
}
