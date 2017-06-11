/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.SQLException;
import java.util.List;
import models.Funcionario;
import models.FuncionarioDAO;

/**
 *
 * @author qwerty
 */
public class FuncionarioController {

    FuncionarioDAO funcDAO;

    public FuncionarioController() throws SQLException {
        funcDAO = new FuncionarioDAO();
    }

    public void Add(String Nome, String CFP, double Salario, int hora_dia) throws Exception {
        Funcionario obj = new Funcionario();
        obj.setNome(Nome);
        obj.setCPF(CFP);
        obj.setSalario(Salario);
        obj.sethora_dia(hora_dia);
        funcDAO.addEntity(obj);
    }

    public void Edit(int Id, String Nome, String CFP, double Salario, int hora_dia) throws Exception {
        Funcionario obj = new Funcionario();
        obj.setId(Id);
        obj.setNome(Nome);
        obj.setCPF(CFP);
        obj.setSalario(Salario);
        obj.sethora_dia(hora_dia);
        funcDAO.updateEntity(obj);
    }

    public void Delete(int Id, String Nome) throws Exception {
        Funcionario obj = new Funcionario();
        obj.setId(Id);
        obj.setNome(Nome);
        funcDAO.removeEntity(obj);

    }

    public List<Funcionario> getAll() throws SQLException {
        return funcDAO.getAllEntitys();
    }

    public Funcionario SearchFuncionarioByCPF(String CPF) throws SQLException {
        return (Funcionario) funcDAO.SearchEntity(CPF);
    }

    public boolean DuplicatedFuncionario(int ID, String CPF) throws Exception {
        return funcDAO.DuplicatedEntity(ID, CPF);
    }

    public Funcionario getByID(int id) throws SQLException {
        return (Funcionario) funcDAO.getEntityById(id);
    }
}
