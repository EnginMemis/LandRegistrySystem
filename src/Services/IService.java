package Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IService<ObjectType, IdType, UpdateRecordType> {
    public ObjectType mapResult(ResultSet resultSet) throws SQLException;
    public ArrayList<ObjectType> getAll() throws SQLException;
    public ObjectType get(IdType id) throws SQLException;
    public ObjectType create(ObjectType object) throws SQLException;
    public ObjectType update(IdType id, UpdateRecordType newObject) throws SQLException;
    public void delete(IdType id) throws SQLException;
    public void FinalizeConnections();
}
