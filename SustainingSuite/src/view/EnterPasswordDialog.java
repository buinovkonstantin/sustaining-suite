package view;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Григорий
 * Date: 30.05.11
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class EnterPasswordDialog extends JDialog {
    private JButton okButton;
    private JButton cancelButton;
    private JPasswordField passwordField;

    public EnterPasswordDialog(Frame owner, final ConnectDialog.AuthResult authResult) {
        super(owner, "Enter password", true);
        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authResult.setAccept(true);
                dispose();
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authResult.setAccept(false);
                dispose();
            }
        });
        passwordField = new JPasswordField();

        FormLayout formLayout = new FormLayout("100px, 100px",
                "25px,10px,25px");
        CellConstraints c = new CellConstraints();
        PanelBuilder builder = new PanelBuilder(formLayout);
        builder.setDefaultDialogBorder();
        builder.add(passwordField, c.xyw(1,1,2));
        builder.add(okButton, c.xyw(1,3,1));
        builder.add(cancelButton, c.xyw(2,3,1));

        add(builder.getPanel());
        pack();
        setResizable(false);
        setVisible(true);
    }
}
