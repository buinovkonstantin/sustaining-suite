package view.menu.analyze.dialog;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import view.CentraStarAnalyzer;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 6/1/11
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZLibCodingDialog extends JDialog {
    JLabel selectLabel;
    JRadioButton zLibEncodeButton;
    JRadioButton zLibDecodeButton;
    ButtonGroup group;
    JButton okButton;
    JButton cancelButton;

    public ZLibCodingDialog() {
        super(CentraStarAnalyzer.link, "ZLib coding", false);
        selectLabel = new JLabel("Select processing type");
        zLibEncodeButton = new JRadioButton("ZLib encode data");
        zLibDecodeButton = new JRadioButton("ZLib decode data");;
        group = new ButtonGroup();
        group.add(zLibEncodeButton);
        group.add(zLibDecodeButton);
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        FormLayout formLayout = new FormLayout("40dlu, 10dlu, 40dlu", "20dlu, 20dlu, 20dlu, 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.setDefaultDialogBorder();
      //  panelBuilder.add(selectLabel, c.xyw());

        panelBuilder.add(selectLabel, c.xyw(1, 1, 3));
        panelBuilder.add(zLibEncodeButton, c.xyw(1, 2, 3));
        panelBuilder.add(zLibDecodeButton, c.xyw(1, 3, 3));
        panelBuilder.add(okButton, c.xy(1, 4));
        panelBuilder.add(cancelButton, c.xy(3, 4));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
