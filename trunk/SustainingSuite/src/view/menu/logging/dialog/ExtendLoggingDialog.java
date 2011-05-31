package view.menu.logging.dialog;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import view.CentraStarAnalyzer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExtendLoggingDialog extends JDialog {
    JButton filterButton;
    JButton nodesButton;
    JButton startButton;
    JButton stopButton;
    public ExtendLoggingDialog() {
        super(CentraStarAnalyzer.link, "Extend logging", true);
        filterButton = new JButton("Filter");
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FilterDialog();
            }
        });
        nodesButton = new JButton("Nodes...");
        nodesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NodeSelectionDialog();
            }
        });
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");

        FormLayout formLayout = new FormLayout("50dlu, 5dlu, 50dlu,", "20dlu, 20dlu,");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        panelBuilder.setDefaultDialogBorder();
        panelBuilder.add(filterButton, CC.xy(1, 1));
        panelBuilder.add(nodesButton, CC.xy(3, 1));
        panelBuilder.add(startButton, CC.xy(1, 2));
        panelBuilder.add(stopButton, CC.xy(3, 2));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
