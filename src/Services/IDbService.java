package Services;

import java.sql.ResultSet;
import java.sql.Statement;

public interface IDbService {
    public ResultSet ExecuteQuery(String query);
}
