package view.menu.analyze;

import view.CentraStarAnalyzer;
import view.menu.analyze.dialog.TextFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 3:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class SmartPacketsMenu extends JMenuItem {
    public SmartPacketsMenu() {
        super("SmartPackets");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CentraStarAnalyzer.link.add(new TextFrame("Smart packet content"));
            }
        });
    }
}
