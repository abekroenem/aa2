/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Env;

import models.Usuario;

/**
 *
 * @author qwerty
 */
public class Constants {

    public static final int PT_BR = 0;
    public static final int EN_US = 1;
    public static final double SALARIO_MINIMO = 937.00;

    public static Usuario ObjUser = null;

    public static int getJornada(int hora) {
        int jorn = 0;
        System.out.println(String.valueOf(hora));
        switch (hora) {
            case 8:
                jorn = 220;
                break;
            case 7:
                jorn = 210;
                break;
            case 6:
                jorn = 180;
                break;
            case 5:
                jorn = 150;
                break;
            case 4:
                jorn = 120;
                break;
        }

        return jorn;

    }
}
