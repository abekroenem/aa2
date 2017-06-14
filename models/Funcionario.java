/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Env.Constants;
import helpers.Config;
import helpers.Formats;
import java.util.ResourceBundle;

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
    
    private String cpf_informado, nome_func, salario_func, hora_func;
    
    private void Traduz() {
        ResourceBundle rbl = null;
        rbl = Config.getResources();
        cpf_informado = rbl.getString("cpf_func");
        nome_func = rbl.getString("nome_func");
        salario_func = rbl.getString("salario_func");
        hora_func = rbl.getString("hora_func");
    }
    
    public Funcionario() {
        Traduz();
    }
    
    public String getCPF() {
        return CPF;
    }
    
    public void setCPF(String CPF) throws IllegalArgumentException {
        if (CPF.isEmpty()) {
            throw new IllegalArgumentException(cpf_informado);
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
            throw new IllegalArgumentException(nome_func);
        }
        this.Nome = Nome.toUpperCase();
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) throws IllegalArgumentException {
        if (salario < Double.valueOf(Constants.SALARIO_MINIMO)) {
            throw new IllegalArgumentException(salario_func);
        }
        this.salario = salario;
    }
    
    public int gethora_dia() {
        return hora_dia / 60;
    }
    
    public void sethora_dia(int hora_dia) throws IllegalArgumentException {
        if (hora_dia < 4) {
            throw new IllegalArgumentException(hora_func);
        }
        this.hora_dia = hora_dia * 60;
    }
    
    public double getValor_hora() {
        double valor_hr = this.salario / Env.Constants.getJornada(this.gethora_dia());
        return Formats.Decimal.Format(valor_hr);
    }
}
