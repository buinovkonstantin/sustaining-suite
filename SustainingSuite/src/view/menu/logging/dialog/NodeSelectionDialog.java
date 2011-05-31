package view.menu.logging.dialog;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.org.apache.bcel.internal.generic.FieldOrMethod;
import view.CentraStarAnalyzer;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class NodeSelectionDialog extends JDialog {
    List<JCheckBox> list;
    JButton okButton;
    JButton cancelButton;
    JButton selectAllButton;
    JButton selectNoneButton;

    public NodeSelectionDialog() {
        super(CentraStarAnalyzer.link, "Select nodes", true);
        okButton = new JButton("Ok");
        cancelButton= new JButton("Cancel");
        selectAllButton = new JButton("Select All");
        selectNoneButton = new JButton("Select None");
        list = new LinkedList<JCheckBox>();
        for(int i = 1; i<6; i++) {
            list.add(new JCheckBox("c001n0" + i + ", id:295476250-12845"));
        }
        PanelBuilder builder = new PanelBuilder(new FormLayout("60dlu, 10dlu, 60dlu"));
        builder.setDefaultDialogBorder();
        int i;
        for(i = 0; i < list.size() ; i++) {
            builder.appendRow("pref");
            builder.add(list.get(i), CC.xyw(1, i+1, 3, CellConstraints.CENTER, CellConstraints.CENTER));
        }
        builder.appendRow("pref");
        builder.add(selectAllButton, CC.xy(1, i + 1));
        builder.add(selectNoneButton, CC.xy(3, i + 1));
        builder.appendRow("pref");
        builder.add(okButton, CC.xy(1, i +2 ));
        builder.add(cancelButton, CC.xy(3, i + 2));
        add(builder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
