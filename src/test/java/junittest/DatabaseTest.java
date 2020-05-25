package junittest;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import utility.JDBCConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


@ContextConfiguration(classes = JDBCConnection.class)
@TestPropertySource("/resources/application.properties")
public class DatabaseTest {
    Properties prop;

    @BeforeAll()
    public void testConditions(){
        try {
            prop = new Properties();
            prop.load(new FileInputStream("application.properties"));
        }catch(FileNotFoundException file){
            file.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    @Test
    public void databaseInitilizationTest(){
        ReflectionTestUtils.setField(JDBCConnection.class, "user", prop.getProperty("db.user"));
        ReflectionTestUtils.setField(JDBCConnection.class, "password", prop.getProperty("db.password"));
        ReflectionTestUtils.setField(JDBCConnection.class, "url", prop.getProperty("db.url"));
        assert(JDBCConnection.getDatabaseConnection() != null);
    }
}
