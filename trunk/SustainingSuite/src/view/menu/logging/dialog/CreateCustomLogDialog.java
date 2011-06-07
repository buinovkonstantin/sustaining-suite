package view.menu.logging.dialog;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import model.logging.LogSink;
import view.CentraStarAnalyzer;
import view.components.jlist.JCheckBoxListRenderer;
import view.components.jlist.JListUtils;
import view.components.jtable.JComboBoxCellRenderer;
import view.components.jtable.JComboBoxEditor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 6/6/11
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
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
        super(CentraStarAnalyzer.link, "Create Custom log", true);

        nodesField = new JTextField();
        nodesButton = new JButton("Select nodes...");
        nodesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NodeSelectionDialog();
            }
        });
        statusBox = new JComboBox(LogSink.Level.values());
        componentsList = JListUtils.createCheckBoxesList(LogSink.Component.values());
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
        filterTable.getColumnModel().getColumn(0).setCellEditor(new JComboBoxEditor(new JComboBox(LogSink.logFilterType.values())));
        filterTable.getColumnModel().getColumn(0).setCellRenderer(new JComboBoxCellRenderer(LogSink.logFilterType.values()));
        ((DefaultTableModel)model).addRow(new Object[] {LogSink.logFilterType.message, "NoCapacity"});

        addFilterButton = new JButton("Add...");
        addFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel)filterTable.getModel()).addRow(new Object[] {LogSink.logFilterType.message, ""});
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
        panelBuilder.add(statusBox, CC.xy(1, 5, CC.CENTER, CC.CENTER));
        panelBuilder.addSeparator("Components", CC.xyw(1, 7, 3));
        panelBuilder.add(new JScrollPane(componentsList), CC.xyw(1, 8, 3));
        panelBuilder.addSeparator("Filters", CC.xyw(1, 10, 3));
        panelBuilder.add(new JScrollPane(filterTable), CC.xyw(1, 11, 3));
        panelBuilder.add(addFilterButton, CC.xy(1, 12, CC.CENTER, CC.CENTER));
        panelBuilder.add(removeFilterButton, CC.xy(3, 12, CC.CENTER, CC.CENTER));        panelBuilder.add(createButton, CC.xy(1, 13));
        panelBuilder.add(cancelButton, CC.xy(3, 13));
        add(panelBuilder.getPanel());
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
