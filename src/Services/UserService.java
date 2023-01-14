package Services;

import Contracts.UpdateUser;
import Models.User;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

public class UserService implements IUserService {
    private DbService dbService;

    public UserService(DbService dbService) {
        this.dbService = dbService;
    }

    public User mapResult(ResultSet resultSet) throws SQLException {
        int ssn = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        Date birthDate = resultSet.getDate(4);
        String gender = resultSet.getString(5);
        String phoneNumber = resultSet.getString(6);
        String email = resultSet.getString(7);
        String address = resultSet.getString(8);
        double wallet = resultSet.getDouble(9);
        String role = resultSet.getString(10);

        return new User(ssn, name, surname, birthDate, gender, phoneNumber, email, address, wallet, role);
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> response = new ArrayList<>();

        ResultSet resultSet = dbService.ExecuteQuery("SELECT * FROM users ORDER BY ssn");

        while (resultSet.next()) {
            User user = mapResult(resultSet);
            response.add(user);
        }

        return response;
    }

    @Override
    public User get(Integer id) throws SQLException {
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM users WHERE ssn = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return mapResult(resultSet);
        } else {
            return null;
        }
    }

    @Override
    public User create(User user) throws SQLException {
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO users (fname, lname, birth_date, " +
                        "gender, phone_number, email, address, user_role, wallet) VALUES (?,?,?,?,?,?,?,?, 0)");

        preparedStatement.setString(1, user.getFname());
        preparedStatement.setString(2, user.getLname());
        preparedStatement.setDate(3, user.getBirthDate());
        preparedStatement.setString(4, user.getGender());
        preparedStatement.setString(5, user.getPhoneNumber());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setString(7, user.getAddress());
        preparedStatement.setString(8, user.getRole());

        preparedStatement.executeUpdate();

        return user;
    }

    @Override
    public User update(Integer ssn, UpdateUser newUser) throws SQLException {
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE users SET fname = ?, lname = ?, " +
                        "birth_date = ?, gender = ?, phone_number = ?, email = ?, address = ?," +
                        "wallet = ?, user_role = ? WHERE ssn = ?");

        preparedStatement.setString(1, newUser.fname());
        preparedStatement.setString(2, newUser.lname());
        preparedStatement.setDate(3, newUser.birthDate());
        preparedStatement.setString(4, newUser.gender());
        preparedStatement.setString(5, newUser.phoneNumber());
        preparedStatement.setString(6, newUser.email());
        preparedStatement.setString(7, newUser.address());
        preparedStatement.setDouble(8, newUser.wallet());
        preparedStatement.setString(9, newUser.role());

        preparedStatement.setInt(10, ssn);

        preparedStatement.executeUpdate();

        return new User(ssn,
                newUser.fname(),
                newUser.lname(),
                newUser.birthDate(),
                newUser.gender(),
                newUser.phoneNumber(),
                newUser.email(),
                newUser.address(),
                newUser.wallet(),
                newUser.role());
    }

    @Override
    public void delete(Integer ssn) throws SQLException {
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM users WHERE ssn = ?");
        preparedStatement.setInt(1, ssn);

        preparedStatement.executeUpdate();
    }

    @Override
    public void FinalizeConnections() {
        this.dbService.FinalizeConnections();
    }
}
