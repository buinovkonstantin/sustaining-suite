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
 * Time: 7:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class DownloadDialog extends JDialog {
    JButton fileSelectButton;
    JButton nodeSelectButton;
    JButton okButton;
    JButton cancelButton;

    public DownloadDialog() {
        super(CentraStarAnalyzer.link, "Download", true);
        fileSelectButton = new JButton("Select files...");
        fileSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FileSelectionDialog();
            }
        });
        nodeSelectButton = new JButton("Nodes...");
        nodeSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NodeSelectionDialog();
            }
        });
        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CentraStarAnalyzer.link.add(new DownloadProgressFrame());
                DownloadDialog.this.dispose();
            }
        });
        cancelButton = new JButton("Cancel");
        FormLayout formLayout = new FormLayout("40dlu, 5dlu, 40dlu", "20dlu, 20dlu, 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        panelBuilder.setDefaultDialogBorder();
        panelBuilder.add(fileSelectButton, CC.xyw(1, 1, 3 ));
        panelBuilder.add(nodeSelectButton, CC.xyw(1, 2, 3));
        panelBuilder.add(okButton, CC.xy(1, 3));
        panelBuilder.add(cancelButton, CC.xy(3, 3));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
