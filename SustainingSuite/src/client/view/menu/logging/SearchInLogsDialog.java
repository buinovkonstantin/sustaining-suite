package client.view.menu.logging;

import client.view.CentraStarAnalyzer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 6/2/11
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchInLogsDialog extends JDialog {

    JTextField fromDateField;
    JTextField toDateField;
    JTextField nodesField;
    JButton nodesButton;
    JCheckBox filepoolBox;
    JCheckBox platformBox;
    JCheckBox osBox;
    JCheckBox patternBox;
    JTextField expressionField;
    JButton searchButton;
    JButton cancelButton;
    public SearchInLogsDialog() {
        super(CentraStarAnalyzer.link, "Search in logs", false);
        fromDateField = new JTextField();
        toDateField = new JTextField();
        nodesField = new JTextField();
        nodesButton = new JButton("Select nodes...");
        nodesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NodeSelectionDialog();
            }
        });
        filepoolBox = new JCheckBox("Filepool");
        platformBox = new JCheckBox("Platform");
        osBox = new JCheckBox("OS");
        patternBox = new JCheckBox("regular expression");
        searchButton = new JButton("Search");
        searchButton.setEnabled(false);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CentraStarAnalyzer.link.getDesktop().add(new LogFrame());
                SearchInLogsDialog.this.dispose();
            }
        });
        expressionField = new JTextField();
        cancelButton = new JButton("Cancel");
        LogTypeActionListener logTypeActionListener = new LogTypeActionListener(searchButton, filepoolBox, platformBox, osBox);
        filepoolBox.addActionListener(logTypeActionListener);
        platformBox.addActionListener(logTypeActionListener);
        osBox.addActionListener(logTypeActionListener);

        FormLayout formLayout = new FormLayout("60dlu, 5dlu, 60dlu, 5dlu, 60dlu,", "20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu, 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        panelBuilder.setDefaultDialogBorder();
        panelBuilder.addSeparator("Time interval", CC.xyw(1, 1, 5));
        panelBuilder.add(new JLabel("From date"), CC.xy(1, 2), fromDateField, CC.xy(1, 3, CC.FILL, CC.CENTER));
        panelBuilder.add(new JLabel("To date"), CC.xy(5, 2), toDateField, CC.xy(5, 3, CC.FILL, CC.CENTER));
        panelBuilder.addSeparator("Nodes", CC.xyw(1, 5, 5));
        panelBuilder.add(nodesField, CC.xyw(1, 6, 3, CC.FILL, CC.CENTER));
        panelBuilder.add(nodesButton, CC.xyw(4, 6, 2, CC.CENTER, CC.CENTER));
        panelBuilder.addSeparator("Log types", CC.xyw(1, 8, 5));
        panelBuilder.add(filepoolBox, CC.xy(1, 9, CC.CENTER, CC.CENTER));
        panelBuilder.add(platformBox, CC.xy(3, 9, CC.CENTER, CC.CENTER));
        panelBuilder.add(osBox, CC.xy(5, 9, CC.CENTER, CC.CENTER));
        panelBuilder.addSeparator("Search pattern", CC.xyw(1, 11, 5));
        panelBuilder.add(patternBox, CC.xyw(1, 12, 5, CC.LEFT, CC.CENTER));
        panelBuilder.add(expressionField, CC.xyw(1, 13, 5, CC.FILL, CC.FILL));
        panelBuilder.add(searchButton, CC.xy(1,14, CC.CENTER, CC.CENTER));
        panelBuilder.add(cancelButton, CC.xy(5,14, CC.CENTER, CC.CENTER));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    private class LogTypeActionListener implements ActionListener {

        private JButton searchButton;
        private JCheckBox[] checkBoxes;

        private LogTypeActionListener(JButton searchButton, JCheckBox... checkBoxes) {
            this.searchButton = searchButton;
            this.checkBoxes = checkBoxes;
        }

        public void actionPerformed(ActionEvent e) {
            for(JCheckBox checkBox:checkBoxes) {
                if(checkBox.isSelected()) {
                    searchButton.setEnabled(true);
                    return;
                }
            }
            searchButton.setEnabled(false);
        }
    }
}
