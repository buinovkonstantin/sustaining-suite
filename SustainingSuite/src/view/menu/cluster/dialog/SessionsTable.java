package view.menu.cluster.dialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 4:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class SessionsTable extends JTable {

    private static String[][] exampleOfData = new String[][]{
            {"c001n02", "yes", "10.05.2011 13:57:03 UTC", "35 Mb"},
            {"c001n03", "no", "11.03.2011 10:51:25 UTC", "12 Mb"},
            {"", "", "", ""},
            {"", "", "", ""},
    };
    private static String[] columnNames = {"Server node", "Active", "Creation date", "Context size"};

    public SessionsTable() {
        //very stupid, only for example
        super(exampleOfData, columnNames);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       // getSelectionModel().addListSelectionListener(new SessionTableListener());
    }

    private class SessionTableListener implements ListSelectionListener {

        private JButton resumeButton;

        private SessionTableListener(final JButton resumeButton) {
            this.resumeButton = resumeButton;
            resumeButton.setEnabled(true);
        }

        public void valueChanged(ListSelectionEvent e) {
            int row = getSelectedRow();
            String s = exampleOfData[row][1];
            this.resumeButton.setEnabled(true);

            if(s.contains("yes")) {
                this.resumeButton.setEnabled(true);
            } else {
                this.resumeButton.setEnabled(false);
            }
        }
    }
}
