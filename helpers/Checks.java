/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.Calendar;

/**
 *
 * @author qwerty
 */
public class Checks {

    public static class Date {

        public static boolean isSunday(java.util.Date data) {

            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            int dayofwk = cal.get(Calendar.DAY_OF_WEEK);
            return (dayofwk == Calendar.SUNDAY);

        }

        public static boolean isSaturday(java.util.Date data) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            int dayofwk = cal.get(Calendar.DAY_OF_WEEK);
            return (dayofwk == Calendar.SATURDAY);
        }

        public boolean isWeekDay(java.util.Date data) {
            return (!isSunday(data)) && (!isSaturday(data));
        }

    }

}
