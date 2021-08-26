/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author pretto
 */
public class Calendario {

    SimpleDateFormat sdData = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdHora = new SimpleDateFormat("HH:mm");
    Calendar cal = new GregorianCalendar();//instancia // objeto pra utilizar os metodos

    public String obterDataAtualDMA() {
        return String.valueOf(sdData.format(cal.getTime()));
    }

    public String obterDataAtualMais(int dias) {
        cal.add(Calendar.DAY_OF_MONTH, dias);
        return (sdData.format(cal.getTime()));
    }

    public String obterDataAtualMenos(int dias) {
        cal.add(Calendar.DAY_OF_MONTH, -dias);
        return (sdData.format(cal.getTime()));
    }

    public String obterHoraAtual() {
        Date data = new Date();
        return (String.valueOf(sdHora.format(data)));
    }
}
