package client.view.menu.analyze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import client.view.MainFrame;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import common.util.FileUtils;

import javax.swing.*;

import sun.misc.BASE64Decoder;

public class Base64Dialog extends JDialog {
	
	private static final long MAXIMAL_AUTO_LOADED_FILE_SIZE = 10 * FileUtils.KiB;
	
	JButton encodedOpenButton;
    JTextArea encodedArea;
    JButton decodeButton;
    JButton decodeAndSaveButton;
    
    JButton decodedOpenButton;
    JTextArea decodedArea;
    JButton encodeButton;
    JButton encodeAndSaveButton;
    
    File encodedFile = null;

    public Base64Dialog() {
        super(MainFrame.link, "Base64", false);
        encodedOpenButton = new JButton("Open encoded file...");
        encodedOpenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	int retVal = fileChooser.showOpenDialog(Base64Dialog.this);
            	if(retVal == JFileChooser.APPROVE_OPTION) {
            		setEncodedFileAsSource(fileChooser.getSelectedFile());
            	}
            }
        });
        
        encodedArea = new JTextArea(10,20);
        decodeButton = new JButton("Decode");
        decodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		decodeToTextArea();
            }
        });
        
        decodeAndSaveButton= new JButton("Decode and save as...");
        decodedOpenButton = new JButton("Open decoded file...");
        decodedArea = new JTextArea(10,20);
        encodeButton = new JButton("Encode");
        encodeAndSaveButton = new JButton("Encode and save as...");
        FormLayout formLayout = new FormLayout("100dlu, 80dlu:grow(1), 90dlu", "20dlu, 20dlu, 80dlu:grow(.5), 20dlu, 20dlu, 20dlu, 20dlu, 80dlu:grow(.5), 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.setDefaultDialogBorder();
        panelBuilder.addSeparator("Base64 Encoded data", c.xyw(1, 1, 3));
        panelBuilder.add(encodedOpenButton, c.xy(1, 2));
        panelBuilder.add(new JScrollPane(encodedArea), c.xyw(1, 3, 3));
        panelBuilder.add(decodeButton, c.xy(1, 4));
        panelBuilder.add(decodeAndSaveButton, c.xy(3, 4));
        panelBuilder.addSeparator("Base64 Decoded data", c.xyw(1, 6, 3));
        panelBuilder.add(decodedOpenButton, c.xy(1, 7));
        panelBuilder.add(new JScrollPane(decodedArea), c.xyw(1, 8, 3));
        panelBuilder.add(encodeButton, c.xy(1, 9));
        panelBuilder.add(encodeAndSaveButton, c.xy(3, 9));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setMinimumSize(getSize());
        setResizable(true);
        setVisible(true);
    }

	private void setEncodedFileAsSource(File selectedFile) {
		encodedArea.setText(null);
		
		encodedFile = selectedFile;
		
		if(encodedFile.length() > MAXIMAL_AUTO_LOADED_FILE_SIZE) {
			int choice = JOptionPane.showConfirmDialog(Base64Dialog.this, 
					"The size of file with encoded content exceeds " + 
					MAXIMAL_AUTO_LOADED_FILE_SIZE + 
					" bytes.\nDo you want to get it loaded into edit area?");
			if(choice == JOptionPane.NO_OPTION)
				return;
		}
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(encodedFile);
			byte[] inputArray = new byte[100];
			boolean stop = false;
			while(!stop) {
				int bytesRead = inputStream.read(inputArray);
				if(bytesRead == -1) {
					stop = true;
					continue;
				} else {
					encodedArea.append(new String(inputArray, 0, bytesRead));
					if(bytesRead < inputArray.length) {
						stop = true;
					}
				}
			}
			
			// drop association with original file to allow user 
			// to modify loaded content for further decoding
			encodedFile = null;
		} catch (FileNotFoundException fnfException) {
			// TODO Auto-generated catch block
			fnfException.printStackTrace();
		}
		catch (IOException ioException) {
			// TODO Auto-generated catch block
			ioException.printStackTrace();
		}
 	}
	
	private void decodeToTextArea() {
		// TODO Auto-generated method stub
		
	}
}
