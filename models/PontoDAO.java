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

public class PontoDAO extends GenericDAO {

    public PontoDAO() throws SQLException {
        super();
    }

    private void preencherObj(Ponto obj, ResultSet rs) throws SQLException {

        obj.setId(rs.getInt("id"));
        obj.setData((java.sql.Date) rs.getDate("dia"));
        obj.setId_funcionario(rs.getInt("id_funcionario"));
        obj.setEntrada_a(rs.getInt("entrada_a"));
        obj.setSaida_a(rs.getInt("saida_a"));
        obj.setEntrada_b(rs.getInt("entrada_b"));
        obj.setSaida_b(rs.getInt("saida_b"));

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
        String sql = "DELETE FROM PONTO WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setInt(1, ((Ponto) obj).getId());
        super.Sql.execute();
        super.Sql.close();
    }

    @Override
    public Object updateEntity(Object obj) throws SQLException {
        String sql = "UPDATE REGISTRO_PONTO SET"
                + " DIA = ?, ID_FUNCIONARIO = ?, ENTRADA_A = ?, SAIDA_A = ?, ENTRADA_B = ?, SAIDA_B = ?, HORAS_EXCEDIDAS = ?, PERCENT_APLICADO = ?, VALOR_EXTRA = ?, TOTAL_RECEBIDO = ?"
                + " WHERE ID=?";
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
        super.Sql.setInt(11, ((Ponto) obj).getId());

        super.Sql.executeUpdate();
        super.Sql.close();
        return obj;
    }

    @Override
    public Object getEntityById(int id) throws SQLException {
        Ponto obj = null;
        String sql = "SELECT * FROM REGISTRO_PONTO WHERE ID=?";
        super.Sql = super.Conn.prepareStatement(sql);
        Sql.setInt(1, id);

        ResultSet rs = super.Sql.executeQuery();

        if (rs.next()) {
            obj = new Ponto();
            preencherObj(obj, rs);
        }
        rs.close();
        return obj;

    }

    @Override
    public <T> List<T> getAllEntitys() throws SQLException {
        List<Ponto> listPonto = new ArrayList();
        String sql = "SELECT * FROM REGISTRO_PONTO";
        super.Sql = super.Conn.prepareStatement(sql);
        ResultSet rs = super.Sql.executeQuery();

        while (rs.next()) {
            Ponto obj = new Ponto();
            preencherObj(obj, rs);
            listPonto.add(obj);
        }
        rs.close();
        super.Sql.close();
        return (List<T>) listPonto;

    }

    public Object SearchEntity(java.sql.Date data, int Id_Funcionario) throws SQLException {
        String sql = "SELECT * FROM REGISTRO_PONTO WHERE DIA = ? AND ID_FUNCIONARIO = ?";
        super.Sql = super.Conn.prepareStatement(sql);

        Sql.setDate(1, data);
        Sql.setInt(2, Id_Funcionario);

        ResultSet rs = super.Sql.executeQuery();
        Ponto obj = null;

        if (rs.next()) {
            obj = new Ponto();
            preencherObj(obj, rs);
        }
        return obj;
    }

    public boolean isEmptyEntity() throws SQLException {
        String sql = "SELECT * FROM REGISTRO_PONTO LIMIT 1";
        return !super.Conn.prepareStatement(sql).executeQuery().next();
    }

    public boolean DuplicatedEntity(int Id, java.sql.Date data, int id_funcionario) throws SQLException {
        String sql = "SELECT * FROM REGISTRO_PONTO WHERE ID_FUNCIONARIO  = ? AND DIA = ? AND ID <> ?";
        super.Sql = super.Conn.prepareStatement(sql);
        super.Sql.setInt(1, id_funcionario);
        super.Sql.setDate(2, data);
        super.Sql.setInt(3, Id);
        return super.Sql.executeQuery().next();
    }

}
