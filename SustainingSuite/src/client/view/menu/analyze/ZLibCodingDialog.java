package client.view.menu.analyze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

import client.view.MainFrame;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

public class ZLibCodingDialog extends JDialog {
	private static final String MODE_COMPRESS = "COMPRESS";
	private static final String MODE_DECOMPRESS = "DECOMPRESS";
	
    JLabel selectLabel;
    JRadioButton zLibCompressButton;
    JRadioButton zLibDecompressButton;
    ButtonGroup group;
    JButton saveButton;
    JButton cancelButton;
    
	private File sourceFile;
    private String mode;

    public ZLibCodingDialog(File sourceFile) {
        super(MainFrame.link, "ZLib compression/decompression", false);
        this.sourceFile = sourceFile;
        
        selectLabel = new JLabel("Select processing type");
        
        zLibCompressButton = new JRadioButton("Compress data");
        zLibCompressButton.setActionCommand(MODE_COMPRESS);
        zLibCompressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = MODE_COMPRESS;
			}
		});
        zLibCompressButton.setSelected(true);
        
        zLibDecompressButton = new JRadioButton("Decompress data");;
        zLibDecompressButton.setActionCommand(MODE_DECOMPRESS);
        zLibDecompressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = MODE_DECOMPRESS;
			}
		});
        
        group = new ButtonGroup();
        group.add(zLibCompressButton);
        group.add(zLibDecompressButton);
        
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	int retVal = fileChooser.showOpenDialog(ZLibCodingDialog.this);
            	if(retVal == JFileChooser.APPROVE_OPTION) {
            		if(MODE_DECOMPRESS.equals(mode)) {
            			decompressAndSaveAs(fileChooser.getSelectedFile());
            		} else {
            			compressAndSaveAs(fileChooser.getSelectedFile());
            		}
            		
            		// close dialog if only output file is selected
            		// otherwise go back to mode selection
            		ZLibCodingDialog.this.dispose();
            	}
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ZLibCodingDialog.this.dispose();
            }
        });
        FormLayout formLayout = new FormLayout("40dlu, 10dlu, 40dlu", "20dlu, 20dlu, 20dlu, 20dlu");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.setDefaultDialogBorder();

        panelBuilder.add(selectLabel, c.xyw(1, 1, 3));
        panelBuilder.add(zLibCompressButton, c.xyw(1, 2, 3));
        panelBuilder.add(zLibDecompressButton, c.xyw(1, 3, 3));
        panelBuilder.add(saveButton, c.xy(1, 4));
        panelBuilder.add(cancelButton, c.xy(3, 4));
        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
	private void compressAndSaveAs(File selectedFile) {
		InputStream inputStream = null;
		OutputStream deflatedOutputStream = null;
		
		try {
			inputStream = new FileInputStream(sourceFile);
			deflatedOutputStream = new DeflaterOutputStream(new FileOutputStream(selectedFile));
			pumpData(inputStream, deflatedOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				deflatedOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void decompressAndSaveAs(File selectedFile) {
		InputStream inflatedInputStream = null;
		OutputStream outputStream = null;
		
		try {
			inflatedInputStream = new InflaterInputStream(new FileInputStream(sourceFile));
			outputStream = new FileOutputStream(selectedFile);
			pumpData(inflatedInputStream, outputStream);
		} catch (ZipException zipException) {
			JOptionPane.showMessageDialog(this, "Can not decompress source data: " + zipException.getMessage(), "Invalid source data", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} finally {
			try {
				inflatedInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void pumpData(InputStream inputStream, OutputStream outputStream) throws IOException {
		int size;
		byte[] buf = new byte[1024];
		
		while(inputStream.available() > 0) {
			size = inputStream.read(buf, 0, buf.length);
			if(size < 0)
				continue;
			outputStream.write(buf, 0, size);
		}
		
		outputStream.flush();
	}
}
