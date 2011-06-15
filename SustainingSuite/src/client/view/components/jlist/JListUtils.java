package client.view.components.jlist;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: skripg
 * Date: 6/7/11
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
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
