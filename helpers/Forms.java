/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author qwerty
 */
public class Forms {

    public static void showInternal(JDesktopPane objDesk, JInternalFrame objFrm) {
        objDesk.add(objFrm);
        Dimension dS = objDesk.getSize();
        Dimension ifz = objFrm.getSize();
        objFrm.setLocation((dS.width - ifz.width) / 2,
                (dS.height - ifz.height) / 2);
        objFrm.setVisible(true);
    }

    public static boolean OnlyNumbers(java.awt.event.KeyEvent evt) {

        int xKey = Character.getNumericValue(evt.getKeyChar());

        if (!(((xKey >= 0) && (xKey <= 9)))) {
            evt.consume();
            return false;
        } else {
            return true;
        }
                
    }

    public static void goNextField(int CodeKey, Object Field) {
        if (CodeKey == 10) {
            if (Field.getClass() == JTextField.class) {
                ((JTextField) Field).requestFocus();
            } else if (Field.getClass() == JFormattedTextField.class) {
                ((JFormattedTextField) Field).requestFocus();
            } else if (Field.getClass() == JPasswordField.class) {
                ((JPasswordField) Field).requestFocus();
            } else if (Field.getClass() == JCheckBox.class) {
                ((JCheckBox) Field).requestFocus();
            } else if (Field.getClass() == JButton.class) {
                ((JButton) Field).doClick();
            } else if (Field.getClass() == JComboBox.class) {
                ((JComboBox) Field).requestFocus();
            }
        }
    }
}
