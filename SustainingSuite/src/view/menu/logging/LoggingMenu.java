package view.menu.logging;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/27/11
 * Time: 2:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoggingMenu extends JMenu {
    public LoggingMenu() {
        super("Logging");
        add(new SearchInLogsMenu());
        add(new CustomLoggingMenu());
        add(new TCPDumpingMenu());
        add(new DownloadMenu());
    }
}
