package view.menu.analyze;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/27/11
 * Time: 2:53 AM
 * To change this template use File | Settings | File Templates.
 */

public class AnalyzeMenu extends JMenu {

    public AnalyzeMenu() {
        super("Analyze");
        add(new SmartPacketsMenu());
        add(new Base64Menu());
        add(new ZLibMenu());
    }
}
