package controllers;

import java.sql.SQLException;
import helpers.Dialogs;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import views.frmLogin;

public class MainController {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        try {
            new frmLogin().setVisible(true);
        } catch (SQLException ex) {
            Dialogs.showError(ex.getMessage());
        }
    }

}
