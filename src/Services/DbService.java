package Services;

import EnvironmentVariables.DatabaseConnectionString;

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

    public void FinalizeConnections() {
        try {
            if (this.connection != null && !this.connection.isClosed())
                this.connection.close();

            if (this.statement != null && !this.statement.isClosed())
                this.statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
