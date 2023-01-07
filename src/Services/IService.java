package Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IService<T, V, W> {
    public T mapResult(ResultSet resultSet) throws SQLException;
    public ArrayList<T> getAll() throws SQLException;
    public T get(V id) throws SQLException;
    public T create(T object) throws SQLException;
    public T update(V id, W newObject) throws SQLException;
    public void delete(V id) throws SQLException;
    public void FinalizeConnection() throws SQLException;
}
