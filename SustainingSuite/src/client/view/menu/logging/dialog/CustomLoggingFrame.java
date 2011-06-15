package client.view.menu.logging.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomLoggingFrame extends JInternalFrame {
    private JLabel logsLabel;
    private JTable logsTable;
    private JButton startButton;
    private JButton stopButton;
	private JLabel logFilterLabel;
	private JTextField logFilterField;

    private static String[][] exampleOfData = new String[][]{
            {"Debug", "ClusterComponent", "c001n05", "01.02.2011 09.37.21 UTC"},
            {"Debug", "PoolComponent", "c001n05", "01.02.2011 09.38.15 UTC"},
            {"Debug", "ReplicationComponent", "c001n05", "01.02.2011 09.42.56 UTC"},
            {"Debug", "ReplicationComponent", "c001n01, c001n02, c001n03, c001n04", "01.02.2011 10.04.41 UTC"}
    };
    private static String[] columnNames = {"Level", "Component", "Nodes", "Start date/time"};

    public CustomLoggingFrame() {
        super("Custom logging", true, true, true, true);
        
        logFilterLabel = new JLabel("Logging filter");
        logFilterField = new JTextField();
        logFilterField.setEditable(false);
        
        startButton = new JButton("Start new log...");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateCustomLogDialog();
            }
        });
        stopButton = new JButton("Stop log");
        stopButton.setEnabled(false);
        
        logsLabel = new JLabel("Started logs");
        logsTable = new JTable(exampleOfData, columnNames);
        logsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Listener MUST be initialized after Stop button creation
        // in other case NullPointerException will be thrown
        logsTable.getSelectionModel().addListSelectionListener(new TableListener(logsTable, stopButton));

        FormLayout formLayout = new FormLayout("15px, 140px, 10px, 140px, 200px:grow(1), 15px", "15px, 15px, 100px:grow(1), 10px, 15px, 20px, 15px, 20px, 15px");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.add(logsLabel, c.xyw(2, 2, 4));
        panelBuilder.add(new JScrollPane(logsTable), c.xyw(2, 3, 4));
        panelBuilder.add(logFilterLabel, c.xyw(2, 5, 4));
        panelBuilder.add(logFilterField, c.xyw(2, 6, 4));
        panelBuilder.add(startButton, c.xy(2, 8));
        panelBuilder.add(stopButton, c.xy(4, 8));
        add(panelBuilder.getPanel());
        pack();
        setVisible(true);
    }

    private class TableListener implements ListSelectionListener {

        private JTable logsTable;
        private JButton stopButton;

        private TableListener(JTable logsTable, final JButton stopButton) {
            this.logsTable = logsTable;
            this.stopButton = stopButton;
        }

        public void valueChanged(ListSelectionEvent e) {
            int row = logsTable.getSelectedRow();
            String s = exampleOfData[row][0];

            if(!s.equalsIgnoreCase("")) {
                this.stopButton.setEnabled(true);
            } else {
                this.stopButton.setEnabled(false);
            }
        }
    }
}
