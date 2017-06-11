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

        public static String Unformat(String cpf) {

            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");
            cpf = cpf.replaceAll(" ", "");
            return cpf;
        }

    }

}
