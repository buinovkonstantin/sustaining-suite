package client.view.menu.analyze;

import client.view.MainFrame;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Base64Dialog extends JDialog {
    JButton encodedOpenButton;
    JTextArea encodedArea;
    JButton decodeButton;
    JButton decodeAndSaveButton;
    JButton decodedOpenButton;
    JTextArea decodedArea;
    JButton encodeButton;
    JButton encodeAndSaveButton;

    public Base64Dialog() {
        super(MainFrame.link, "Base64", false);
        encodedOpenButton = new JButton("Open encoded file...");
        encodedArea = new JTextArea(10,20);
        decodeButton = new JButton("Decode");
        decodeAndSaveButton= new JButton("Decode and save as...");
        decodedOpenButton = new JButton("Open decoded file...");
        decodedArea = new JTextArea(10,20);
        encodeButton = new JButton("Encode");
        encodeAndSaveButton = new JButton("Encode and save as...");
        FormLayout formLayout = new FormLayout("100dlu, 80dlu:grow(1), 90dlu", "20dlu, 20dlu, 80dlu:grow(.5), 20dlu, 20dlu, 20dlu, 20dlu, 80dlu:grow(.5), 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.setDefaultDialogBorder();
        panelBuilder.addSeparator("Base64 Encoded data", c.xyw(1, 1, 3));
        panelBuilder.add(encodedOpenButton, c.xy(1, 2));
        panelBuilder.add(new JScrollPane(encodedArea), c.xyw(1, 3, 3));
        panelBuilder.add(decodeButton, c.xy(1, 4));
        panelBuilder.add(decodeAndSaveButton, c.xy(3, 4));
        panelBuilder.addSeparator("Base64 Decoded data", c.xyw(1, 6, 3));
        panelBuilder.add(decodedOpenButton, c.xy(1, 7));
        panelBuilder.add(new JScrollPane(decodedArea), c.xyw(1, 8, 3));
        panelBuilder.add(encodeButton, c.xy(1, 9));
        panelBuilder.add(encodeAndSaveButton, c.xy(3, 9));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setMinimumSize(getSize());
        setResizable(true);
        setVisible(true);
    }
}
