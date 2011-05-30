package view.menu.help;

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
        add(new UsersManualMenu());
        add(new AboutMenu());
    }
}
