/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import helpers.Dialogs;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qwerty
 */
public class FuncionarioDAO extends GenericDAO {

    public FuncionarioDAO() throws SQLException {
        super();
    }

    private void preencherObj(Funcionario obj, ResultSet rs) throws SQLException, Exception {
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setCPF(rs.getString("cpf"));
        obj.setSalario(rs.getDouble("salario"));
        obj.setHora_base(rs.getInt("hora_base"));
        obj.setValor_hora(rs.getDouble("valor_hora"));
    }

    @Override
    public void addEntity(Object obj) throws SQLException {
        String sql = "INSERT INTO FUNCIONARIO (NOME, CPF, SALARIO, HORA_BASE, HORA_EXTRA) VALUES (?,?,?,?,?)";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, ((Funcionario) obj).getNome());
        super.Sql.setString(2, ((Funcionario) obj).getCPF());
        super.Sql.setDouble(3, ((Funcionario) obj).getSalario());
        super.Sql.setInt(4, ((Funcionario) obj).getHora_base());
        super.Sql.setDouble(5, ((Funcionario) obj).getValor_hora());
        super.Sql.execute();
        super.Sql.close();
    }

    @Override
    public void removeEntity(Object obj) throws SQLException {
        String sql = "DELETE FROM FUNCIONARIO WHERE ID=? AND NOME=?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setInt(1, ((Funcionario) obj).getId());
        super.Sql.setString(2, ((Funcionario) obj).getNome());
        super.Sql.execute();
        super.Sql.close();
    }

    @Override
    public Object updateEntity(Object obj) throws SQLException {
        String sql = "UPDATE FUNCIONARIO SET NOME=?,CPF=?,SALARIO=?,HORA_BASE=?, VALOR_HORA=? WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, ((Funcionario) obj).getNome());
        super.Sql.setString(2, ((Funcionario) obj).getCPF());
        super.Sql.setDouble(3, ((Funcionario) obj).getSalario());
        super.Sql.setInt(4, ((Funcionario) obj).getHora_base());
        super.Sql.setDouble(5, ((Funcionario) obj).getValor_hora());
        super.Sql.setInt(6, ((Funcionario) obj).getId());
        super.Sql.executeUpdate();
        super.Sql.close();
        return obj;
    }

    @Override
    public Object getEntityById(int id) throws SQLException {
        Funcionario obj = null;
        String sql = "SELECT * FROM FUNCINOARIO WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        Sql.setInt(1, id);

        ResultSet rs = super.Sql.executeQuery();

        if (rs.next()) {
            obj = new Funcionario();
            try {
                preencherObj(obj, rs);
            } catch (Exception ex) {
                Dialogs.showError(ex.getMessage());
            }
        }
        rs.close();
        return obj;

    }

    @Override
    public <T> List<T> getAllEntitys() throws SQLException {
        List<Funcionario> listFunc = new ArrayList();
        String sql = "SELECT * FROM FUNCIONARIO";
        super.Sql = super.Conn.prepareStatement(sql);
        ResultSet rs = super.Sql.executeQuery();

        while (rs.next()) {
            try {
                Funcionario obj = new Funcionario();
                preencherObj(obj, rs);
                listFunc.add(obj);
            } catch (Exception ex) {
                Dialogs.showError(ex.getMessage());
            }
        }
        rs.close();
        super.Sql.close();
        return (List<T>) listFunc;

    }

    public Object SearchEntity(String name) throws SQLException {
        String sql = "SELECT * FROM FUNCIONARIO WHERE NOME = ?";
        super.Sql = super.Conn.prepareStatement(sql);
        Sql.setString(1, name.toUpperCase());
        ResultSet rs = super.Sql.executeQuery();
        Funcionario obj = null;
        if (rs.next()) {
            try {
                obj = new Funcionario();
                preencherObj(obj, rs);
            } catch (Exception ex) {
                Dialogs.showError(ex.getMessage());
            }
        }
        return obj;
    }

    public boolean isEmptyEntity() throws SQLException {
        String sql = "SELECT * FROM FUNCIONARIO LIMIT 1";
        return !super.Conn.prepareStatement(sql).executeQuery().next();
    }

}
