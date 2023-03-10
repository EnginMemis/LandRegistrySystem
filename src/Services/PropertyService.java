package Services;

import Contracts.UpdateProperty;
import Models.Property;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyService implements IPropertyService {
    private final DbService dbService;

    public PropertyService(DbService dbService){
        this.dbService = dbService;
    }

    public Property mapResult(ResultSet resultSet) throws SQLException {
        int propertyId = resultSet.getInt("property_id");
        String propertyAddress = resultSet.getString("address");
        String propertyType = resultSet.getString("property_type");
        Integer propertyValue = resultSet.getInt("property_value");
        double propertyArea = resultSet.getDouble("area");

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
        preparedStatement.setInt(3, property.getValue());
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
        preparedStatement.setInt(4, newProperty.value());
        preparedStatement.setInt(5, id);

        preparedStatement.executeUpdate();

        return new Property(id,
                newProperty.address(),
                newProperty.type(),
                newProperty.value(),
                newProperty.area());
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
