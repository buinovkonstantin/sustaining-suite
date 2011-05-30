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
 * User: gregory
 * Date: 5/27/11
 * Time: 2:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectDialog extends JFrame {
    private JButton okButton;
    private JButton cancelButton;

    private JLabel addressLabel;
    private JLabel loginLabel;

    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JTextField addressField;
    private JTextField loginField;

    //TODO
    public ConnectDialog() throws HeadlessException {
        super("Choose a cluster to connect to");

        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AuthResult authResult = new AuthResult();
                new EnterPasswordDialog(ConnectDialog.this, authResult);
                if(authResult.isAccept()) {
                    new CentraStarAnalyzer();
                    ConnectDialog.this.dispose();
                }
            }
        });
        cancelButton = new JButton("Cancel");
        addButton = new JButton("Add...");
        editButton = new JButton("Edit...");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClusterConnectionInfoDialog(ConnectDialog.this);
            }
        });
        removeButton = new JButton("Remove");

        JList connectionList = new JList(new String[]{"hopkinton", "spb"});
        connectionList.setLayoutOrientation(JList.VERTICAL);
        connectionList.setVisibleRowCount(-1);
        JScrollPane jScrollPane = new JScrollPane(connectionList);
        jScrollPane.setPreferredSize(new Dimension(150, 400));

        addressLabel = new JLabel("Address");
        loginLabel = new JLabel("Login");
        addressField = new JTextField();
        addressField.setMaximumSize(new Dimension(200, 30));
        loginField = new JTextField();
        loginField.setMaximumSize(new Dimension(200, 30));

        FormLayout formLayout = new FormLayout("100px, 15px, 80px",
                "20px,20px, 15px,20px,20px, 15px,20px,15px, 20px,15px,20px, 15px,20px");
        CellConstraints c = new CellConstraints();
        PanelBuilder builder = new PanelBuilder(formLayout);
        builder.setDefaultDialogBorder();
        builder.add(jScrollPane, c.xywh(1,1,1,10));
        builder.add(addressLabel, c.xy(3,1));
        builder.add(addressField, c.xy(3,2));
        builder.add(loginLabel, c.xy(3,4));
        builder.add(loginField, c.xy(3,5));
        builder.add(addButton, c.xy(3,7));
        builder.add(editButton, c.xy(3,9));
        builder.add(removeButton, c.xy(3,11));
        builder.add(okButton, c.xy(1,13));
        builder.add(cancelButton, c.xy(3,13));

        add(builder.getPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setMinimumSize(getSize());
        setMaximumSize(getSize());
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ConnectDialog();
    }

    public class AuthResult {
        private boolean accept;
        private AuthResult() {
        }

        public boolean isAccept() {
            return accept;
        }

        public void setAccept(boolean accept) {
            this.accept = accept;
        }
    }

}
