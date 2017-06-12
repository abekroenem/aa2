package helpers;

import Env.Constants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import models.DB;

public class Config {

    public static int getLang() throws SQLException {

        int lang = Constants.PT_BR;
        ResultSet rs = DB.Connect().prepareStatement("SELECT VALUE_X FROM CONFIG WHERE KEY_X= 'lang' ").executeQuery();
        if (rs.next()) {
            lang = rs.getInt("VALUE_X");
        }
        return lang;
    }

    public static void setLang(int Lang) throws SQLException {
        PreparedStatement pstm = DB.Connect().prepareStatement("UPDATE CONFIG SET VALUE_X = ? WHERE KEY_X = ? ");
        pstm.setInt(1, Lang);
        pstm.setString(2, "lang");
        pstm.executeUpdate();
        pstm.close();
    }

    public static ResourceBundle getResources() {
        ResourceBundle rb = null;
        try {

            if (Config.getLang() == Constants.EN_US) {
                Locale en = new Locale("en", "US");
                Locale.setDefault(en);
                rb = ResourceBundle.getBundle("lang/lg_en_US");

            } else if (Config.getLang() == Constants.PT_BR) {
                Locale pt = new Locale("pt", "BR");
                Locale.setDefault(pt);
                rb = ResourceBundle.getBundle("lang/lg");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rb;
    }

}
