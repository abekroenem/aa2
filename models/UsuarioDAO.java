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
public class UsuarioDAO extends GenericDAO {

    public UsuarioDAO() throws SQLException {
        super();

    }

    private void preencherObj(Usuario obj, ResultSet rs) throws SQLException {
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("nome"));
        obj.setPassword(rs.getString("senha"));
        obj.setAdmin(rs.getBoolean("isAdmin"));
    }

    @Override
    public void addEntity(Object obj) throws SQLException {
        String sql = "INSERT INTO USUARIO (NOME, SENHA, ISADMIN) VALUES (?,?,?)";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, ((Usuario) obj).getName());
        super.Sql.setString(2, ((Usuario) obj).getPassword());
        super.Sql.setBoolean(3, ((Usuario) obj).getAdmin());
        super.Sql.execute();
        super.Sql.close();
    }

    @Override
    public void removeEntity(Object obj) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE ID=? AND NOME=?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setInt(1, ((Usuario) obj).getId());
        super.Sql.setString(2, ((Usuario) obj).getName());
        super.Sql.execute();
        super.Sql.close();
    }

    @Override
    public Object updateEntity(Object obj) throws SQLException {
        String sql = "UPDATE USUARIO SET NOME=?,SENHA=?,ISADMIN=? WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, ((Usuario) obj).getName());
        super.Sql.setString(2, ((Usuario) obj).getPassword());
        super.Sql.setBoolean(3, ((Usuario) obj).getAdmin());
        super.Sql.setInt(4, ((Usuario) obj).getId());
        super.Sql.executeUpdate();
        super.Sql.close();
        return obj;
    }

    @Override
    public Object getEntityById(int id) throws SQLException {
        Usuario obj = null;
        String sql = "SELECT * FROM USUARIO WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        Sql.setInt(1, id);

        ResultSet rs = super.Sql.executeQuery();

        if (rs.next()) {
            obj = new Usuario();
            preencherObj(obj, rs);
        }
        rs.close();
        return obj;

    }

    @Override
    public <T> List<T> getAllEntitys() throws SQLException {
        List<Usuario> listUser = new ArrayList();
        String sql = "SELECT * FROM USUARIO";
        super.Sql = super.Conn.prepareStatement(sql);
        ResultSet rs = super.Sql.executeQuery();

        while (rs.next()) {
            Usuario obj = new Usuario();
            preencherObj(obj, rs);
            listUser.add(obj);
        }
        rs.close();
        super.Sql.close();
        return (List<T>) listUser;

    }

    public Object SearchEntity(String name) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE NOME = ?";
        super.Sql = super.Conn.prepareStatement(sql);
        Sql.setString(1, name.toUpperCase());
        ResultSet rs = super.Sql.executeQuery();
        Usuario obj = null;

        if (rs.next()) {
            obj = new Usuario();
            preencherObj(obj, rs);
        }
        return obj;
    }

    public boolean isEmptyEntity() throws SQLException {
        String sql = "SELECT * FROM USUARIO LIMIT 1";
        return !super.Conn.prepareStatement(sql).executeQuery().next();
    }

    public boolean DuplicatedEntity(int Id, String name) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE NOME  = ? AND ID <> ?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, name.toUpperCase());
        super.Sql.setInt(2, Id);
        return super.Sql.executeQuery().next();
    }

}
