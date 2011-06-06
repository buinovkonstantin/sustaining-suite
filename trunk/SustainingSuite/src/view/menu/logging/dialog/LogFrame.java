package view.menu.logging.dialog;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 6/6/11
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogFrame extends JInternalFrame {
    JTable logTable;
    private static Object[][] exampleOfData = new String[][]{
            {"2009.10.05 23:20:46.082", "c001n09", "WARNING NoCapacity"},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
    };
    private static String[] columnNames = {"Time", "Node", "Message"};
    public LogFrame() {
        super("Log", true, true, true, true);
        logTable = new JTable(exampleOfData, columnNames);
        FormLayout formLayout = new FormLayout("100dlu:grow(1)", "60dlu:grow(1)");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.add(new JScrollPane(logTable), c.xy(1, 1));
        add(panelBuilder.getPanel());
        pack();
        setVisible(true);
    }
}
