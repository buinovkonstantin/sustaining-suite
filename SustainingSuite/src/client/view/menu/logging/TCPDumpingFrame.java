package client.view.menu.logging;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class TCPDumpingFrame extends JInternalFrame {
    JLabel tcpDumpLabel;
    JTable tcpDumpsTable;
    JButton startButton;
    JButton stopButton;

    private static Object[][] exampleOfData = new String[][]{
            {"c001n05", "eth0", "25.05.2011 17:35:41"},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
    };
    private static String[] columnNames = {"Nodes", "NIC", "Start date/time"};
    public TCPDumpingFrame() {
        super("TCPDump capturing", true, true, true, true);
        tcpDumpLabel = new JLabel("Started TCP dumps");
        tcpDumpsTable = new JTable(exampleOfData, columnNames);
        startButton = new JButton("Start new capture...");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateTCPDumpingDialog();
            }
        });
        stopButton = new JButton("Stop capture");
        FormLayout formLayout = new FormLayout("50dlu:grow(0.5), 50dlu:grow(0.5)", "20dlu, 5dlu, 60dlu:grow(1), 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.add(tcpDumpLabel, c.xyw(1, 1, 2));
        panelBuilder.add(new JScrollPane(tcpDumpsTable), c.xyw(1, 3, 2));
        panelBuilder.add(startButton, c.xy(1, 4, CC.LEFT, CC.CENTER));
        panelBuilder.add(stopButton, c.xy(2, 4, CC.RIGHT, CC.CENTER));
        add(panelBuilder.getPanel());
        pack();
        setVisible(true);
    }
}
