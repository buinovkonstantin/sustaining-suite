package client.view.menu.logging;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import client.model.ClientContext;
import client.view.MainFrame;
import client.view.NodeSelectionDialog;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import common.protocol.tasks.Log;

public class CreateCustomLogDialog extends JDialog {
    JTextField nodesField;
    JButton nodesButton;
    JComboBox statusBox;

    JList componentsList;

    JTable filterTable;
    JButton addFilterButton;
    //    JButton editFilterButton;
    JButton removeFilterButton;

    JButton createButton;
    JButton cancelButton;


    public CreateCustomLogDialog() {
        super(MainFrame.link, "Create Custom log", true);

        nodesField = new JTextField();
        nodesButton = new JButton("Select nodes...");
        nodesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NodeSelectionDialog();
            }
        });
        statusBox = new JComboBox(Log.Level.values());
        componentsList = JListUtils.createCheckBoxesList(ClientContext.availableLoggers);
        //TODO       methods \/ refactor to /\ method
        componentsList.setCellRenderer(new JCheckBoxListRenderer());
        componentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        componentsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                int index = list.locationToIndex(event.getPoint());
                JCheckBox item = (JCheckBox)list.getModel().getElementAt(index);
                item.setSelected(!item.isSelected());
                list.repaint(list.getCellBounds(index, index));
            }
        });

        filterTable = new JTable();
        filterTable.setRowHeight(25);
        TableModel model = filterTable.getModel();
        ((DefaultTableModel)model).addColumn("Type");
        ((DefaultTableModel)model).addColumn("Value");
        filterTable.getColumnModel().getColumn(0).setCellEditor(new JComboBoxEditor(new JComboBox(ClientContext.logFilterType.values())));
        filterTable.getColumnModel().getColumn(0).setCellRenderer(new JComboBoxCellRenderer(ClientContext.logFilterType.values()));
        ((DefaultTableModel)model).addRow(new Object[] {ClientContext.logFilterType.message, "NoCapacity"});

        addFilterButton = new JButton("Add...");
        addFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel)filterTable.getModel()).addRow(new Object[] {ClientContext.logFilterType.message, ""});
            }
        });
        removeFilterButton = new JButton("Remove");
        removeFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(filterTable.getSelectedRow() != -1)
                    ((DefaultTableModel)filterTable.getModel()).removeRow(filterTable.getSelectedRow());
            }
        });
        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateCustomLogDialog.this.dispose();
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateCustomLogDialog.this.dispose();
            }
        });

        FormLayout formLayout = new FormLayout("80dlu, 10dlu:grow(1), 80dlu,", "20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 80dlu, 20dlu, 20dlu, 80dlu:grow(1), 20dlu, 20dlu, 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        panelBuilder.setDefaultDialogBorder();
        panelBuilder.addSeparator("Nodes", CC.xyw(1, 1, 3));
        panelBuilder.add(nodesField, CC.xy(1, 2, CC.FILL, CC.CENTER));
        panelBuilder.add(nodesButton, CC.xy(3, 2, CC.CENTER, CC.CENTER));
        panelBuilder.addSeparator("Log level", CC.xyw(1, 4, 3));
        panelBuilder.add(statusBox, CC.xy(1, 5, CC.LEFT, CC.CENTER));
        panelBuilder.addSeparator("Components", CC.xyw(1, 7, 3));
        panelBuilder.add(new JScrollPane(componentsList), CC.xyw(1, 8, 3));
        panelBuilder.addSeparator("Filters", CC.xyw(1, 10, 3));
        panelBuilder.add(new JScrollPane(filterTable), CC.xyw(1, 11, 3));
        panelBuilder.add(addFilterButton, CC.xy(1, 12, CC.CENTER, CC.CENTER));
        panelBuilder.add(removeFilterButton, CC.xy(3, 12, CC.CENTER, CC.CENTER));
        panelBuilder.add(createButton, CC.xy(1, 13));
        panelBuilder.add(cancelButton, CC.xy(3, 13));
        add(panelBuilder.getPanel());
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
