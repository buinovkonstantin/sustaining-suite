package client.view.menu.cluster;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.MainFrame;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import common.controller.ModulesController;

import javax.swing.*;

public class ExitDialog extends JDialog {

    private JCheckBox serverModuleBox;
    private JCheckBox sessionContextBox;
    private JLabel disconnectInfoLabel;
    private JButton yesButton;
    private JButton noButton;
    
	private ModulesController controller;

    public ExitDialog(ModulesController controller) {
        super(MainFrame.link, "Exit the program", true);
        this.controller = controller;
        disconnectInfoLabel = new JLabel("Are you sure you want to close the program?");
        serverModuleBox = new JCheckBox("stop server module");
        sessionContextBox = new JCheckBox("remove session context");
        yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExitDialog.this.controller.stop();
			}
		});
        noButton = new JButton("No");
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
