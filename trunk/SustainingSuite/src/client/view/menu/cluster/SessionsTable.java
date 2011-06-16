package client.view.menu.cluster;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SessionsTable extends JTable {

    private static String[][] exampleOfData = new String[][]{
            {"c001n02", "yes", "10.05.2011 13:57:03 UTC", "35 Mb"},
            {"c001n03", "no", "11.03.2011 10:51:25 UTC", "12 Mb"},
            {"", "", "", ""},
            {"", "", "", ""},
    };
    private static String[] columnNames = {"Server node", "Active", "Creation date", "Context size"};

    public SessionsTable(JButton resumeButton) {
        //very stupid, only for example
        super(exampleOfData, columnNames);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getSelectionModel().addListSelectionListener(new SessionTableListener(resumeButton));
    }

    private class SessionTableListener implements ListSelectionListener {

        private JButton resumeButton;

        private SessionTableListener(final JButton resumeButton) {
            this.resumeButton = resumeButton;
        }

        public void valueChanged(ListSelectionEvent e) {
            int row = getSelectedRow();
            String s = exampleOfData[row][1];

            if(s.contains("yes")) {
                this.resumeButton.setEnabled(true);
            } else {
                this.resumeButton.setEnabled(false);
            }
        }
    }
}
