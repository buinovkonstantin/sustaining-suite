package client.view.menu.logging;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class LogFrame extends JInternalFrame {
    private JTable logTable;
	private JTextField statusField;
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
        statusField = new JTextField();
        statusField.setEditable(false);
        FormLayout formLayout = new FormLayout("200dlu:grow(1)", "100dlu:grow(1), 20px");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.add(new JScrollPane(logTable), c.xy(1, 1));
        panelBuilder.add(statusField, c.xy(1, 2));
        add(panelBuilder.getPanel());
        pack();
        setVisible(true);
    }
}
