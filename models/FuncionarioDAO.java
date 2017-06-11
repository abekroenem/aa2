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

/**
 *
 * @author qwerty
 */
public class FuncionarioDAO extends GenericDAO {

    public FuncionarioDAO() throws SQLException {
        super();
    }

    private void preencherObj(Funcionario obj, ResultSet rs) throws SQLException {
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setCPF(rs.getString("cpf"));
        obj.setSalario(rs.getDouble("salario"));
        obj.sethora_dia(rs.getInt("hora_dia"));
    }

    @Override
    public void addEntity(Object obj) throws SQLException {
        String sql = "INSERT INTO FUNCIONARIO (NOME, CPF, SALARIO, HORA_DIA, VALOR_HORA) VALUES (?,?,?,?,?)";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, ((Funcionario) obj).getNome());
        super.Sql.setString(2, ((Funcionario) obj).getCPF());
        super.Sql.setDouble(3, ((Funcionario) obj).getSalario());
        super.Sql.setInt(4, ((Funcionario) obj).gethora_dia());
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
        String sql = "UPDATE FUNCIONARIO SET NOME=?,CPF=?,SALARIO=?,HORA_DIA=?,VALOR_HORA=? WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, ((Funcionario) obj).getNome());
        super.Sql.setString(2, ((Funcionario) obj).getCPF());
        super.Sql.setDouble(3, ((Funcionario) obj).getSalario());
        super.Sql.setInt(4, ((Funcionario) obj).gethora_dia());
        super.Sql.setDouble(5, ((Funcionario) obj).getValor_hora());
        super.Sql.setInt(6, ((Funcionario) obj).getId());
        super.Sql.executeUpdate();
        super.Sql.close();
        return obj;
    }

    @Override
    public Object getEntityById(int id) throws SQLException {
        Funcionario obj = null;
        String sql = "SELECT * FROM FUNCIONARIO WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        Sql.setInt(1, id);

        ResultSet rs = super.Sql.executeQuery();

        if (rs.next()) {
            obj = new Funcionario();
            preencherObj(obj, rs);
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

    public Object SearchEntity(String CFP) throws SQLException {
        String sql = "SELECT * FROM FUNCIONARIO WHERE CPF = ?";
        super.Sql = super.Conn.prepareStatement(sql);
        Sql.setString(1, CFP);
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

    public boolean DuplicatedEntity(int Id, String CPF) throws SQLException {
        String sql = "SELECT * FROM FUNCIONARIO WHERE CPF  = ? AND ID <> ?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setString(1, CPF);
        super.Sql.setInt(2, Id);
        return super.Sql.executeQuery().next();
    }
}
