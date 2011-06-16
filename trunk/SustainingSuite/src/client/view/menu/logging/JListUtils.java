package client.view.menu.logging;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class JListUtils {

    /**
     * create JList that containing JCheckBoxes with names = element.toString()
     * @param elements
     * @return
     */
    public static JList createCheckBoxesList(Object[] elements) {
        DefaultListModel listModel = new DefaultListModel();
        for(Object element:elements) {
            listModel.addElement(new JCheckBox(element.toString()));
        }
        return new JList(listModel);
    }
}
