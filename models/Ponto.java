/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

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
    int horas_excedidas;
    double percent_aplicado;
    double valor_extra;
    double total_recebido;

    public Ponto() {
        this.Id = 0;
        this.data = null;
        this.id_funcionario = 0;
        this.entrada_a = 0;
        this.saida_a = 0;
        this.entrada_b = 0;
        this.saida_b = 0;
        this.horas_excedidas = 0;
        this.percent_aplicado = 0;
        this.valor_extra = 0;
        this.total_recebido = 0;
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

    public void setData(Date data) {
        if (data == null) {
            throw new IllegalArgumentException("Dia do registro de ponto deve ser informado!");
        }
        this.data = data;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
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
        return horas_excedidas;
    }

    public void setHoras_excedidas(int horas_excedidas) {
        this.horas_excedidas = horas_excedidas;
    }

    public double getPercent_aplicado() {
        return percent_aplicado;
    }

    public void setPercent_aplicado(double percent_aplicado) {
        this.percent_aplicado = percent_aplicado;
    }

    public double getValor_extra() {
        return valor_extra;
    }

    public void setValor_extra(double valor_extra) {
        this.valor_extra = valor_extra;
    }

    public double getTotal_recebido() {
        return total_recebido;
    }

    public void setTotal_recebido(double total_recebido) {
        if (total_recebido < 1) {
            throw new IllegalArgumentException("Total recebido no dia deve ser maior que zero!");
        }

        this.total_recebido = total_recebido;
    }

}
