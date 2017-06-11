/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author qwerty
 */
public class Formats {

    public static class CPF {

        public static String Format(String cpf) {
            cpf = Unformat(cpf);
            if (cpf.length() == 11) {
                return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
            } else {
                return "";
            }
        }

        public static String Unformat(String cpf) {

            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
            cpf = cpf.replaceAll(" ", "");
            return cpf;
        }

    }

}
