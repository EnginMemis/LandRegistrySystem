package Services;

import Constants.DatabaseConnectionString;

import java.sql.*;

public class DbService implements IDbService {
    private Connection connection;
    private Statement statement;

    public DbService() {
        try {
            this.connection = DriverManager.getConnection(DatabaseConnectionString.DatabaseConnectionString,
                    DatabaseConnectionString.DatabaseUsername,
                    DatabaseConnectionString.DatabasePassword
            );

            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet ExecuteQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
