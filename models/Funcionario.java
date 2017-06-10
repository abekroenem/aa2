/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Env.Constants;

/**
 *
 * @author qwerty
 */
public class Funcionario {

    private int Id;
    private String Nome;
    private double salario;
    private int hora_base;
    private double valor_hora;
    private String CPF;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) throws Exception {
        if (CPF.isEmpty()) {
            throw new Exception("CPF do Funcionario deve ser informado!");
        }
        this.CPF = CPF;
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

    public void setNome(String Nome) throws Exception {
        if (Nome.isEmpty()) {
            throw new Exception("Nome do Funcionario deve ser preencido!");
        }
        this.Nome = Nome.toUpperCase();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) throws Exception {
        if (salario < Constants.SALARIO_MINIMO) {
            throw new Exception("Salario do Funcionario deve ser maior ou igual ao piso salarial de 937,00!");
        }
        this.salario = salario;
    }

    public int getHora_base() {
        return hora_base;
    }

    public void setHora_base(int hora_base) throws Exception {
        if (hora_base < 60) {
            throw new Exception("Funcionario deve ter pelo menos 1h como hora base!");
        }
        this.hora_base = hora_base;
    }

    public double getValor_hora() {
        return valor_hora;
    }

    public void setValor_hora(double valor_hora) throws Exception {
        if (valor_hora <= 0) {
            throw new Exception("Valor/Hora do Funcionario deve ser maior que zero!");

        }
        this.valor_hora = valor_hora;
    }

}
