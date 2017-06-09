package helpers;

import javax.swing.JOptionPane;

public class Dialogs {

    private static String ALERTA = "Alerta";
    private static String ERRO = "Erro";
    private static String INFO = "Informaçao";
    private static String CONFIRMAR = "Confirmar Operaçao";

    public static void showInfo(String msg) {
        JOptionPane.showMessageDialog(null, msg, INFO, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showWarning(String msg) {
        JOptionPane.showMessageDialog(null, msg, ALERTA, JOptionPane.WARNING_MESSAGE);
    }

    public static void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, ERRO, JOptionPane.ERROR_MESSAGE);
    }

    public static void showConfirm(String msg) {
        JOptionPane.showConfirmDialog(null, msg, CONFIRMAR, JOptionPane.OK_CANCEL_OPTION);
    }

}
