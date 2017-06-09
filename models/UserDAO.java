/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qwerty
 */
public class UserDAO extends GenericDAO {

    public UserDAO() throws SQLException {
        super();

    }

    @Override
    public void addEntity(Object obj) throws SQLException {
        String sql = "INSERT INTO USERS (NAME, PASSWORD) VALUES (?,?)";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, ((User) obj).getName());
        super.Sql.setString(2, ((User) obj).getPassword());
        super.Sql.execute();
        super.Sql.close();
    }

    @Override
    public void removeEntity(Object obj) throws SQLException {
        String sql = "DELETE FROM USERS WHERE ID=? AND NAME=?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setInt(1, ((User) obj).getId());
        super.Sql.setString(2, ((User) obj).getName());
        super.Sql.execute();
        super.Sql.close();
    }

    @Override
    public Object updateEntity(Object obj) throws SQLException {
        String sql = "UPDATE USERS SET NAME=?,PASSWORD=? WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, ((User) obj).getName());
        super.Sql.setString(2, ((User) obj).getPassword());
        super.Sql.setInt(3, ((User) obj).getId());
        super.Sql.executeUpdate();
        super.Sql.close();
        return obj;
    }

    @Override
    public Object getEntityById(int id) throws SQLException {
        User obj = null;
        String sql = "SELECT * FROM USERS WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        Sql.setInt(1, id);

        ResultSet rs = super.Sql.executeQuery();

        if (rs.next()) {
            obj = new User();
            obj.setId(rs.getInt("id"));
            obj.setName(rs.getString("name"));
            obj.setPassword(rs.getString("password"));
        }
        rs.close();
        return obj;

    }

    @Override
    public <T> List<T> getAllEntitys() throws SQLException {
        List<User> listUser = new ArrayList();
        String sql = "SELECT * FROM USERS";
        super.Sql = super.Conn.prepareStatement(sql);
        ResultSet rs = super.Sql.executeQuery();

        while (rs.next()) {
            User obj = new User();
            obj.setId(rs.getInt("id"));
            obj.setName(rs.getString("name"));
            obj.setPassword(rs.getString("password"));
            listUser.add(obj);
        }
        rs.close();
        super.Sql.close();
        return (List<T>) listUser;

    }

    public Object SearchEntity(String name) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE NAME = ?";
        super.Sql = super.Conn.prepareStatement(sql);
        Sql.setString(1, name);
        ResultSet rs = super.Sql.executeQuery();
        User obj = null;

        if (rs.next()) {
            obj = new User();
            obj.setId(rs.getInt("id"));
            obj.setName(rs.getString("Name"));
            obj.setPassword(rs.getString("password"));
        }
        return obj;
    }

    public boolean isEmptyEntity() throws SQLException {
        String sql = "SELECT * FROM USERS LIMIT 1";
        return !super.Conn.prepareStatement(sql).executeQuery().next();
    }

}
