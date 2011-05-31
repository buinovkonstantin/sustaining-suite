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
 * Date: 5/30/11
 * Time: 6:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClusterConnectionInfoDialog extends JDialog {
    private JButton okButton;
    private JButton cancelButton;
    private JLabel connectionNameLabel;
    private JLabel clusterAddressLabel;
    private JTextField connectionNameField;
    private JTextField clusterAddressField;
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    //TODO
    public ClusterConnectionInfoDialog(Frame owner) {
        super(owner, "Edit cluster connection info", true);

        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        connectionNameLabel = new JLabel("Connection name");
        clusterAddressLabel = new JLabel("Cluster address");
        connectionNameField = new JTextField();
        connectionNameField.setSize(new Dimension(500, 40));
        connectionNameField.setMaximumSize(new Dimension(500, 40));
        clusterAddressField = new JTextField();
        clusterAddressField.setSize(new Dimension(500, 40));
        clusterAddressField.setMaximumSize(new Dimension(500, 40));
        loginLabel = new JLabel("Login");
        loginField = new JTextField();
        loginField.setSize(new Dimension(500, 40));
        loginField.setMaximumSize(new Dimension(500, 40));
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        passwordField.setSize(new Dimension(500, 40));
        passwordField.setMaximumSize(new Dimension(500, 40));

        FormLayout formLayout = new FormLayout("100px, 100px",
            "20px,20px, 10px,20px,20px, 10px,20px,20px, 10px,20px,20px, 10px,20px");
        CellConstraints c = new CellConstraints();
        PanelBuilder builder = new PanelBuilder(formLayout);
        builder.setDefaultDialogBorder();
        builder.add(connectionNameLabel, c.xyw(1, 1, 2));
        builder.add(connectionNameField, c.xyw(1, 2, 2));
        builder.add(clusterAddressLabel, c.xyw(1, 4, 2));
        builder.add(clusterAddressField, c.xyw(1, 5, 2));
        builder.add(loginLabel, c.xyw(1, 7, 2));
        builder.add(loginField, c.xyw(1, 8, 2));
        builder.add(passwordLabel, c.xyw(1, 10, 2));
        builder.add(passwordField, c.xyw(1, 11, 2));
        builder.add(okButton, c.xy(1, 13));
        builder.add(cancelButton, c.xy(2, 13));

//        JPanel p = new JPanel();
//        JPanel panel = new JPanel();
//        JPanel buttonPanel = new JPanel();
//        setMinimumSize(new Dimension(200, 300));
//
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.add(connectionNameLabel);
//        panel.add(connectionNameField);
//        panel.add(clusterAddressLabel);
//        panel.add(clusterAddressField);
//        panel.add(loginLabel);
//        panel.add(loginField);
//        panel.add(passwordLabel);
//        panel.add(passwordField);
//
//        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
////        buttonPanel.add(Box.createHorizontalGlue());
//        buttonPanel.add(okButton);
////        buttonPanel.add(Box.createHorizontalGlue());
//        buttonPanel.add(cancelButton);
////        buttonPanel.add(Box.createHorizontalGlue());
//
//       // p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
//        p.add(panel);
//        p.add(buttonPanel);
//        add(p);

        add(builder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
