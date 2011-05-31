package view;

import view.menu.MenuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/27/11
 * Time: 2:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class CentraStarAnalyzer extends JFrame {
    public static CentraStarAnalyzer link;
    JPanel menuPanel = new MenuPanel();
    JPanel mainPanel = new JPanel();

    public CentraStarAnalyzer() throws HeadlessException {
        super("CentraStarAnalyzer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 300));
        setSize(600, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(menuPanel);
        getContentPane().add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        link = this;
    }

    public static void main(String[] args) {
        new CentraStarAnalyzer();
    }
}
