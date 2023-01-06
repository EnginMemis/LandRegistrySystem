package Services;

import Models.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerService implements ICustomerService {
    private DbService dbService;

    public CustomerService(DbService dbService) {
        this.dbService = dbService;
    }

    public Customer mapResult(ResultSet resultSet) throws SQLException {
        int ssn = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        Date birthDate = resultSet.getDate(4);
        String gender = resultSet.getString(5);
        String phoneNumber = resultSet.getString(6);
        String email = resultSet.getString(7);
        String address = resultSet.getString(8);

        return new Customer(ssn, name, surname, birthDate, gender, phoneNumber, email, address);
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> response = new ArrayList<>();

        ResultSet resultSet = dbService.ExecuteQuery("SELECT * FROM customer");

        while (resultSet.next()) {
            Customer customer = mapResult(resultSet);
            response.add(customer);
        }

        return response;
    }

    @Override
    public Customer get(int id) throws SQLException {
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM customer WHERE ssn = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return mapResult(resultSet);
        } else {
            return null;
        }
    }

    @Override
    public Customer create(Customer customer) throws SQLException {
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?)");

        preparedStatement.setInt(1, customer.getSsn());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getSurname());
        preparedStatement.setDate(4, customer.getBirthDate());
        preparedStatement.setString(5, customer.getGender());
        preparedStatement.setString(6, customer.getPhoneNumber());
        preparedStatement.setString(7, customer.getCustomerEmail());
        preparedStatement.setString(8, customer.getCustomerAddress());

        preparedStatement.executeUpdate();

        return customer;
    }

    @Override
    public Customer update(Customer newCustomer) throws SQLException {
        return null;
    }

    @Override
    public Customer delete(int id) throws SQLException {
        return null;
    }

    @Override
    public void FinalizeConnection() throws SQLException {
        this.dbService.FinalizeConnections();
    }
}
