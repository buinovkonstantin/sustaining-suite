package view.menu.cluster;

import view.CentraStarAnalyzer;
import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExitDialog extends JDialog {
    public ExitDialog() {
       super(CentraStarAnalyzer.link, "Disconnect from cluster", true);

//        setTitle("Disconnect from cluster");
//        setModal(true);

        setMinimumSize(new Dimension(300, 150));
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JCheckBox serverModuleBox = new JCheckBox("stop server module");
        JCheckBox sessionContextBox = new JCheckBox("remove session context");
        panel.add(new JLabel("Are you want to disconnect"));
        panel.add(new JLabel(" from cluster?")); //no time to investigate of JLAbel newline
        panel.add(serverModuleBox);
        panel.add(sessionContextBox);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(new JButton("Yes"));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(new JButton("No"));
        buttonPanel.add(Box.createHorizontalGlue());
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);
    }
}
