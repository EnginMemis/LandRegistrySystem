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
        ResultSet resultSet = dbService.ExecuteQuery("SELECT * FROM property");

        while (resultSet.next()){
            Property property = mapResult(resultSet);
            response.add(property);
        }
        return response;
    }

    public Property get(Integer id) throws SQLException{
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM property WHERE property_id = ?");
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
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO property VALUES(?,?,?,?,?)");
        preparedStatement.setInt(1, property.getPropertyId());
        preparedStatement.setString(2, property.getPropertyAddress());
        preparedStatement.setString(3, property.getPropertyType());
        preparedStatement.setDouble(4, property.getPropertyValue());
        preparedStatement.setDouble(5, property.getPropertyArea());

        preparedStatement.executeUpdate();
        return property;
    }

    public Property update(Integer id, UpdateProperty newProperty) throws SQLException{
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE property SET address = ?," +
                "property_type = ?, property_value = ?, area = ? WHERE property_id = ?");
        preparedStatement.setString(1, newProperty.propertyAddress());
        preparedStatement.setString(2, newProperty.propertyType());
        preparedStatement.setDouble(3, newProperty.propertyArea());
        preparedStatement.setDouble(4, newProperty.propertyValue());
        preparedStatement.setInt(5, id);

        preparedStatement.executeUpdate();

        return new Property(id,
                newProperty.propertyAddress(),
                newProperty.propertyType(),
                newProperty.propertyArea(),
                newProperty.propertyValue());
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
