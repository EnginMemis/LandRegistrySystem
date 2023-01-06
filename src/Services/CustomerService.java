package Services;

import Models.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class CustomerService implements ICustomerService<Customer> {
    private DbService dbService;

    public CustomerService(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> response = new ArrayList<>();

        ResultSet resultSet = dbService.ExecuteQuery("SELECT * FROM customer");

        try {
            while (resultSet.next()) {
                int ssn = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                Date birthDate = resultSet.getDate(4);
                String gender = resultSet.getString(5);
                String phoneNumber = resultSet.getString(6);
                String email = resultSet.getString(7);
                String address = resultSet.getString(8);

                response.add(new Customer(ssn, name, surname, birthDate, gender, phoneNumber, email, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return response;

    }

    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public Customer create(Customer object) {
        return null;
    }

    @Override
    public Customer update(Customer newObject) {
        return null;
    }

    @Override
    public Customer delete(int id) {
        return null;
    }

    @Override
    public void save() {

    }
}
