package models;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author qwerty
 */
public abstract class GenericDAO {

    protected java.sql.Connection Conn;
    protected java.sql.PreparedStatement Sql;

    public GenericDAO() throws SQLException {
        this.Conn = DB.Connect();
    }

    public abstract void addEntity(Object obj) throws SQLException;

    public abstract void removeEntity(Object obj) throws SQLException;

    public abstract Object updateEntity(Object obj) throws SQLException;

    public abstract Object getEntityById(int id) throws SQLException;

    public abstract <T> List<T> getAllEntitys() throws SQLException;

}
