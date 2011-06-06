package view.menu.logging.dialog;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomLoggingFrame extends JInternalFrame {
    JLabel logsLabel;
    JTable logsTable;
    JButton startButton;
    JButton stopButton;

    private static String[][] exampleOfData = new String[][]{
            {"Debug", "ClusterComponent", "c001n05", "01.02.2011 09.37.21"},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
    };
    private static String[] columnNames = {"Level", "Component", "Nodes", "Start date/time"};

    public CustomLoggingFrame() {
        super("Custom logging", true, true, true, true);
        logsLabel = new JLabel("Started logs");
        logsTable = new JTable(exampleOfData, columnNames);
        startButton = new JButton("Start new log...");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateCustomLogDialog();
            }
        });
        stopButton = new JButton("Stop log");
        stopButton.setEnabled(false);
        logsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        logsTable.getSelectionModel().addListSelectionListener(new TableListener(logsTable, stopButton));

        FormLayout formLayout = new FormLayout("50dlu:grow(0.5), 50dlu:grow(0.5)", "20dlu, 5dlu, 60dlu:grow(1), 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.add(logsLabel, c.xyw(1, 1, 2));
        panelBuilder.add(new JScrollPane(logsTable), c.xyw(1, 3, 2));
        panelBuilder.add(startButton, c.xy(1, 4, CC.LEFT, CC.CENTER));
        panelBuilder.add(stopButton, c.xy(2, 4, CC.RIGHT, CC.CENTER));
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
