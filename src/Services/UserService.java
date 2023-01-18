package Services;

import Contracts.UpdateUser;
import Models.User;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

public class UserService implements IUserService {
    private final DbService dbService;

    public UserService(DbService dbService) {
        this.dbService = dbService;
    }

    public User mapResult(ResultSet resultSet) throws SQLException {
        int ssn = resultSet.getInt("ssn");
        String name = resultSet.getString("fname");
        String surname = resultSet.getString("lname");
        Date birthDate = resultSet.getDate("birth_date");
        String gender = resultSet.getString("gender");
        String phoneNumber = resultSet.getString("phone_number");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        double wallet = resultSet.getDouble("wallet");
        String role = resultSet.getString("user_role");

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

    public Integer getRichestUser(String address) throws SQLException{
        Connection connection = this.dbService.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{? = call max_user_land(?)}");
        callableStatement.setString(2, address);
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();
        return callableStatement.getInt(1);
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

    public void insertUser(User user) throws SQLException{
        Connection connection = this.dbService.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call insertUser(?,?,?,?,?,?,?,?)}");
        callableStatement.setString(1, user.getFname());
        callableStatement.setString(2, user.getLname());
        callableStatement.setDate(3, user.getBirthDate());
        callableStatement.setString(4, user.getGender());
        callableStatement.setString(5, user.getPhoneNumber());
        callableStatement.setString(6, user.getEmail());
        callableStatement.setString(7, user.getAddress());
        callableStatement.setString(8, user.getRole());

        callableStatement.execute();
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

    public void updateBalance(Integer ssn, Integer balance) throws SQLException{
        Connection connection = this.dbService.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call update_balance(?,?)}");
        callableStatement.setInt(1, ssn);
        callableStatement.setInt(2, balance);

        callableStatement.execute();
    }

    public ArrayList<User> listCustomer() throws SQLException{
        ArrayList<User> list = new ArrayList<>();
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM users EXCEPT (SELECT * FROM users WHERE user_role = 'Employee')");

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            User user = mapResult(resultSet);
            list.add(user);
        }
        return list;
    }
    public ArrayList<User> listEmployee() throws SQLException{
        ArrayList<User> list = new ArrayList<>();
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM users EXCEPT (SELECT * FROM users WHERE user_role = 'Customer')");

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            User user = mapResult(resultSet);
            list.add(user);
        }
        return list;
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
