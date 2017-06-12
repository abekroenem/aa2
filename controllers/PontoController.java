/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.SQLException;
import java.util.List;
import models.PontoDAO;
import models.Ponto;

/**
 *
 * @author qwerty
 */
public class PontoController {

    PontoDAO pntDAO;

    public PontoController() throws SQLException {
        pntDAO = new PontoDAO();

    }

    public void Add(java.sql.Date data, int id_funcionario, int entrada_a, int saida_a, int entrada_b, int saida_b) throws Exception {
        Ponto obj = new Ponto();
        obj.setData(data);
        obj.setId_funcionario(id_funcionario);
        obj.setEntrada_a(entrada_a);
        obj.setSaida_a(saida_a);
        obj.setEntrada_b(entrada_b);
        obj.setSaida_b(saida_a);
        pntDAO.addEntity(obj);
    }

    public void Edit(int Id, java.sql.Date data, int id_funcionario, int entrada_a, int saida_a, int entrada_b, int saida_b) throws Exception {
        Ponto obj = new Ponto();
        obj.setId(Id);
        obj.setData(data);
        obj.setId_funcionario(id_funcionario);
        obj.setEntrada_a(entrada_a);
        obj.setSaida_a(saida_a);
        obj.setEntrada_b(entrada_b);
        obj.setSaida_b(saida_a);
        pntDAO.updateEntity(obj);
    }

    public void Delete(int Id, java.sql.Date data, int id_funcionario) throws SQLException {
        Ponto obj = new Ponto();
        obj.setId(Id);
        obj.setData(data);
        obj.setId_funcionario(id_funcionario);
        pntDAO.removeEntity(obj);

    }

    public List<Ponto> getAll() throws SQLException {
        return pntDAO.getAllEntitys();
    }

    public Ponto getByID(int id) throws SQLException {
        return (Ponto) pntDAO.getEntityById(id);
    }

    public Ponto SearchUserByFunDay(java.sql.Date data, int id_funcionario) throws SQLException {
        return (Ponto) pntDAO.SearchEntity(data, id_funcionario);
    }

    public boolean DuplicatedPonto(int ID, java.sql.Date data, int id_funcionario) throws SQLException {
        return pntDAO.DuplicatedEntity(ID, data, id_funcionario);
    }

    public boolean isEmpty() throws SQLException {
        return pntDAO.isEmptyEntity();
    }

}
