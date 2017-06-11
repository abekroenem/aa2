/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Env.Constants;
import helpers.Formats;

/**
 *
 * @author qwerty
 */
public class Funcionario {

    private int Id;
    private String Nome;
    private double salario;
    private int hora_dia;
    private String CPF;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) throws IllegalArgumentException {
        if (CPF.isEmpty()) {
            throw new IllegalArgumentException("CPF do Funcionario deve ser informado!");
        }
        this.CPF = Formats.CPF.Unformat(CPF);
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) throws IllegalArgumentException {
        if (Nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do Funcionario deve ser preencido!");
        }
        this.Nome = Nome.toUpperCase();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) throws IllegalArgumentException {
        if (salario < Double.valueOf(Constants.SALARIO_MINIMO)) {
            throw new IllegalArgumentException("Salario do Funcionario deve ser maior ou igual ao piso salarial de 937,00!");
        }
        this.salario = salario;
    }

    public int gethora_dia() {
        return hora_dia * 60;
    }

    public void sethora_dia(int hora_dia) throws IllegalArgumentException {
        if (hora_dia < 4) {
            throw new IllegalArgumentException("Funcionario deve ter pelo menos 4h como hora base para um dia de trabalho!");
        }
        this.hora_dia = hora_dia * 60;
    }

    public double getValor_hora() {
        return Double.parseDouble(String.format("%.2f", ((this.salario / 30) / (this.hora_dia / 60))).replace(",", "."));
    }
}
