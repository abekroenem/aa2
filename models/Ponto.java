/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.FuncionarioController;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

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

    public Ponto() {
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
            throw new IllegalArgumentException("Dia do registro de ponto deve ser informado!");
        }
        this.data = data;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) throws SQLException {
        objFun = new FuncionarioController().getByID(this.id_funcionario);
        if (id_funcionario < 1) {
            throw new IllegalArgumentException("Funcionario deve ser informado em um registro de ponto!");
        }
        this.id_funcionario = id_funcionario;
    }

    public int getEntrada_a() {
        return entrada_a;
    }

    public void setEntrada_a(int entrada_a) {
        if (entrada_a < 1) {
            throw new IllegalArgumentException("Primeira entrada do dia deve ser informada!");
        }
        this.entrada_a = entrada_a;
    }

    public int getSaida_a() {
        return saida_a;
    }

    public void setSaida_a(int saida_a) {
        if (saida_a < 1) {
            throw new IllegalArgumentException("Primeira saida do dia deve ser informada!");
        }
        this.saida_a = saida_a;
    }

    public int getEntrada_b() {
        return entrada_b;
    }

    public void setEntrada_b(int entrada_b) {
        if (entrada_b < 1) {
            throw new IllegalArgumentException("Segunda entrada do dia deve ser informada!");
        }

        this.entrada_b = entrada_b;
    }

    public int getSaida_b() {
        return saida_b;
    }

    public void setSaida_b(int saida_b) {
        if (saida_b < 1) {
            throw new IllegalArgumentException("Segunda saida do dia deve ser informada!");
        }
        this.saida_b = saida_b;
    }

    public int getHoras_excedidas() {
        if (objFun != null) {
            return (this.getHoras_Trabalhadas() - objFun.gethora_dia());
        } else {
            return 0;
        }
    }

    public double getPercent_aplicado() throws SQLException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getData());
        int dayofwk = cal.get(Calendar.DAY_OF_WEEK);
        if (this.getHoras_excedidas() != 0) {
            if (dayofwk == Calendar.SUNDAY) {
                return 100;
            } else {
                return 50;
            }
        } else {
            return 0;
        }
    }

    public double getValor_extra() throws SQLException {
        if (objFun != null) {
            double min_value = objFun.getValor_hora() * 60;
            return ((this.getHoras_excedidas() * min_value) * (this.getPercent_aplicado() / 100)) / 60;
        } else {
            return 0;
        }
    }

    public double getTotal_recebido() throws SQLException {
        if (objFun != null) {
            return (objFun.gethora_dia() * 60) + this.getValor_extra();
        } else {
            return 0;
        }
    }

    public int getHoras_Trabalhadas() {
        return ((this.saida_a) - (this.entrada_a)) + ((this.saida_b) - (this.entrada_b));
    }

}
