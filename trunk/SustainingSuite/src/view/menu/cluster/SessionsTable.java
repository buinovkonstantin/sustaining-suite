package view.menu.cluster;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 4:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class SessionsTable extends JTable {

    private static Object[][] exampleOfData = new String[][]{
            {"c001n02", "yes", "10.05.2011 13:57:03 UTC", "35 Mb"},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
    };
    private static String[] columnNames = {"Server node", "Active", "Creation date", "Context size"};
    public SessionsTable() {
        //very stupid, only for example
        super(exampleOfData, columnNames);


    }
}
