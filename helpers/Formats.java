/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author qwerty
 */
public class Formats {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

    public static class Hora {

        public static String Format(int Valor_Minutos) {
            Double horas = Valor_Minutos / 60.0;
            Double minutos = (horas - horas.intValue()) * 60;
            return String.format("%02d:%02d", horas.intValue(), minutos.intValue());
        }

        public static int Unformat(String Valor_Campo) {
            if (!Valor_Campo.replaceAll(" ", "").equals(":")) {
                int hora = Integer.parseInt(Valor_Campo.substring(0, Valor_Campo.indexOf(':'))) * 60;
                int minutos = Integer.parseInt(Valor_Campo.substring(Valor_Campo.indexOf(':') + 1, Valor_Campo.length()));
                return hora + minutos;
            } else {
                return 0;
            }

        }

    }

    public static class Data {

        public static String Format(java.sql.Date DataBanco) {
            return sdf.format(DataBanco);
        }

        public static java.sql.Date Unformat(String DataCampo) throws ParseException {
            if (!(DataCampo.replaceAll("/", "").replaceAll(" ", "").equals("2017"))) {
                java.util.Date data = sdf.parse(DataCampo);

                return (new java.sql.Date(data.getTime()));
            } else {
                return null;
            }

        }

    }

    public static class Valor {

        public static String Format(double Valor_Format) {
            return String.format("R$ %.2f", Valor_Format);
        }

    }

    public static class Percent {

        public static String Format(double Valor_Format) {
            return String.format("%.0f", Valor_Format) + " %";

        }

    }
    
    public static class Decimal {
        
        public static double Format(double value) {
            return Double.parseDouble(String.format("%.2f", value).replace(",", "."));
        
        }
    
    }

}
