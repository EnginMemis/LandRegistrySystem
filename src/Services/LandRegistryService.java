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
        Integer buyerSsn = resultSet.getInt(2);
        Integer sellerSsn = resultSet.getInt(3);
        double price = resultSet.getDouble(4);
        Date issuedAt = resultSet.getDate(5);
        boolean isActive = resultSet.getBoolean(6);
        Integer propertyId = resultSet.getInt(7);
        String address = resultSet.getString(8);
        String propertyType = resultSet.getString(9);
        double propertyValue = resultSet.getDouble(10);
        double propertyArea = resultSet.getDouble(11);

        return new LandRegistry(
                landRegistryId,
                propertyId,
                new Property(
                        propertyId,
                        address,
                        propertyType,
                        propertyValue,
                        propertyArea
                ),
                buyerSsn,
                sellerSsn,
                price,
                issuedAt,
                isActive
        );
    }

    @Override
    public ArrayList<LandRegistry> getAll() throws SQLException {
        ArrayList<LandRegistry> response = new ArrayList<>();
        ResultSet resultSet = dbService.ExecuteQuery("SELECT * FROM land_registry_property ORDER BY land_registry_id");

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
                "SELECT * FROM land_registry_property WHERE land_registry_id = ?");
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
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO land_registry(property_id, buyer_ssn, seller_ssn, price, " +
                        "issued_at, is_active) VALUES(?,?,?,?,?,?)");

        preparedStatement.setInt(1, landRegistry.getPropertyId());
        preparedStatement.setInt(2, landRegistry.getBuyerSsn());
        preparedStatement.setInt(3, landRegistry.getSellerSsn());
        preparedStatement.setDouble(4, landRegistry.getPrice());
        preparedStatement.setDate(5, landRegistry.getIssuedAt());
        preparedStatement.setBoolean(6, landRegistry.isActive());

        preparedStatement.executeUpdate();

        return landRegistry;
    }

    @Override
    public LandRegistry update(Integer id, UpdateLandRegistry newLandRegistry) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE land_registry SET property_id = ?, buyer_ssn = ?, seller_ssn = ?," +
                        "price = ?, issued_at = ?, is_active = ? WHERE property_id = ?");

        preparedStatement.setInt(1, newLandRegistry.propertyId());
        preparedStatement.setInt(2, newLandRegistry.buyerSsn());
        preparedStatement.setInt(3, newLandRegistry.sellerSsn());
        preparedStatement.setDouble(4, newLandRegistry.price());
        preparedStatement.setDate(5, newLandRegistry.issuedAt());
        preparedStatement.setBoolean(6, newLandRegistry.isActive());

        preparedStatement.executeUpdate();

        return new LandRegistry(
                id,
                newLandRegistry.propertyId(),
                newLandRegistry.buyerSsn(),
                newLandRegistry.sellerSsn(),
                newLandRegistry.price(),
                newLandRegistry.issuedAt(),
                newLandRegistry.isActive());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM land_registry WHERE land_registry_id = ?");
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<LandRegistry> getPropertyID(Integer buyerSSN) throws SQLException{
        ArrayList<LandRegistry> list = new ArrayList<>();
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM land_registry_property WHERE buyer_ssn = ?");
        preparedStatement.setInt(1, buyerSSN);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            list.add(mapResult(resultSet));
        }
        return list;
    }
    @Override
    public void FinalizeConnections() {
        this.dbService.FinalizeConnections();
    }

}
