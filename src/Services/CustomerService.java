package Services;

import Models.Customer;

import java.sql.*;
import java.sql.Date;
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
    public Customer get(Integer id) throws SQLException {
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
    public Customer update(Integer ssn, Customer newCustomer) throws SQLException {
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE customer SET name = ?, surname = ?, " +
                        "birth_date = ?, gender = ?, phone_number = ?, email = ?, address = ? WHERE ssn = ?");

        preparedStatement.setString(1, newCustomer.getName());
        preparedStatement.setString(2, newCustomer.getSurname());
        preparedStatement.setDate(3, newCustomer.getBirthDate());
        preparedStatement.setString(4, newCustomer.getGender());
        preparedStatement.setString(5, newCustomer.getPhoneNumber());
        preparedStatement.setString(6, newCustomer.getCustomerEmail());
        preparedStatement.setString(7, newCustomer.getCustomerAddress());

        preparedStatement.setInt(8, ssn);

        preparedStatement.executeUpdate();

        return newCustomer;
    }

    @Override
    public void delete(Integer ssn) throws SQLException {
        Connection connection = this.dbService.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM customer WHERE ssn = ?");
        preparedStatement.setInt(1, ssn);

        preparedStatement.executeUpdate();
    }

    @Override
    public void FinalizeConnection() throws SQLException {
        this.dbService.FinalizeConnections();
    }
}
