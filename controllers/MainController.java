package controllers;

import java.sql.SQLException;
import helpers.Dialogs;
import views.frmLogin;

public class MainController {

    public static void main(String[] args) {
        try {
            new frmLogin().setVisible(true);
        } catch (SQLException ex) {
            Dialogs.showError(ex.getMessage());
        }
    }

}
