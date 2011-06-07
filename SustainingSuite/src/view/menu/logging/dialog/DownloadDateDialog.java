package view.menu.logging.dialog;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import view.CentraStarAnalyzer;
import view.components.jlist.JCheckBoxListRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 7:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadDateDialog extends JDialog {
    JTextField nodesField;
    JButton nodesButton;
    JList typeOfDataList;
    JButton queryButton;
    JCheckBox oldSessionsBox;
    JTable existingDataTable;
    JButton downloadButton;
    JButton cancelButton;
    private static Object[][] exampleOfData = new String[][]{
            {"c001n01", "/var/log", "messages", "2010.09.20 18:48:35"},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
    };
    private static String[] columnNames = {"Node", "Path", "Name", "Creation date/time"};

    public DownloadDateDialog() {
    	super(CentraStarAnalyzer.link, "Download data", true);
        nodesField = new JTextField();
        nodesButton = new JButton("Select nodes...");
        nodesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NodeSelectionDialog();
            }
        });
        queryButton = new JButton("Query data");
        typeOfDataList = new JList(new JCheckBox[] {
            new JCheckBox("Filepool logfiles"),
            new JCheckBox("Platform logfiles"),
            new JCheckBox("OS logfiles"),
            new JCheckBox("TCP dumps")
        });
        typeOfDataList.setCellRenderer(new JCheckBoxListRenderer());
        typeOfDataList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        typeOfDataList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                int index = list.locationToIndex(event.getPoint());
                JCheckBox item = (JCheckBox)list.getModel().getElementAt(index);
                item.setSelected(!item.isSelected());
                list.repaint(list.getCellBounds(index, index));
            }
        });
        oldSessionsBox = new JCheckBox("Query data of the other sessions also");
        existingDataTable = new JTable(exampleOfData, columnNames);
        downloadButton = new JButton("Download selected data...");
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CentraStarAnalyzer.link.add(new DownloadProgressFrame());
            	DownloadDateDialog.this.dispose();
            }
        });
        cancelButton = new JButton("Cancel");

        FormLayout formLayout = new FormLayout("100dlu, 10dlu:grow(1), 60dlu", "20dlu, 20dlu, 20dlu, 20dlu, 40dlu:grow(0.5), 20dlu, 20dlu, 20dlu, 60dlu:grow(0.5), 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        panelBuilder.setDefaultDialogBorder();
        panelBuilder.addSeparator("Nodes", CC.xyw(1, 1, 3));
        panelBuilder.add(nodesField, CC.xy(1, 2));
        panelBuilder.add(nodesButton, CC.xy(3, 2));
        panelBuilder.addSeparator("Type of data", CC.xyw(1, 4, 3));
        panelBuilder.add(new JScrollPane(typeOfDataList), CC.xy(1, 5));
        panelBuilder.add(queryButton, CC.xy(1, 6));
        panelBuilder.add(oldSessionsBox, CC.xyw(1, 7, 3, CC.LEFT, CC.CENTER));
        panelBuilder.addSeparator("Existing data", CC.xyw(1, 8, 3));
        panelBuilder.add(new JScrollPane(existingDataTable), CC.xyw(1, 9, 3));
        panelBuilder.add(downloadButton, CC.xy(1, 10));
        panelBuilder.add(cancelButton, CC.xy(3, 10));
        add(panelBuilder.getPanel());
        pack();
        setMaximumSize(getSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
