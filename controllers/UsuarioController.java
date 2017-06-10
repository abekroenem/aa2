/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.SQLException;
import java.util.List;
import models.Usuario;
import models.UsuarioDAO;

/**
 *
 * @author qwerty
 */
public class UsuarioController {

    UsuarioDAO usrDAO;

    public UsuarioController() throws SQLException {
        usrDAO = new UsuarioDAO();

    }

    public void Add(String Name, String Password, boolean isAdmin) throws SQLException {
        Usuario obj = new Usuario();
        obj.setName(Name);
        obj.setPassword(Password);
        obj.setAdmin(isAdmin);
        usrDAO.addEntity(obj);
    }

    public void Edit(int Id, String Name, String Password, boolean isAdmin) throws SQLException {
        Usuario obj = new Usuario();
        obj.setId(Id);
        obj.setName(Name);
        obj.setPassword(Password);
        obj.setAdmin(isAdmin);
        usrDAO.updateEntity(obj);
    }

    public void Delete(int Id, String Name) throws SQLException {
        Usuario obj = new Usuario();
        obj.setId(Id);
        obj.setName(Name);
        usrDAO.removeEntity(obj);

    }

    public List<Usuario> getAll() throws SQLException {
        return usrDAO.getAllEntitys();
    }

    public Usuario SearchUser(String name) throws SQLException {

        return (Usuario) usrDAO.SearchEntity(name);

    }

    public boolean isEmpty() throws SQLException {
        return usrDAO.isEmptyEntity();
    }

}
