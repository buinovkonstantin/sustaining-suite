package client.view.menu.cluster.dialog;

import client.view.CentraStarAnalyzer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExitDialog extends JDialog {

    private JCheckBox serverModuleBox;
    private JCheckBox sessionContextBox;
    private JLabel disconnectInfoLabel;
    private JButton yesButton;
    private JButton noButton;

    public ExitDialog() {
        super(CentraStarAnalyzer.link, "Disconnect from cluster", true);
        disconnectInfoLabel = new JLabel("Are you sure you want to disconnect from cluster?");
        serverModuleBox = new JCheckBox("stop server module");
        sessionContextBox = new JCheckBox("remove session context");
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        setTitle("Disconnect from cluster");
        setModal(true);

        FormLayout formLayout = new FormLayout("70dlu, 10dlu, 70dlu", "20dlu, 20dlu, 20dlu, 20dlu");
        CellConstraints c = new CellConstraints();
        PanelBuilder builder = new PanelBuilder(formLayout);
        builder.setDefaultDialogBorder();
        builder.add(disconnectInfoLabel, c.xyw(1, 1, 3));
        builder.add(serverModuleBox, c.xyw(1, 2, 3));
        builder.add(sessionContextBox, c.xyw(1, 3, 3));
        builder.add(yesButton, c.xy(1, 4, "default,center"));
        builder.add(noButton, c.xy(3, 4, "default,center"));
        add(builder.getPanel());
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
