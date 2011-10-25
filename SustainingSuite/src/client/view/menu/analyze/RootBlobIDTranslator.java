package client.view.menu.analyze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.view.MainFrame;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class RootBlobIDTranslator extends JDialog {

	private JLabel rbidMD5Label;
	private JTextField rbidMD5TextField;
	private JButton rbidMD5ToCanonicalButton;
	private JLabel canonicalMD5Label;
	private JTextField canonicalMD5TextField;
	private JButton canonicalMD5toRBAButton;

	public RootBlobIDTranslator() {
        super(MainFrame.link, "RootBlobID translator", false);
        
        rbidMD5Label = new JLabel("MD5 in RootBlobID format");
        rbidMD5TextField = new JTextField();
        rbidMD5ToCanonicalButton = new JButton("to canonical format");
        rbidMD5ToCanonicalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	convertRBIDMd5ToCanonical();
            }
        });
        
        canonicalMD5Label = new JLabel("MD5 in canonical hexadecimal format");
        canonicalMD5TextField = new JTextField();
        canonicalMD5toRBAButton = new JButton("to RootBlobID format");
        canonicalMD5toRBAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	convertCanonicalMd5ToRBID();
            }
        });
        
        FormLayout formLayout = new FormLayout("150dlu, 10dlu, 100dlu", "15dlu, 20dlu, 10dlu, 15dlu, 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.setDefaultDialogBorder();
        panelBuilder.add(rbidMD5Label, c.xy(1, 1));
        panelBuilder.add(rbidMD5TextField, c.xy(1, 2));
        panelBuilder.add(rbidMD5ToCanonicalButton, c.xy(3, 2));
        panelBuilder.add(canonicalMD5Label, c.xy(1, 4));
        panelBuilder.add(canonicalMD5TextField, c.xy(1, 5));
        panelBuilder.add(canonicalMD5toRBAButton, c.xy(3, 5));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setMinimumSize(getSize());
        setResizable(false);
        setVisible(true);
	}
	
	private void convertRBIDMd5ToCanonical() {
		String rbidMD5 = rbidMD5TextField.getText();
		if(rbidMD5.length() != 27 || !rbidMD5.matches("[0-9a-vA-V]{13}[xXeE][0-9a-vA-V]{13}")) {
			JOptionPane.showMessageDialog(this, "RootBlobID MD5 is not valid", "Invalid source MD5", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String canonicalMD5 = convert13DigitRBIDToHex(rbidMD5.substring(0, 13)).concat(convert13DigitRBIDToHex(rbidMD5.substring(14)));

		canonicalMD5TextField.setText(canonicalMD5);
	}

	private String convert13DigitRBIDToHex(String rbid) {
		StringBuilder reversedHexResult = new StringBuilder(16);
		
		reversedHexResult.append(rbid.substring(0, 1));
		for(int chunkIndex=0; chunkIndex<3; chunkIndex++) {
			String hexChunk = Integer.toHexString(Integer.parseInt(rbid.substring(1+chunkIndex*4, 5+chunkIndex*4), 32));
			String paddedHexChunk = "00000".substring(0, 5-hexChunk.length()).concat(hexChunk);
			reversedHexResult.append(paddedHexChunk);
		}
		
		StringBuilder hexResult = new StringBuilder(16);
		for(int hexChunkIndex=7; hexChunkIndex>=0; hexChunkIndex--)
			hexResult.append(reversedHexResult.substring(hexChunkIndex*2, hexChunkIndex*2+2));
		
		return hexResult.toString().toLowerCase();
	}
	
	private void convertCanonicalMd5ToRBID() {
		String canonicalMD5 = canonicalMD5TextField.getText();
		if(canonicalMD5.length() != 32 || !canonicalMD5.matches("[0-9a-fA-F]{32}")) {
			JOptionPane.showMessageDialog(this, "Hexadecimal MD5 is not valid", "Invalid source MD5", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String rbidMD5 = convert16DigitHexToRBID(canonicalMD5.substring(0, 16)).concat("?").concat(convert16DigitHexToRBID(canonicalMD5.substring(16)));

		rbidMD5TextField.setText(rbidMD5);
	}

	private String convert16DigitHexToRBID(String hex) {
		StringBuilder reverseHex = new StringBuilder(16);
		for(int hexChunkIndex=7; hexChunkIndex>=0; hexChunkIndex--)
			reverseHex.append(hex.substring(hexChunkIndex*2, hexChunkIndex*2+2));
		
		StringBuilder rbidResult = new StringBuilder(13);
		
		rbidResult.append(reverseHex.substring(0, 1));
		for(int chunkIndex=0; chunkIndex<3; chunkIndex++) {
			String rbidChunk = Integer.toString(Integer.parseInt(reverseHex.substring(1+chunkIndex*5, 6+chunkIndex*5), 16), 32);
			String paddedRBIDChunk = "0000".substring(0, 4-rbidChunk.length()).concat(rbidChunk);
			rbidResult.append(paddedRBIDChunk);
		}
		
		return rbidResult.toString().toUpperCase();
	}

}
