package Services;

import Contracts.UpdatePropertyFeature;
import Models.Property;
import Models.PropertyFeature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyFeatureService implements IPropertyFeatureService {
    private DbService dbService;

    public PropertyFeatureService(DbService dbService) {
        this.dbService = dbService;
    }
    @Override
    public PropertyFeature mapResult(ResultSet resultSet) throws SQLException {
        int featureId = resultSet.getInt(1);
        int propertyId = resultSet.getInt(2);
        String title = resultSet.getString(3);
        String value = resultSet.getString(4);
        return new PropertyFeature(featureId, propertyId, title, value);
    }

    @Override
    public ArrayList<PropertyFeature> getAll() throws SQLException {
        ArrayList<PropertyFeature> response = new ArrayList<>();

        ResultSet resultSet = dbService.ExecuteQuery("SELECT * FROM property_feature");
        while (resultSet.next()){
            PropertyFeature propertyFeature = mapResult(resultSet);
            response.add(propertyFeature);
        }

        return response;
    }

    @Override
    public PropertyFeature get(Integer id) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM property_feature WHERE feature_id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return mapResult(resultSet);
        } else {
            return null;
        }
    }

    @Override
    public PropertyFeature create(PropertyFeature propertyFeature) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO property_feature VALUES (?,?,?,?)");

        preparedStatement.setInt(1, propertyFeature.getFeatureId());
        preparedStatement.setInt(2, propertyFeature.getPropertyId());
        preparedStatement.setString(3, propertyFeature.getFeatureTitle());
        preparedStatement.setString(4, propertyFeature.getFeatureValue());

        preparedStatement.executeUpdate();
        return propertyFeature;
    }

    @Override
    public PropertyFeature update(Integer id, UpdatePropertyFeature propertyFeature) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE property_feature SET property_id = ?," +
                        " title = ?, value = ? WHERE feature_id = ?");
        preparedStatement.setInt(1, propertyFeature.property_id());
        preparedStatement.setString(2, propertyFeature.title());
        preparedStatement.setString(3, propertyFeature.value());
        preparedStatement.setInt(4, id);

        preparedStatement.executeUpdate();
        return new PropertyFeature(id,
                propertyFeature.property_id(),
                propertyFeature.title(),
                propertyFeature.value());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM property_feature WHERE feature_id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void FinalizeConnections() {
        this.dbService.FinalizeConnections();
    }
}
