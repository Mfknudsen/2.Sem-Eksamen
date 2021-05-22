package business.persistence;

import business.entities.Material;
import business.exceptions.UserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlannerMapperTest {
    private final static String DATABASE = "fog";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static UserMapper userMapper;
    private static PlannerMapper plannerMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            userMapper = new UserMapper(database);
            plannerMapper = new PlannerMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @BeforeEach
    public void setUp() {

            // reset test database
            try ( Statement stmt = database.connect().createStatement() ) {
                stmt.execute("drop table if exists `user`" );
                stmt.execute("create table " + TESTDATABASE + ".`user` LIKE " + DATABASE + ".`user`;" );
                stmt.execute(
                    "insert into `user` values " +
                    "(1,'jens@somewhere.com','test1','jensen',5555,'customer'), " +
                    "(2,'ken@somewhere.com','test2','kensen',88888,'customer'), " +
                    "(3,'robin@somewhere.com','test3','batman',9999,'employee')");
            } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }

        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists `materials`" );
            stmt.execute("create table " + TESTDATABASE + ".`materials` LIKE " + DATABASE + ".`materials`;" );
            stmt.execute(
                    "insert into `materials` values " +
                            "(1,'25x200 mm. trykimp. Brædt',25,360,'understernbrædder til for & bag ende','spær'), " +
                            "(2,'25x200 mm. trykimp. Brædt',25,540,'understernbrædder til siderne','stolpe'), " +
                            "(3,'25x125 mm. trykimp. Brædt',25,700,'understernbrædder til forenden','rem')");
        } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }

        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists unit" );
            stmt.execute("create table " + TESTDATABASE + ".unit LIKE " + DATABASE + ".unit;" );
            stmt.execute(
                    "insert into unit values " +
                            "(1,'stk'), " +
                            "(2,'stk'), " +
                            "(3,'stk')");
        } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }

        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists `order`" );
            stmt.execute("create table " + TESTDATABASE + ".`order` LIKE " + DATABASE + ".`order`;" );
            stmt.execute(
                    "insert into `order` values " +
                            "(1,1,200,99), " +
                            "(2,1,550,660), " +
                            "(3,1,999,720)");
        } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }

        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists orderitems" );
            stmt.execute("create table " + TESTDATABASE + ".orderitems LIKE " + DATABASE + ".orderitems;" );
            stmt.execute(
                    "insert into orderitems values " +
                            "(1,1,1,1,888), " +
                            "(2,1,2,3,9999), " +
                            "(3,1,3,5,1000), " +
                            "(4,2,3,50,3), " +
                            "(5,2,2,30,33), " +
                            "(6,2,1,3,37), " +
                            "(7,3,1,18,40), " +
                            "(8,3,2,20,90), " +
                            "(9,3,3,13,70)");
        } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }

        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists status" );
            stmt.execute("create table " + TESTDATABASE + ".status LIKE " + DATABASE + ".status;" );
            stmt.execute(
                    "insert into status values " +
                            "(1,'not accepted'), " +
                            "(2,'not accepted'), " +
                            "(3,'not accepted')");
        } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(database);
    }

    public List<Material> CalculateStolper(float length, List<Material> list)
    {
//        return list<material> af stolpe med quantity sat til mere end 0
        List<Material> stolper = new ArrayList<>();
        int i = 0;
        while (!list.get(i).getCategory().equals("stolpe") && i < list.size()) {
            i++;
        }
        Material stolpe = list.get(i);
        stolpe.setQuantity((int) ((length / 301 + 1) * 2));
        stolper.add(stolpe);

        String expectedCategory = "stolpe";
        String actualCategory = list.get(i).getCategory();
        assertEquals(expectedCategory,actualCategory);

        int expectedID = 2;
        int actualID = list.get(i).getId();
        assertEquals(expectedID, actualID);

        return stolper;
    }

    public List<Material> CalculateRem(List<Material> list)
    {
//        return list<material> af rem med quantity sat til mere end 0
        List<Material> remmer = new ArrayList<>();
        int i = 0;
        while (!list.get(i).getCategory().equals("rem") && i < list.size()) {
            i++;
        }
        Material rem = list.get(i);
        rem.setQuantity(2);
        remmer.add(rem);

        String expectedCategory = "rem";
        String actualCategory = list.get(i).getCategory();
        assertEquals(expectedCategory,actualCategory);

        int expectedID = 3;
        int actualID = list.get(i).getId();
        assertEquals(expectedID, actualID);

        return remmer;
    }

    public List<Material> CalculateSpær(float length, List<Material> list)
    {
//        return list<material> af spær med quantity sat til mere end 0
        List<Material> xSpær = new ArrayList<>();
        int i = 0;
        while (!list.get(i).getCategory().equals("spær") && i < list.size()) {
            i++;
        }
        Material spær = list.get(i);
        spær.setQuantity((int) ((length / 55) + 1));
        xSpær.add(spær);

        String expectedCategory = "spær";
        String actualCategory = list.get(i).getCategory();
        assertEquals(expectedCategory,actualCategory);

        int expectedID = 1;
        int actualID = list.get(i).getId();
        assertEquals(expectedID, actualID);
        return xSpær;
    }

    @Test
    public void Calculate() throws UserException
    {
        List<Material> list = plannerMapper.listOfMaterials();
        List<Material> materials = new ArrayList<>();

        materials.addAll(CalculateStolper(800f, list));
        materials.addAll(CalculateRem(list));
        materials.addAll(CalculateSpær(600f, list));
        for (Material items:materials)
        {
            System.out.println(items.getId() + " " + items.getName() + " " + items.getLength() + " " + items.getQuantity() + items.getDescription());
        }

        float expectedLength1 = 540;
        float actualLength1 = materials.get(0).getLength();
        assertEquals(expectedLength1,actualLength1);

        float expectedLength2 = 700;
        float actualLength2 = materials.get(1).getLength();
        assertEquals(expectedLength2,actualLength2);

        float expectedLength3 = 360;
        float actualLength3 = materials.get(2).getLength();
        assertEquals(expectedLength3,actualLength3);
    }
}