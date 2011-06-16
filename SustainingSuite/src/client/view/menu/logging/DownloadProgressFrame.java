package client.view.menu.logging;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

public class DownloadProgressFrame extends JInternalFrame {
    JTable downloadTable;
    private static Object[][] exampleOfData = new String[][]{
            {"c001n05", "/var/log/messages", "58%", "Downloading"},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
    };
    private static String[] columnNames = {"Node", "File", "Progress", "Status"};
    public DownloadProgressFrame() {
        super("Download progress", true, true, true, true);
        downloadTable = new JTable(exampleOfData, columnNames);
        FormLayout formLayout = new FormLayout("100dlu:grow(1)", "5dlu, 60dlu:grow(1)");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.add(downloadTable.getTableHeader(), c.xy(1, 1));
        panelBuilder.add(new JScrollPane(downloadTable), c.xy(1, 2));
        add(panelBuilder.getPanel());
        pack();
        setVisible(true);
    }
}
