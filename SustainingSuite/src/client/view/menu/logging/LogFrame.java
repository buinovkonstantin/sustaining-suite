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
            {"2010.08.25 16:53:29.427 UTC", "c001n01", "FragmentBlobWriterServerSession: NoCapacity [205992]"},
            {"2010.08.24 14:39:35.730 UTC", "c001n02", "[RETRY] retry (5) writeblob because Exception"},
            {"2010.08.24 13:04:20.478 UTC", "c001n02", "[RETRY] retry (0) writeblob because Exception"},
            {"2010.08.24 00:53:57.004 UTC", "c001n02", "SDK error: errline(1088), errorcode(-10005),systemError(13), errorString(FP_SERVER_ERR), message(WriterException: NoCapacity [1078614]"},
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
