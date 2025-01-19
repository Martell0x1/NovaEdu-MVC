package Controller;

import util.Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static Connection connection;
    private static DBConnect instance;

    private DBConnect(){}

    private static void Create() {
        try {
            DriverManager.setLoginTimeout(10);
            String url = Config.getProperty("db.url");
            String username = Config.getProperty("db.username");
            String password = Config.getProperty("db.password");
            Class.forName(Config.getProperty("db.driver"));

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("[*] Connection Established Successfully !");
        } catch (Exception e) {
            throw new RuntimeException("-- Couldn't Connect To The DB-Server , Check Your Connection");
        }
    }
    public static synchronized Connection getInstance(){
        if(instance == null && connection == null) {
            instance = new DBConnect();
            Create();
        }
        return connection;
    }
}
