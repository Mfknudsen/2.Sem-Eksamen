package business.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class PlannerMapperTest {
    private final static String DATABASE = "fog";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static UserMapper userMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            userMapper = new UserMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @BeforeEach
    public void setUp() {

            // reset test database
            try ( Statement stmt = database.connect().createStatement() ) {
                stmt.execute("drop table if exists user" );
                stmt.execute("create table " + TESTDATABASE + ".user LIKE " + DATABASE + ".user;" );
                stmt.execute(
                    "insert into users values " +
                    "(1,'jens@somewhere.com','jensen','customer'), " +
                    "(2,'ken@somewhere.com','kensen','customer'), " +
                    "(3,'robin@somewhere.com','batman','employee')");
            } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }

        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists materials" );
            stmt.execute("create table " + TESTDATABASE + ".materials LIKE " + DATABASE + ".materials;" );
            stmt.execute(
                    "insert into materials values " +
                            "(1,'25x200 mm. trykimp. Brædt',25f,360,'understernbrædder til for & bag ende'), " +
                            "(2,'25x200 mm. trykimp. Brædt',25f,540,'understernbrædder til siderne'), " +
                            "(3,'25x125 mm. trykimp. Brædt',25f,360,'understernbrædder til forenden')");
        } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }

        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists unit" );
            stmt.execute("create table " + TESTDATABASE + ".unit LIKE " + DATABASE + ".unit;" );
            stmt.execute(
                    "insert into unit values " +
                            "(1,stk), " +
                            "(2,stk), " +
                            "(3,stk)");
        } catch (SQLException ex) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }

        try ( Statement stmt = database.connect().createStatement() ) {
            stmt.execute("drop table if exists order" );
            stmt.execute("create table " + TESTDATABASE + ".order LIKE " + DATABASE + ".order;" );
            stmt.execute(
                    "insert into order values " +
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
                            "(1,1,1,1), " +
                            "(2,1,2,3), " +
                            "(3,1,3,5), " +
                            "(4,2,3,50), " +
                            "(5,2,2,30), " +
                            "(6,2,1,3), " +
                            "(7,3,1,18), " +
                            "(8,3,2,20), " +
                            "(9,3,3,13)");
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

    @Test
    public void CalculateSpær()
    {

    }

    @Test
    public void CalculateRem()
    {

    }

    @Test
    public void CalculateStolper()
    {

    }

}