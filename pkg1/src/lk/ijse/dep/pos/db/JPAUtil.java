package lk.ijse.dep.pos.db;

import lk.ijse.dep.crypto.DEPCrypt;
import lk.ijse.dep.pos.entity.Customer;
import lk.ijse.dep.pos.entity.Item;
import lk.ijse.dep.pos.entity.OrderDetail;
import lk.ijse.dep.pos.entity.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPAUtil {

    private static EntityManagerFactory emf = buildEntityManagerFactory();
    private static String username;
    private static String password;
    private static String host;
    private static String port;
    private static String database;


    private static EntityManagerFactory buildEntityManagerFactory() {

        //File propFile  = new File("src/application.properties");
        Properties properties = new Properties();

        try (InputStream fis = JPAUtil.class.getResourceAsStream("/application.properties")){
            properties.load(fis);
        }catch (Exception e){
            Logger.getLogger("lk.ijse.dep.pos.Hibernate.HibernateUtil").log(Level.SEVERE,null,e);
            System.exit(2);
        }
        username = DEPCrypt.decode(properties.getProperty("javax.persistence.jdbc.user"),"dep4");
        password = DEPCrypt.decode(properties.getProperty("javax.persistence.jdbc.password"),"dep4");
        host = properties.getProperty("ijse.dep.ip");
        port = properties.getProperty("ijse.dep.port");
        database = properties.getProperty("ijse.dep.db");

       return Persistence.createEntityManagerFactory("dep4",properties);
    }


    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getHost() {
        return host;
    }

    public static String getPort() {
        return port;
    }

    public static String getDatabase() {
        return database;
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }
}
