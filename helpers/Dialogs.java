package helpers;

import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class Dialogs {

    private static String ALERTA = "Alerta";
    private static String ERRO = "Erro";
    private static String INFO = "Informaçao";
    private static String CONFIRMAR = "Confirmar Operaçao";
    private static Object[] opcoes = null;

    @SuppressWarnings("empty-statement")
    private static void Traduz() {
        ResourceBundle rsb = Config.getResources();

        ALERTA = rsb.getString("alerta_titulo");
        ERRO = rsb.getString("erro_titulo");
        INFO = rsb.getString("info_titulo");
        CONFIRMAR = rsb.getString("confirma_op");

        opcoes = new Object[]{rsb.getString("sim"), rsb.getString("nao")};

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

    public static int showConfirm(String msg) {
        Traduz();
        return JOptionPane.showOptionDialog(null, msg, CONFIRMAR, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

    }

}
