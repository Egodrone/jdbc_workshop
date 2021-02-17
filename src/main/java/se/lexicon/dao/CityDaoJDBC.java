package se.lexicon.dao;



import se.lexicon.dao.db.DbConnection;
import se.lexicon.model.City;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CityDaoJDBC implements CityDao {

    @Override
    public City findById(int id) {
        String query = "SELECT * FROM city WHERE id = ?";
        City city = new City();

        try (
                PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query)
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
        List<City> ctList = new ArrayList<>();

        String query = "SELECT * FROM city WHERE CountryCode = ?";
        try (
            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query)
        ) {
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
        List<City> ctList = new ArrayList<>();
        String query = "SELECT * FROM city WHERE `Name` = ?";

        try (
            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query)
        ) {
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
        List<City> ctList = new ArrayList<>();
        String query = "SELECT * FROM city";

        try (
            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query)
        ) {
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
        System.out.printf(city.getName());

        String query = "INSERT INTO city(`Name`, CountryCode, District, Population) VALUES('"
                + city.getName() + "', '" + city.getCountryCode() + "','"
                + city.getDistrict() + "'," + city.getPopulation() + ")";

        City newCity = new City();

        try(
                PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query)
                ) {
            int key = preparedStatement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            city.setId(key);
            newCity = city;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return newCity;
    }



    @Override
    public City update(City city) {
        System.out.printf(city.getName());

        String query = "UPDATE city SET `Name` = '"
                + city.getName() + "', CountryCode = '"
                + city.getCountryCode() + "', District = '"
                + city.getDistrict() + "', Population = "
                + city.getPopulation() + " WHERE Id = " + city.getId();

        City newCity = new City();

        try(
                PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query)
        ) {
            int key = preparedStatement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            city.setId(key);
            newCity = city;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return newCity;
    }



    @Override
    public int delete(City city) {

        String query = "DELETE FROM city WHERE id = ?";
        int result = 0;

        try (
                PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(query)
        ) {
            preparedStatement.setInt(1, city.getId());
            result = preparedStatement.executeUpdate();
            System.out.println("delete result: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
