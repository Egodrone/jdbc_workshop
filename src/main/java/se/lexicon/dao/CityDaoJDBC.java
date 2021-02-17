package se.lexicon.dao;



import se.lexicon.dao.db.DbConnection;
import se.lexicon.model.City;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CityDaoJDBC implements CityDao {

    @Override
    public City findById(int id) {
        String query = "select * from city where id = ?";
        City city = new City();

        try (
                PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                city.setId(resultSet.getInt(1));
                city.setName(resultSet.getString(2));
                city.setCountryCode(resultSet.getString(3));
                city.setDistrict(resultSet.getString(4));
                city.setPopulation(resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }



    @Override
    public List<City> findByCode(String code) {
        Connection connection = DbConnection.getConnection();
        List<City> ctList = new ArrayList<>();

        String query = "select * from city where CountryCode = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ctList.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ctList;
    }



    @Override
    public List<City> findByName(String name) {
        Connection connection = DbConnection.getConnection();
        List<City> ctList = new ArrayList<>();
        String query = "select * from city where `Name` = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ctList.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ctList;
    }



    @Override
    public List<City> findAll() {
        Connection connection = DbConnection.getConnection();
        List<City> ctList = new ArrayList<>();
        String query = "select * from city";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ctList.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ctList;
    }



    @Override
    public City add(City city) {
        Connection connection = DbConnection.getConnection();
        String query = "INSERT INTO city VALUES ? ? ? ? ?";

        return new City();
    }



    @Override
    public City update(City city) {

        return null;
    }



    @Override
    public int delete(City city) {
        Connection connection = DbConnection.getConnection();

        String query = "delete from city where id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, city.getId());
            int result = preparedStatement.executeUpdate();
            System.out.println("delete result: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
