package client.view.components.jtable;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
* Created by IntelliJ IDEA.
* User: skripg
* Date: 6/6/11
* Time: 6:32 PM
* To change this template use File | Settings | File Templates.
*/
public class JComboBoxCellRenderer extends JComboBox implements TableCellRenderer {
    public JComboBoxCellRenderer(Object[] items) {
        super(items);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setSelectedItem(value);
        return this;
    }
}
