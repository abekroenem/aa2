package helpers;

import Env.Constants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.DB;

public class Config {

    public static int getLang() throws SQLException {

        int lang = Constants.PT_BR;
        ResultSet rs = DB.Connect().prepareStatement("SELECT VALUE FROM CONFIG WHERE KEY='lang'").executeQuery();
        if (rs.next()) {
            lang = rs.getInt("VALUE");
        }
        return lang;
    }

    public static void setLang(int Lang) throws SQLException {
        PreparedStatement pstm = DB.Connect().prepareStatement("UPDATE CONFIG SET VALUE = ? WHERE KEY = 'lang'");
        pstm.setInt(1, Lang);
        pstm.executeQuery();
        pstm.close();
    }

}
