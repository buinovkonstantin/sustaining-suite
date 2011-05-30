package view.menu.cluster;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/27/11
 * Time: 2:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClusterMenu extends JMenu {
    public ClusterMenu() {
        super("Cluster");
        add(new ReconnectMenu());
        add(new DisconnectMenu());
        add(new SessionsMenu());
        add(new ExitMenu());
    }
}
