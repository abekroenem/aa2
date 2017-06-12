package helpers;

import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class Dialogs {

    private static String ALERTA = "Alerta";
    private static String ERRO = "Erro";
    private static String INFO = "Informaçao";
    private static String CONFIRMAR = "Confirmar Operaçao";

    private static void Traduz(){
        ResourceBundle rsb = Config.getResources();

        ALERTA = rsb.getString("alerta_titulo");
        ERRO = rsb.getString("erro_titulo");
        INFO = rsb.getString("info_titulo");
        CONFIRMAR = rsb.getString("confirma_op");

    }

    public static void showInfo(String msg) {
        Traduz();
        JOptionPane.showMessageDialog(null, msg, INFO, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showWarning(String msg) {
        Traduz();
        JOptionPane.showMessageDialog(null, msg, ALERTA, JOptionPane.WARNING_MESSAGE);
    }

    public static void showError(String msg) {
        Traduz();
        JOptionPane.showMessageDialog(null, msg, ERRO, JOptionPane.ERROR_MESSAGE);
    }

    public static void showConfirm(String msg) {
        Traduz();
        JOptionPane.showConfirmDialog(null, msg, CONFIRMAR, JOptionPane.OK_CANCEL_OPTION);
    }

}
