package junittest;

import Repositories.AccessoryRepository;
import models.Accessory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import utility.JDBCConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DatabaseTest {
    private static Properties prop;

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
        AccessoryRepository ar = new AccessoryRepository();
        Accessory model = null;
        model = ar.getById(4);
        System.out.println(model.toString());
        assertNotNull(model.getDescription(), "accessory could not be loaded");
    }

    @Test
    public void getAllEntities(){
        AccessoryRepository ar = new AccessoryRepository();
        Accessory[] model = ar.getAll();
        for(Accessory m: model) {
            assertNotNull(m.getDescription(), "accessory could not be loaded");
        }
    }

    @Test
    public void addEntity(){
        AccessoryRepository ra = new AccessoryRepository();
        Accessory a = new Accessory(0, 100, "name", "description", 2);
        assertNotNull(ra.create(a), "could not create entity");
        System.out.println(a);
    }

    @Test
    public void updateEntity(){
        AccessoryRepository ra = new AccessoryRepository();
        Accessory a = new Accessory(3,12, "hello", "this is a test accessory", 10);
        assertTrue(ra.update(a), "could not create entity");
        System.out.println(a);
    }
}
