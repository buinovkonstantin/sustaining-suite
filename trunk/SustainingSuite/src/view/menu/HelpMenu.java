package view.menu;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/27/11
 * Time: 2:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class HelpMenu extends JMenu {
    public HelpMenu() {
        super("Help");

        // User's Manual Menu item
        add(new JMenuItem("User's Manual"));

        // About Menu item
        add(new JMenuItem("About"));
    }
}
