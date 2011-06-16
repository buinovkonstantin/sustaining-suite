package client.view;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class EnterPasswordDialog extends JDialog {
    private JButton okButton;
    private JButton cancelButton;
    private JPasswordField passwordField;

    public EnterPasswordDialog(Dialog owner, final ConnectDialog.AuthResult authResult) {
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

        FormLayout formLayout = new FormLayout("20px, 80px, 15px, 80px, 20px",
                "20px,15px,20px");
        CellConstraints c = new CellConstraints();
        PanelBuilder builder = new PanelBuilder(formLayout);
        builder.setDefaultDialogBorder();
        builder.add(passwordField, c.xyw(1,1,5));
        builder.add(okButton, c.xy(2,3));
        builder.add(cancelButton, c.xy(4,3));

        add(builder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
