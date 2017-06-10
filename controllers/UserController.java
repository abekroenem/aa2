/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.SQLException;
import java.util.List;
import models.User;
import models.UserDAO;

/**
 *
 * @author qwerty
 */
public class UserController {

    UserDAO usrDAO;

    public UserController() throws SQLException {
        usrDAO = new UserDAO();

    }

    public void Add(String Name, String Password, boolean isAdmin) throws SQLException {
        User obj = new User();
        obj.setName(Name);
        obj.setPassword(Password);
        obj.setAdmin(isAdmin);
        usrDAO.addEntity(obj);
    }

    public void Edit(int Id, String Name, String Password, boolean isAdmin) throws SQLException {
        User obj = new User();
        obj.setId(Id);
        obj.setName(Name);
        obj.setPassword(Password);
        obj.setAdmin(isAdmin);
        usrDAO.updateEntity(obj);
    }

    public void Delete(int Id, String Name) throws SQLException {
        User obj = new User();
        obj.setId(Id);
        obj.setName(Name);
        usrDAO.removeEntity(obj);

    }

    public List<User> getAll() throws SQLException {
        return usrDAO.getAllEntitys();
    }

    public User SearchUser(String name) throws SQLException {

        return (User) usrDAO.SearchEntity(name);

    }

    public boolean isEmpty() throws SQLException {
        return usrDAO.isEmptyEntity();
    }

}
