package client.view.menu.analyze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.MainFrame;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

public class ZLibCodingDialog extends JDialog {
    JLabel selectLabel;
    JRadioButton zLibEncodeButton;
    JRadioButton zLibDecodeButton;
    ButtonGroup group;
    JButton saveButton;
    JButton cancelButton;

    public ZLibCodingDialog() {
        super(MainFrame.link, "ZLib coding", false);
        selectLabel = new JLabel("Select processing type");
        zLibEncodeButton = new JRadioButton("ZLib encode data");
        zLibDecodeButton = new JRadioButton("ZLib decode data");;
        group = new ButtonGroup();
        group.add(zLibEncodeButton);
        group.add(zLibDecodeButton);
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	int retVal = fileChooser.showOpenDialog(ZLibCodingDialog.this);
            	if(retVal == JFileChooser.APPROVE_OPTION) {
            		ZLibCodingDialog.this.dispose();
            	}
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ZLibCodingDialog.this.dispose();
            }
        });
        FormLayout formLayout = new FormLayout("40dlu, 10dlu, 40dlu", "20dlu, 20dlu, 20dlu, 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.setDefaultDialogBorder();

        panelBuilder.add(selectLabel, c.xyw(1, 1, 3));
        panelBuilder.add(zLibEncodeButton, c.xyw(1, 2, 3));
        panelBuilder.add(zLibDecodeButton, c.xyw(1, 3, 3));
        panelBuilder.add(saveButton, c.xy(1, 4));
        panelBuilder.add(cancelButton, c.xy(3, 4));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
