package Services;

import Contracts.UpdateLandRegistry;
import Models.LandRegistry;
import Models.Property;

import java.sql.*;
import java.util.ArrayList;

public class LandRegistryService implements ILandRegistryService {
    private DbService dbService;

    public LandRegistryService(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public LandRegistry mapResult(ResultSet resultSet) throws SQLException {
        Integer landRegistryId = resultSet.getInt(1);
        Integer propertyId = resultSet.getInt(2);
        double price = resultSet.getDouble(3);
        Date date = resultSet.getDate(4);
        String propertyAddress = resultSet.getString(5);
        String propertyType = resultSet.getString(6);
        double propertyValue = resultSet.getDouble(7);
        double propertyArea = resultSet.getDouble(8);

        return new LandRegistry(landRegistryId,
                propertyId,
                new Property(
                        propertyId,
                        propertyAddress,
                        propertyType,
                        propertyValue,
                        propertyArea),
                price,
                date);
    }

    @Override
    public ArrayList<LandRegistry> getAll() throws SQLException {
        ArrayList<LandRegistry> response = new ArrayList<>();
        ResultSet resultSet = dbService.ExecuteQuery("SELECT land_registry_id, land_registry.property_id, " +
                "land_registry.price, land_registry.date, address, property_type, property_value, area " +
                "FROM land_registry INNER JOIN property ON land_registry.property_id = property.property_id");

        while (resultSet.next()) {
            LandRegistry landRegistry = mapResult(resultSet);
            response.add(landRegistry);
        }

        return response;
    }

    @Override
    public LandRegistry get(Integer id) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT land_registry_id, land_registry.property_id, " +
                        "land_registry.price, land_registry.date, address, property_type, property_value, area " +
                        "FROM land_registry INNER JOIN property ON land_registry.property_id = property.property_id" +
                        " WHERE land_registry_id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return mapResult(resultSet);
        } else {
            return null;
        }
    }

    @Override
    public LandRegistry create(LandRegistry landRegistry) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO land_registry" +
                " VALUES(?,?,?,?)");

        preparedStatement.setInt(1, landRegistry.getLandRegistryId());
        preparedStatement.setInt(2, landRegistry.getPropertyId());
        preparedStatement.setDouble(3, landRegistry.getPrice());
        preparedStatement.setDate(4, landRegistry.getDate());

        preparedStatement.executeUpdate();

        return landRegistry;
    }

    @Override
    public LandRegistry update(Integer id, UpdateLandRegistry newLandRegistry) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE land_registry" +
                " SET property_id = ?, price = ?, date = ? WHERE property_id = ?");
        preparedStatement.setInt(1, newLandRegistry.propertyId());
        preparedStatement.setDouble(2, newLandRegistry.price());
        preparedStatement.setDate(3, newLandRegistry.date());
        preparedStatement.setInt(4, id);

        preparedStatement.executeUpdate();

        return new LandRegistry(id, newLandRegistry.propertyId(), newLandRegistry.price(), newLandRegistry.date());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM land_registry " +
                "WHERE land_registry_id = ?");
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    @Override
    public void FinalizeConnections() {
        this.dbService.FinalizeConnections();
    }
}
