package controllers;

import java.sql.SQLException;
import helpers.Dialogs;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import views.frmLogin;

public class MainController {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        UIManager.put("InternalFrame.useTaskBar", Boolean.FALSE);
        try {
            new frmLogin(null).setVisible(true);
        } catch (SQLException ex) {
            Dialogs.showError(ex.getMessage());
        }

        //     new mditeste().setVisible(true);
    }

}
