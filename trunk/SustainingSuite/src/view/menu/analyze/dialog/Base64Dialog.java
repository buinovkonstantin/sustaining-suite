package view.menu.analyze.dialog;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.FormLayout;
import view.CentraStarAnalyzer;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 5/31/11
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Base64Dialog extends JDialog {
    public Base64Dialog() {
        super(CentraStarAnalyzer.link, "Base64", false);

        FormLayout formLayout = new FormLayout();
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);

        add(panelBuilder.getPanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
