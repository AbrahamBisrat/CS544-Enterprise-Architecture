package edu.miu.cs.cs544.exercise16_1.bank.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import edu.miu.cs.cs544.exercise16_1.bank.domain.Account;
import edu.miu.cs.cs544.exercise16_1.bank.domain.Customer;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    
    private static Configuration configuration = new Configuration();
    
    static {
        try {
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://172.17.18.6:3306/cs544Ex13?useSSL=false"); // Home server
            settings.put(Environment.USER, "reconov");
            settings.put(Environment.PASS, "reconov");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");

            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//            settings.put(Environment.HBM2DDL_AUTO, "create");
            settings.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(settings);
            
            @SuppressWarnings("rawtypes")
			List<Class> entityClasses = new ArrayList<>();
            
            entityClasses.add(Customer.class);
            entityClasses.add(Account.class);
            
            entityClasses.forEach(entityClass -> configuration.addAnnotatedClass(entityClass));

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
