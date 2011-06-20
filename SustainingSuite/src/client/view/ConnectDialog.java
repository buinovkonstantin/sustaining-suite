package client.view;

import client.controller.ConnectionController;
import client.library.ClientUserInterface;
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

public class ConnectDialog extends JDialog {
    private JButton okButton;
    private JButton cancelButton;

    private JList connectionList;
    private DefaultListModel connectionListModel;
    
    private JLabel addressLabel;
    private JLabel loginLabel;

    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JTextField addressField;
    private JTextField loginField;
    
	private ConnectionController connectionController;

    public ConnectDialog(Frame parent, ConnectionController connectionController) throws HeadlessException {
        super(parent, "Choose a cluster to connect to", true);
        
        this.connectionController = connectionController;

        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnterPasswordDialog passwordDialog = 
                	new EnterPasswordDialog(ConnectDialog.this);
                if(passwordDialog.getPassword() != null &&
                		ConnectDialog.this.getSelectedConnectionParams() != null) {
                	ConnectDialog.this.connectionController.establishConnection(
                			ConnectDialog.this.getSelectedConnectionParams(),
                			passwordDialog.getPassword(),
                			new ConnectionClientUserInterface(ConnectDialog.this));
                    ConnectDialog.this.dispose();
                }
            }
        });
        
        cancelButton = new JButton("Cancel");
        addButton = new JButton("Add...");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClusterConnectionInfoDialog(ConnectDialog.this, null);
                updateConnectionListModel();
            }
        });
        editButton = new JButton("Edit...");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String selectedListItem = (String) connectionList.getSelectedValue();
            	if(selectedListItem == null)
            		return;
            	
            	ConnectionParams selectedConnection = ClientContext.getConnectionsParams().get(selectedListItem);
                new ClusterConnectionInfoDialog(ConnectDialog.this, selectedConnection);
                updateConnectionListModel();
            }
        });
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Object selectedConnection = connectionList.getSelectedValue();
            	if(selectedConnection != null) {
            		ConnectionParams removedConnection = ClientContext.getConnectionsParams().get((String) selectedConnection);
            		try {
						ClientContext.updateConnectionsParams(removedConnection, null);
	                    updateConnectionListModel();
					} catch (ClientContextException e1) {
						e1.printStackTrace();
					}
            	}
            		
            }
        });

        connectionListModel = new DefaultListModel();
        updateConnectionListModel();
        
        connectionList = new JList(connectionListModel);
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
                "15px,20px, 5px, 15px,20px, 15px,20px,10px, 20px,10px,20px, 15px,20px");
        CellConstraints c = new CellConstraints();
        PanelBuilder builder = new PanelBuilder(formLayout);
        builder.setDefaultDialogBorder();
        builder.add(jScrollPane, c.xywh(1,1,1,11));
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setMinimumSize(getSize());
        setMaximumSize(getSize());
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private ConnectionParams getSelectedConnectionParams() {
    	if(addressField.getText().isEmpty() || loginField.getText().isEmpty()) {
    		// incomplete manually entered information - try to get selected
    		// connection from list
			String selectedConnection = (String) connectionList.getSelectedValue();
    		if(selectedConnection != null) {
    			// connection is selected in list - try to get its params
    			return ClientContext.getConnectionsParams().get(selectedConnection);
    		}
    		
    		return null;
    	}

    	// combine manually entered connection info
    	return new ConnectionParams(null, addressField.getText(), loginField.getText());
	}

	private void updateConnectionListModel() {
    	connectionListModel.clear();
    	
        for(String connectionName : ClientContext.getConnectionsParams().keySet())
        	connectionListModel.addElement(connectionName);
	}
	
	private class ConnectionClientUserInterface implements ClientUserInterface {

		private Component parent;

		public ConnectionClientUserInterface(Component parent) {
			this.parent = parent;
		}
		
		@Override
		public void showMessage(String message) {
			JOptionPane.showMessageDialog(parent, message);
		}

		@Override
		public boolean promptYesNo(String message) {
			return (JOptionPane.showConfirmDialog(parent, message) == JOptionPane.YES_OPTION);
		}
		
	}

}
