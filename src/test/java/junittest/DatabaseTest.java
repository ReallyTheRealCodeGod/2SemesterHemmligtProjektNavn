package junittest;

import Repositories.AccessoryRepository;
import Repositories.Repository;
import models.Accessory;
import models.Autocamper;
import models.Model;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import utility.JDBCConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

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
        Repository<Accessory> ar = new Repository<>("accessory_type", Accessory.class);
        Accessory model = ar.getById(4);
        System.out.println(model.toString());
        assertNotNull(model.getDescription(), "accessory could not be loaded");
    }

    @Test
    public void getAllEntities(){
        Repository<Accessory> ar = new Repository<>("accessory_type", Accessory.class);
        ArrayList<Accessory> model = ar.getAll();
        for(Accessory m: model) {
            assertNotNull(m.getDescription(), "accessory could not be loaded");
        }
    }

    @Test
    public void addEntity(){
        Repository<Accessory> ra = new Repository<>("accessory_type", Accessory.class);
        Accessory a = new Accessory(0,100, "name", "description");
        assertTrue(ra.insert(a) != 0, "could not create entity");
        System.out.println(a);
    }

    @Test
    public void updateEntity(){
        Repository<Accessory> ra = new Repository<>("accessory_type", Accessory.class);
        Accessory a = new Accessory(12,12, "hello", "descridsdsption");
        assertTrue(ra.update(a), "could not create entity");
        System.out.println(a);
    }
}
