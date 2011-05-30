package view.menu.cluster;

import view.CentraStarAnalyzer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 4:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class SessionsDialog extends JDialog {

    private JButton cancelButton;
    private JButton removeButton;
    private JButton resumeButton;

    public SessionsDialog() {
        super(CentraStarAnalyzer.link, "Sessions", false);
        setMinimumSize(new Dimension(500, 300));
        JPanel panel = new JPanel();
        JPanel labelPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        resumeButton = new JButton("Resume");
        removeButton = new JButton("Remove");
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SessionsDialog.this.dispose();
            }
        });
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        //labelPanel.add(Box.createHorizontalStrut(30));
        labelPanel.add(new JLabel("Existing sessions"));
        labelPanel.add(Box.createHorizontalGlue());
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        SessionsTable sessionsTable = new SessionsTable();
        sessionsTable.setFillsViewportHeight(true);
        tablePanel.add(sessionsTable.getTableHeader());
        tablePanel.add(sessionsTable);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(resumeButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(cancelButton);
        panel.add(labelPanel);
        panel.add(new JScrollPane(tablePanel));
        panel.add(buttonPanel);
        getContentPane().add(panel);
    }
}
