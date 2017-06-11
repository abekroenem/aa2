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
public class PontoDAO extends GenericDAO {

    public PontoDAO() throws SQLException {
        super();

    }

    private void preencherObj(Usuario obj, ResultSet rs) throws SQLException {
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("dia"));
        obj.setPassword(rs.getString("id_funcionario"));
        obj.setAdmin(rs.getBoolean("entrada_a"));
        obj.setAdmin(rs.getBoolean("saida_b"));
        obj.setAdmin(rs.getBoolean("entrada_b"));
        obj.setAdmin(rs.getBoolean("saida_b"));
        obj.setAdmin(rs.getBoolean(""));
        obj.setAdmin(rs.getBoolean("entrada_a"));
    }

    @Override
    public void addEntity(Object obj) throws SQLException {
        String sql = "INSERT INTO REGISTRO_PONTO "
                + "(DIA, ID_FUNCIONARIO, ENTRADA_A, SAIDA_A, ENTRADA_B, SAIDA_B, HORAS_EXCEDIDAS, PERCENT_APLICADO, VALOR_EXTRA, TOTAL_RECEBIDO)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?)";

        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setDate(1, ((Ponto) obj).getData());
        super.Sql.setInt(2, ((Ponto) obj).getId_funcionario());
        super.Sql.setInt(3, ((Ponto) obj).getEntrada_a());
        super.Sql.setInt(4, ((Ponto) obj).getSaida_a());
        super.Sql.setInt(5, ((Ponto) obj).getEntrada_b());
        super.Sql.setInt(6, ((Ponto) obj).getSaida_b());
        super.Sql.setInt(7, ((Ponto) obj).getHoras_excedidas());
        super.Sql.setDouble(8, ((Ponto) obj).getPercent_aplicado());
        super.Sql.setDouble(9, ((Ponto) obj).getValor_extra());
        super.Sql.setDouble(10, ((Ponto) obj).getTotal_recebido());

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
