package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String URL = "jdbc:mysql://localhost:3306/jdbc_hibernate";
    public static final String USER = "bestuser";
    public static final String PASSWORD = "bestuser";
    static Connection connection;

    public static Connection openConnection() {

        Driver driver;

        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e1) {
            System.out.println("������� �� �����������������");
            e1.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.err.println("���������� �� �����������");
            ex.printStackTrace();
        }
        return connection;
    }

    public static Session getCurrentSessionFromConfig() {
        Configuration config = new Configuration().addAnnotatedClass(User.class).configure("hibernate.cfg.xml");
        return config.buildSessionFactory().getCurrentSession();
    }

}
