package Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IService<T> {
    public T mapResult(ResultSet resultSet) throws SQLException;
    public ArrayList<T> getAll() throws SQLException;
    public T get(int id) throws SQLException;
    public T create(T object) throws SQLException;
    public T update(T newObject) throws SQLException;
    public T delete(int id) throws SQLException;
    public void FinalizeConnection() throws SQLException;
}
