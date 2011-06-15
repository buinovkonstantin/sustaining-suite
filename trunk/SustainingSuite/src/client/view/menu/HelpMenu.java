package client.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;


import client.view.CentraStarAnalyzer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/27/11
 * Time: 2:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class HelpMenu extends JMenu {
    public HelpMenu() {
        super("Help");

        // User's Manual Menu item
        JMenuItem usersManualMenuItem = new JMenuItem("User's Manual");
        usersManualMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// TODO open default browser with help content
            }
        });
        add(usersManualMenuItem);

        // About Menu item
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AboutDialog();
            }
        });
        add(aboutMenuItem);
    }
    
    private class AboutDialog extends JDialog {
    	public AboutDialog() {
            super(CentraStarAnalyzer.link, "About Sustaining suite", false);
            JLabel titleLabel = new JLabel("Sustaining suite v 1.0.31");
            JLabel descriptionLabel1 = new JLabel("Special toolset for sustaining purposes");
            JLabel descriptionLabel2 = new JLabel("applied to EMC Centera product");
            JTextField contactField = new JTextField("Contact: buinov.konstantin@emc.com");
            contactField.setEditable(false);
            JButton okButton = new JButton("Ok");
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	AboutDialog.this.dispose();
                }
            });

            FormLayout formLayout = new FormLayout("100px, 60px, 100px", "20px, 10px, 20px, 20px, 10px, 20px, 10px, 20px");
            PanelBuilder panelBuilder = new PanelBuilder(formLayout);
            CellConstraints c = new CellConstraints();
            panelBuilder.setDefaultDialogBorder();

            panelBuilder.add(titleLabel, c.xyw(1, 1, 3));
            panelBuilder.add(descriptionLabel1, c.xyw(1, 3, 3));
            panelBuilder.add(descriptionLabel2, c.xyw(1, 4, 3));
            panelBuilder.add(contactField, c.xyw(1, 6, 3));
            panelBuilder.add(okButton, c.xy(2, 8));
            add(panelBuilder.getPanel());
            pack();
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
    	}
    }
}
