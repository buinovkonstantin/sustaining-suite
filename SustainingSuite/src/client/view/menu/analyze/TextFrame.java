package client.view.menu.analyze;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class TextFrame extends JInternalFrame {
    JTextArea area;
    public TextFrame(String name) {
        super(name, true, true, true, true);
        area = new JTextArea(10, 20);
        area.setWrapStyleWord(true);
        FormLayout formLayout = new FormLayout("50dlu:grow(1)", "50dlu:grow(1)");
        PanelBuilder panelBuilder = new PanelBuilder(formLayout);
        CellConstraints c = new CellConstraints();
        panelBuilder.add(new JScrollPane(area), c.xy(1, 1, CC.FILL, CC.FILL));
        add(panelBuilder.getPanel());
        pack();
        setVisible(true);
    }
}
