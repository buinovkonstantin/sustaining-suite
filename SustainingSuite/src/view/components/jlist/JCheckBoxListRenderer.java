package view.components.jlist;

import javax.swing.*;
import java.awt.*;

/**
* Created by IntelliJ IDEA.
* User: skripg
* Date: 6/6/11
* Time: 6:25 PM
* To change this template use File | Settings | File Templates.
*/
public class JCheckBoxListRenderer extends JCheckBox implements ListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index,boolean isSelected, boolean hasFocus) {
        setEnabled(list.isEnabled());
        setSelected(((JCheckBox)value).isSelected());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(((JCheckBox)value).getText());
        return this;
    }
}
