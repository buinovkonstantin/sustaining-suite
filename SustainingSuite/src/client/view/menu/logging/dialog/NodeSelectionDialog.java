package client.view.menu.logging.dialog;

import client.view.CentraStarAnalyzer;
import client.view.components.jlist.JCheckBoxListRenderer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class NodeSelectionDialog extends JDialog {
    JComboBox nodesSetBox;
    JList nodesList;
    JButton okButton;
    JButton cancelButton;

    public NodeSelectionDialog() {
        super(CentraStarAnalyzer.link, "Select nodes", true);
        nodesSetBox = new JComboBox(new String[] {"None", "Custom", "All"});
        nodesSetBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nodesSetBoxStateChanged();
            }
        });
        nodesList = new JList(new JCheckBox[] {
                new JCheckBox("c001n01"),
                new JCheckBox("c001n02"),
                new JCheckBox("c001n03"),
                new JCheckBox("c001n04"),
                new JCheckBox("c001n05"),
                new JCheckBox("c001n06"),
                new JCheckBox("c001n07"),
                new JCheckBox("c001n08")
        });
        nodesList.setCellRenderer(new JCheckBoxListRenderer());
        nodesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nodesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                int index = list.locationToIndex(event.getPoint());
                JCheckBox item = (JCheckBox)list.getModel().getElementAt(index);
                item.setSelected(!item.isSelected());
                nodeListStateChanged();
                list.repaint(list.getCellBounds(index, index));
            }
        });

        okButton = new JButton("Ok");
        cancelButton= new JButton("Cancel");

        PanelBuilder builder = new PanelBuilder(new FormLayout("60dlu, 10dlu, 60dlu", "20dlu, 20dlu, 20dlu, 60dlu, 20dlu"));
        builder.setDefaultDialogBorder();
        builder.addSeparator("Nodes set", CC.xy(1, 1));
        builder.add(nodesSetBox, CC.xy(1, 2));
        builder.addSeparator("Selected nodes", CC.xy(1, 3));
        builder.add(new JScrollPane(nodesList), CC.xyw(1, 4, 3));
        builder.add(okButton, CC.xy(1, 5));
        builder.add(cancelButton, CC.xy(3, 5));
        add(builder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void nodesSetBoxStateChanged() {
        if(nodesSetBox.getSelectedIndex() == 0) {
            for(int i = 0 ; i < nodesList.getModel().getSize() ; i++ ) {
                ((JCheckBox)nodesList.getModel().getElementAt(i)).setSelected(false);
            }
        }
        if(nodesSetBox.getSelectedIndex() == 2) {
            for(int i = 0 ; i < nodesList.getModel().getSize() ; i++ ) {
                ((JCheckBox)nodesList.getModel().getElementAt(i)).setSelected(true);
            }
        }
        nodesList.repaint();
    }

    private void nodeListStateChanged() {
        if(nodesSetBox.getSelectedIndex() != 1)
            nodesSetBox.setSelectedIndex(1);
    }
}
