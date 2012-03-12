package client.view.menu.analyze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import client.view.MainFrame;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import common.util.FileUtils;

import javax.swing.*;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
    
    // file with encoded source to be decoded
    // null if source is entered into text area
    private File encodedFile = null;

    // file with decoded source to be encoded
    // null if source is entered into text area
	private File decodedFile = null;

    public Base64Dialog() {
        super(MainFrame.link, "Base64", false);
        // decode section
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
        decodeAndSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	int retVal = fileChooser.showOpenDialog(Base64Dialog.this);
            	if(retVal == JFileChooser.APPROVE_OPTION) {
            		decodeAndSaveAs(fileChooser.getSelectedFile());
            	}
            }
        });
        
        // encode section
        decodedOpenButton = new JButton("Open decoded file...");
        decodedOpenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	int retVal = fileChooser.showOpenDialog(Base64Dialog.this);
            	if(retVal == JFileChooser.APPROVE_OPTION) {
            		setDecodedFileAsSource(fileChooser.getSelectedFile());
            	}
            }
        });
        
        decodedArea = new JTextArea(10,20);
        encodeButton = new JButton("Encode");
        encodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		encodeToTextArea();
            }
        });
        
        encodeAndSaveButton = new JButton("Encode and save as...");
        encodeAndSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	int retVal = fileChooser.showOpenDialog(Base64Dialog.this);
            	if(retVal == JFileChooser.APPROVE_OPTION) {
            		encodeAndSaveAs(fileChooser.getSelectedFile());
            	}
            }
        });

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

	private void resetEncodedSource() {
		encodedArea.setText(null);
		encodedFile = null;
	}

	private void resetDecodedSource() {
		decodedArea.setText(null);
		decodedFile = null;
	}

	private void setEncodedFileAsSource(File selectedFile) {
		resetEncodedSource();
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
	
	private byte[] decodeData() {
		
		try {
			if((encodedFile == null) || (encodedArea.getText().length() > 0)) {
				return new BASE64Decoder().decodeBuffer(encodedArea.getText());
			} else {
				return new BASE64Decoder().decodeBuffer(new FileInputStream(encodedFile));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

	private void decodeToTextArea() {
		resetDecodedSource();

		byte[] decodedData = decodeData();
		if(decodedData == null)
			JOptionPane.showMessageDialog(this, "Entered data is not valid Base64-encoded data", "Invalid source data", JOptionPane.ERROR_MESSAGE);
		else
			decodedArea.setText(new String(decodedData));
	}

	private void decodeAndSaveAs(File selectedFile) {
		byte[] decodedData = decodeData();
		if(decodedData == null)
			JOptionPane.showMessageDialog(this, "Specified source file has not valid Base64-encoded data", "Invalid source data", JOptionPane.ERROR_MESSAGE);
		else {
			OutputStream outputStream;
			try {
				outputStream = new FileOutputStream(selectedFile);
				outputStream.write(decodedData);
			} catch (FileNotFoundException fnfException) {
				// TODO Auto-generated catch block
				fnfException.printStackTrace();
			} catch (IOException ioException) {
				// TODO Auto-generated catch block
				ioException.printStackTrace();
			}
		}
	}
	
	private void setDecodedFileAsSource(File selectedFile) {
		resetDecodedSource();
		decodedFile = selectedFile;
		
		if(decodedFile.length() > MAXIMAL_AUTO_LOADED_FILE_SIZE) {
			int choice = JOptionPane.showConfirmDialog(Base64Dialog.this, 
					"The file may contain binary data, \n" +
					"moreover its size exceeds " + 
					MAXIMAL_AUTO_LOADED_FILE_SIZE + 
					" bytes. \nDo you still want to get it loaded into edit area?");
			if(choice == JOptionPane.NO_OPTION)
				return;
		} else {
			int choice = JOptionPane.showConfirmDialog(Base64Dialog.this, 
					"The file may contain binary data.\n" +
					"Do you want to get it loaded into edit area?");
			if(choice == JOptionPane.NO_OPTION)
					return;
		}
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(decodedFile);
			byte[] inputArray = new byte[100];
			boolean stop = false;
			while(!stop) {
				int bytesRead = inputStream.read(inputArray);
				if(bytesRead == -1) {
					stop = true;
					continue;
				} else {
					decodedArea.append(new String(inputArray, 0, bytesRead));
					if(bytesRead < inputArray.length) {
						stop = true;
					}
				}
			}
			
			// drop association with original file to allow user 
			// to modify loaded content for further decoding
			decodedFile = null;
		} catch (FileNotFoundException fnfException) {
			// TODO Auto-generated catch block
			fnfException.printStackTrace();
		}
		catch (IOException ioException) {
			// TODO Auto-generated catch block
			ioException.printStackTrace();
		}
	}

	private void encodeToTextArea() {
		resetEncodedSource();

		String encodedData = encodeData();
		if(encodedData == null)
			JOptionPane.showMessageDialog(this, "Entered data is not valid Base64-encoded data", "Invalid source data", JOptionPane.ERROR_MESSAGE);
		else
			encodedArea.setText(encodedData);
	}

	private String encodeData() {
		
		if((decodedFile == null) || (decodedArea.getText().length() > 0)) {
			return new BASE64Encoder().encodeBuffer(decodedArea.getText().getBytes());
		} else {
			FileInputStream inputStream;
			StringBuilder encodedData = new StringBuilder();
			BASE64Encoder encoder = new BASE64Encoder();
			
			try {
				inputStream = new FileInputStream(decodedFile);
				final int DECODED_SOURCE_CHUNK = 300;
				byte[] inputArray = new byte[DECODED_SOURCE_CHUNK];
				boolean stop = false;
				while(!stop) {
					int bytesRead = inputStream.read(inputArray);
					if(bytesRead == -1) {
						stop = true;
						continue;
					} else {
						if(bytesRead < DECODED_SOURCE_CHUNK) {
							encodedData.append(encoder.encode(Arrays.copyOf(inputArray, bytesRead)));
						} else
							encodedData.append(encoder.encode(inputArray));
							
						if(bytesRead < inputArray.length) {
							stop = true;
						}
					}
				}
				
				return encodedData.toString();
			} catch (FileNotFoundException fnfException) {
				// TODO Auto-generated catch block
				fnfException.printStackTrace();
			}
			catch (IOException ioException) {
				// TODO Auto-generated catch block
				ioException.printStackTrace();
			}

		}

		// TODO Auto-generated method stub
		return null;
	}

	private void encodeAndSaveAs(File selectedFile) {
		String encodedData = encodeData();
		if(encodedData == null)
			JOptionPane.showMessageDialog(this, "There is an error occurred during data encoding", "Encoding error", JOptionPane.ERROR_MESSAGE);
		else {
			OutputStream outputStream;
			try {
				outputStream = new FileOutputStream(selectedFile);
				outputStream.write(encodedData.getBytes());
			} catch (FileNotFoundException fnfException) {
				// TODO Auto-generated catch block
				fnfException.printStackTrace();
			} catch (IOException ioException) {
				// TODO Auto-generated catch block
				ioException.printStackTrace();
			}
		}
	}
}
