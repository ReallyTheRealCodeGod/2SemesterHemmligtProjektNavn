package junittest;

import models.Customer;
import org.junit.jupiter.api.BeforeEach;
import repositories.*;
import models.Accessory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import utility.JDBCConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DatabaseTest {
    private static Properties prop;
    AccessoryRepository acc;
    AutocamperRepository auto;
    BillRepository bill;
    CustomerRepository cus;
    MaintenanceReportRepository maintenanceRep;
    RentalRepository rent;

    @BeforeAll()
    public static void testConditions(){
        try {
            prop = new Properties();
            prop.load(new FileInputStream("C:\\Users\\123al\\Desktop\\Programming-Design\\coding\\Skole\\Mandatory\\2SemesterHemmligtProjektNavn\\src\\main\\resources\\application.properties"));
            ReflectionTestUtils.setField(JDBCConnection.class, "user", prop.getProperty("db.user"));
            ReflectionTestUtils.setField(JDBCConnection.class, "password", prop.getProperty("db.password"));
            ReflectionTestUtils.setField(JDBCConnection.class, "url", prop.getProperty("db.url"));
        }catch(FileNotFoundException file){
            file.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    @BeforeEach
    public void initilizeRepos(){
        acc = new AccessoryRepository();
        auto = new AutocamperRepository();
        bill = new BillRepository();
        cus = new CustomerRepository();
        maintenanceRep = new MaintenanceReportRepository();
        rent = new RentalRepository();
    }

    @Test
    public void databaseInitilizationTest(){
        try{
        assertTrue(JDBCConnection.getDatabaseConnection().isValid(100), "Could not connect to database");
    }catch(SQLException sql){
        sql.printStackTrace();
        }
    }

    @Test
    public void getEntities(){
        assertTrue(acc.getById(1) != null, "accessory could not be Created");
        assertTrue(auto.getById(1) != null, "accessory could not be Created");
        assertTrue(bill.getById(1) != null, "accessory could not be Created");
        assertTrue(cus.getById(1) != null, "accessory could not be Created");
        //assertTrue(maintenance.getById(1) != null, "accessory could not be Created");
        assertTrue(rent.getById(1) != null, "accessory could not be Created");
    }

    @Test
    public void getAllEntities(){
        acc = new AccessoryRepository();
        Accessory[] model = acc.getAll();
        for(Accessory m: model) {
            assertNotNull(m.getDescription(), "accessory could not be loaded");
        }
    }

    @Test
    public void addEntity(){
        acc = new AccessoryRepository();
        Accessory a = new Accessory(100, "name", "description", 1, 1);
        assertNotNull(acc.create(a), "could not create entity");
        System.out.println(a);
    }

    @Test
    public void updateEntity(){
        acc = new AccessoryRepository();
        Accessory a = new Accessory(12, "hello", "this is a test accessory", 1,1);
        assertTrue(acc.update(a), "could not create entity");
        System.out.println(a);
    }
}
