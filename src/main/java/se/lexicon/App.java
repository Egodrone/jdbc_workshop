package se.lexicon;



import se.lexicon.dao.CityDaoJDBC;
import se.lexicon.model.City;
import java.util.ArrayList;
import java.util.List;



public class App
{
    public static void main( String[] args )
    {
        System.out.println( "JDBC Workshop" );

        System.out.println( "------------------------------" );
        CityDaoJDBC jbc = new CityDaoJDBC();
        City ct = new City();

        System.out.println("---------------------------------");
        ct = jbc.findById(11);
        //System.out.println(ct.toString());



        System.out.println("---------------------------------");
        //2
        List<City> ctList = new ArrayList<>();
        String cCode = "AFG";
        ctList = jbc.findByCode(cCode);
        System.out.println(ctList.toString());

        System.out.println("---------------------------------");
        String cName = "Ede";
        List<City> findByNameList = jbc.findByName(cName);
        System.out.println(findByNameList.toString());


        System.out.println("---------------------------------");
        List<City> findAllList = jbc.findAll();
        System.out.println(findAllList.toString());

        System.out.println("---------------------------------");
        City cityToDelete = new City(4079, "Rafah", "PSE", "Rafah", 92020);
        findAllList = jbc.findAll();
        System.out.println(findAllList.toString());
    }
}
