package Services;

import Contracts.UpdateProperty;
import Models.Property;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyService implements IPropertyService {
    private DbService dbService;

    public PropertyService(DbService dbService){
        this.dbService = dbService;
    }

    public Property mapResult(ResultSet resultSet) throws SQLException {
        int propertyId = resultSet.getInt(1);
        String propertyAddress = resultSet.getString(2);
        String propertyType = resultSet.getString(3);
        double propertyValue = resultSet.getDouble(4);
        double propertyArea = resultSet.getDouble(5);

        return new Property(propertyId, propertyAddress, propertyType, propertyValue, propertyArea);
    }

    public ArrayList<Property> getAll() throws SQLException{
        ArrayList<Property> response = new ArrayList<>();
        ResultSet resultSet = dbService.ExecuteQuery("SELECT * FROM property ORDER BY property_id");

        while (resultSet.next()){
            Property property = mapResult(resultSet);
            response.add(property);
        }
        return response;
    }

    public Property get(Integer id) throws SQLException{
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM property WHERE property_id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return mapResult(resultSet);
        }
        else {
            return null;
        }
    }

    public Property create(Property property) throws SQLException{
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO property VALUES(?,?,?,?)");

        preparedStatement.setString(1, property.getAddress());
        preparedStatement.setString(2, property.getType());
        preparedStatement.setDouble(3, property.getValue());
        preparedStatement.setDouble(4, property.getArea());

        preparedStatement.executeUpdate();
        return property;
    }

    public Property update(Integer id, UpdateProperty newProperty) throws SQLException{
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE property SET address = ?," +
                "property_type = ?, property_value = ?, area = ? WHERE property_id = ?");
        preparedStatement.setString(1, newProperty.address());
        preparedStatement.setString(2, newProperty.type());
        preparedStatement.setDouble(3, newProperty.area());
        preparedStatement.setDouble(4, newProperty.value());
        preparedStatement.setInt(5, id);

        preparedStatement.executeUpdate();

        return new Property(id,
                newProperty.address(),
                newProperty.type(),
                newProperty.area(),
                newProperty.value());
    }

    public void delete(Integer id) throws SQLException{
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM property WHERE property_id = ?");
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    public void FinalizeConnections(){
        this.dbService.FinalizeConnections();
    }
}
