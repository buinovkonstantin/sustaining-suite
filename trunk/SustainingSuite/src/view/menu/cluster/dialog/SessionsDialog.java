package view.menu.cluster.dialog;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import view.CentraStarAnalyzer;

import javax.swing.*;
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
    private JLabel existingSessionsLabel;
    private SessionsTable sessionsTable;

    public SessionsDialog() {
        super(CentraStarAnalyzer.link, "Sessions", false);
        existingSessionsLabel = new JLabel("Existing sessions");
        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SessionsDialog.this.dispose();
            }
        });
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No"};
                JOptionPane.showOptionDialog(SessionsDialog.this, "Are you sure to remove session context from cluster?", "Remove sessions", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SessionsDialog.this.dispose();
            }
        });
        sessionsTable = new SessionsTable();
        sessionsTable.setFillsViewportHeight(true);

        FormLayout formLayout = new FormLayout("left:50dlu, left:50dlu, 50dlu:grow(1), right:50dlu", "10dlu, 10dlu, top:80dlu:grow(1), 20dlu");
        CellConstraints c = new CellConstraints();
        PanelBuilder builder = new PanelBuilder(formLayout);
        builder.setDefaultDialogBorder();
        builder.add(existingSessionsLabel, c.xyw(1,1,3));
        builder.add(sessionsTable.getTableHeader(), c.xyw(1,2,4));
        builder.add(new JScrollPane(sessionsTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c.xyw(1, 3, 4));
        builder.add(resumeButton, c.xy(1,4));
        builder.add(removeButton, c.xy(2,4));
        builder.add(cancelButton, c.xy(4,4));
        add(builder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setMinimumSize(getSize());
        setVisible(true);
    }
}
