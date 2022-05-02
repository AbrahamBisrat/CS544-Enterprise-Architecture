package edu.miu.cs.cs544.exercise07_1;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private static Configuration configuration = new Configuration();
    private static SessionFactory sessionFactory;

    public static Session getSession(List<Class> entityClasses) {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://172.17.18.6:3306/cs544Ex7?useSSL=false"); // Home server
                settings.put(Environment.USER, "reconov");
                settings.put(Environment.PASS, "reconov");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//                settings.put(Environment.FORMAT_SQL, "true");
//                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                
                entityClasses.forEach(entityClass -> configuration.addAnnotatedClass(entityClass));

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return sessionFactory.getCurrentSession();
    }
    
}