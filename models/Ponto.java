/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.FuncionarioController;
import helpers.Config;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 *
 * @author qwerty
 */
public class Ponto {

    int Id;
    Date data;
    int id_funcionario;
    int entrada_a;
    int saida_a;
    int entrada_b;
    int saida_b;
    private Funcionario objFun = null;
    private String dia_pnt, func_pnt, ea, sa, eb, sb;

    private void Traduz() {

        ResourceBundle rbl = null;
        rbl = Config.getResources();

        dia_pnt = rbl.getString("dia_pnt");
        func_pnt = rbl.getString("func_pnt");
        ea = rbl.getString("ea");
        sa = rbl.getString("sa");
        eb = rbl.getString("eb");
        sb = rbl.getString("sb");

    }

    public Ponto() {
        Traduz();
        this.Id = 0;
        this.data = null;
        this.id_funcionario = 0;
        this.entrada_a = 0;
        this.saida_a = 0;
        this.entrada_b = 0;
        this.saida_b = 0;

    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Date getData() {
        return data;
    }

    public void setData(java.sql.Date data) {
        if (data == null) {
            throw new IllegalArgumentException(dia_pnt);
        }
        this.data = data;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) throws SQLException {
        this.objFun = new FuncionarioController().getByID(id_funcionario);
        if (id_funcionario < 1) {
            throw new IllegalArgumentException(func_pnt);
        }
        this.id_funcionario = id_funcionario;
    }

    public int getEntrada_a() {
        return entrada_a;
    }

    public void setEntrada_a(int entrada_a) {
        if (entrada_a < 1) {
            throw new IllegalArgumentException(ea);
        }
        this.entrada_a = entrada_a;
    }

    public int getSaida_a() {
        return saida_a;
    }

    public void setSaida_a(int saida_a) {
        if (saida_a < 1) {
            throw new IllegalArgumentException(sa);
        }
        this.saida_a = saida_a;
    }

    public int getEntrada_b() {
        return entrada_b;
    }

    public void setEntrada_b(int entrada_b) {
        if (entrada_b < 1) {
            throw new IllegalArgumentException(eb);
        }

        this.entrada_b = entrada_b;
    }

    public int getSaida_b() {
        return saida_b;
    }

    public void setSaida_b(int saida_b) {
        if (saida_b < 1) {
            throw new IllegalArgumentException(sb);
        }
        this.saida_b = saida_b;
    }

    public int getHoras_excedidas() {
        if (objFun != null) {
            int horas_trab = this.getHoras_Trabalhadas();
            int hora_dia = objFun.gethora_dia() * 60;
            if (horas_trab <= hora_dia) {
                return 0;
            } else {
                return horas_trab - hora_dia;
            }

        } else {
            return 0;
        }
    }

    public double getPercent_aplicado() throws SQLException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getData());
        int dayofwk = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofwk == Calendar.SUNDAY) {
            return 100;
        } else if (this.getHoras_excedidas() != 0) {
            return 50;
        } else {
            return 0;
        }
    }

    public double getValor_extra() throws SQLException {
        if (objFun != null) {
            double valor_tot = 0;
            if (this.getPercent_aplicado() == 50) {
                double min_value = objFun.getValor_hora() / 60;
                double horas_exec = this.getHoras_excedidas();
                valor_tot = (horas_exec * min_value);
                valor_tot = (valor_tot) * (this.getPercent_aplicado() / 100);
                valor_tot = valor_tot / 60;
            }

            return valor_tot;
        } else {
            return 0;
        }
    }

    public double getTotal_recebido() throws SQLException {
        if (objFun != null) {
            double valor_hd = 0.0;
            if (objFun.gethora_dia() < this.getHoras_Trabalhadas()) {
                valor_hd = (objFun.gethora_dia() * objFun.getValor_hora());
            } else {
                valor_hd = this.getHoras_Trabalhadas();
            }
            double valor_ext = this.getValor_extra();
            if (this.getPercent_aplicado() == 50) {
                return valor_hd + valor_ext;
            } else if (this.getPercent_aplicado() == 100) {
                return valor_hd + valor_hd;
            } else {
                return valor_hd;
            }
        } else {
            return 0;
        }
    }

    public int getHoras_Trabalhadas() {
        int horas_corridas = (this.saida_b) - (this.entrada_a);
        int intervalo = (this.entrada_b) - (this.saida_a);
        int horas_trab = horas_corridas - intervalo;
        return horas_trab;
    }

}
