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

    FuncionarioDAO usrDAO;

    public FuncionarioController() throws SQLException {
        usrDAO = new FuncionarioDAO();

    }

    public void Add(String Nome, String CFP, double Salario, int Hora_Base, double Valor_Hora) throws Exception {
        Funcionario obj = new Funcionario();
        obj.setNome(Nome);
        obj.setCPF(CFP);
        obj.setHora_base(Hora_Base);
        obj.setValor_hora(Valor_Hora);
        usrDAO.addEntity(obj);
    }

    public void Edit(int Id, String Nome, String CFP, double Salario, int Hora_Base, double Valor_Hora) throws Exception {
        Funcionario obj = new Funcionario();
        obj.setNome(Nome);
        obj.setCPF(CFP);
        obj.setHora_base(Hora_Base);
        obj.setValor_hora(Valor_Hora);
        usrDAO.updateEntity(obj);
    }

    public void Delete(int Id, String Nome) throws Exception {
        Funcionario obj = new Funcionario();
        obj.setId(Id);
        obj.setNome(Nome);
        usrDAO.removeEntity(obj);

    }

    public List<Funcionario> getAll() throws SQLException {
        return usrDAO.getAllEntitys();
    }

    public Funcionario SearchUser(String name) throws SQLException {

        return (Funcionario) usrDAO.SearchEntity(name);

    }

    public boolean isEmpty() throws SQLException {
        return usrDAO.isEmptyEntity();
    }
}
