package client.view.menu.logging;

import client.view.CentraStarAnalyzer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 6/1/11
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateTCPDumpingDialog extends JDialog {
//    JLabel nodesLabel;

//    JLabel networkLabel;
    JComboBox interfaceBox;
//    JLabel filterLabel;
    JTextField filterField;
    JButton createButton;
    JButton cancelButton;
    JTextField nodesField;
    JButton nodeSelectionDialog;

    public CreateTCPDumpingDialog() {
        super(CentraStarAnalyzer.link, "Create new TCP dumping", true);
        nodesField = new JTextField();
        nodeSelectionDialog = new JButton("Select nodes...");
        nodeSelectionDialog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NodeSelectionDialog();
            }
        });
        interfaceBox = new JComboBox(new String[]{"eth0", "eth1", "eth2"});
        filterField = new JTextField();
        createButton = new JButton("Create");
        cancelButton = new JButton("Cancel");
//        nodesLabel = new JLabel("Nodes");

        FormLayout formLayout = new FormLayout("center:60dlu, 10dlu, center:60dlu", "20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.setDefaultDialogBorder();
        panelBuilder.addSeparator("Nodes", c.xyw(1, 1, 3));
        panelBuilder.add(nodesField, CC.xy(1, 2, CC.FILL, CC.CENTER));
        panelBuilder.add(nodeSelectionDialog, c.xy(3, 2));
        panelBuilder.addSeparator("Network interface", c.xyw(1, 3, 3));
        panelBuilder.add(interfaceBox, c.xy(1, 4, CC.LEFT, CC.CENTER));
        panelBuilder.addSeparator("Pcap filter", c.xyw(1, 5, 3));
        panelBuilder.add(filterField, c.xyw(1, 6, 3));
        panelBuilder.add(createButton, c.xy(1, 7));
        panelBuilder.add(cancelButton, c.xy(3, 7));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
