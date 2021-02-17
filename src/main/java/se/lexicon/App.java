package se.lexicon;



import se.lexicon.dao.CityDaoJDBC;
import se.lexicon.model.City;
import java.util.List;



public class App
{
    public static void main( String[] args )
    {
        System.out.println( "JDBC Workshop" );

        System.out.println( "------------------------------" );
        CityDaoJDBC cityDao = new CityDaoJDBC();

        System.out.println("---------------------------------");
        City city = cityDao.findById(11);
        System.out.println("city = " + city);

        System.out.println("---------------------------------");
        String cCode = "AFG";
        List<City> cityList = cityDao.findByCode(cCode);
        cityList.forEach(System.out::println);

        System.out.println("---------------------------------");
        String cName = "Ede";
        List<City> findByNameList = cityDao.findByName(cName);
        findByNameList.forEach(System.out::println);

        System.out.println("---------------------------------");
        List<City> findAllList = cityDao.findAll();
        //findAllList.forEach(System.out::println);

        System.out.println("---------------------------------");
        City cityToAdd = cityDao.add(new City("sss", "NLD", "sss", 92020));
        //System.out.println(cityToAdd.getId());


        System.out.println("---------------------------------");
        City cityToDelete = new City(4079, "Rafah", "PSE", "Rafah", 92020);
        findAllList = cityDao.findAll();
        //findAllList.forEach(System.out::println);
    }
}
