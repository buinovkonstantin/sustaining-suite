package client.view;

import client.model.ClientContext;
import client.model.ClientContextException;
import client.model.ConnectionParams;

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
	private ConnectionParams existingConnection;

    public ClusterConnectionInfoDialog(Dialog owner, ConnectionParams existingConnection) {
        super(owner, "Edit cluster connection info", true);

       	this.existingConnection = existingConnection;
        
        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(updateConnectionsParams());
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
        connectionNameField = new JTextField();
        clusterAddressLabel = new JLabel("Cluster address");
        clusterAddressField = new JTextField();
        loginLabel = new JLabel("Login");
        loginField = new JTextField();

        if(existingConnection != null) {
        	connectionNameField.setText(existingConnection.getConnectionName());
        	clusterAddressField.setText(existingConnection.getAddress());
        	loginField.setText(existingConnection.getLogin());
        }
        
        FormLayout formLayout = new FormLayout("90px, 15px, 90px",
            "15px,20px, 10px, 15px,20px, 10px, 15px,20px, 15px, 20px");
        CellConstraints c = new CellConstraints();
        PanelBuilder builder = new PanelBuilder(formLayout);
        builder.setDefaultDialogBorder();
        builder.add(connectionNameLabel, c.xyw(1, 1, 3));
        builder.add(connectionNameField, c.xyw(1, 2, 3));
        builder.add(clusterAddressLabel, c.xyw(1, 4, 3));
        builder.add(clusterAddressField, c.xyw(1, 5, 3));
        builder.add(loginLabel, c.xyw(1, 7, 3));
        builder.add(loginField, c.xyw(1, 8, 3));
        builder.add(okButton, c.xy(1, 10));
        builder.add(cancelButton, c.xy(3, 10));

        add(builder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

	protected boolean updateConnectionsParams() {
		ConnectionParams newConnectionParams = new ConnectionParams(
				connectionNameField.getText(),
				clusterAddressField.getText(),
				loginField.getText());

		try {
			if(!ClientContext.updateConnectionsParams(existingConnection, newConnectionParams)) {
				JOptionPane.showMessageDialog(this, "Connection with specified name already exists.\nPlease enter another name for the connection", "Existing connection name", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} catch (ClientContextException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Unexpected error occurred", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
}
