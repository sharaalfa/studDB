package models.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bot on 23.02.17.
 */
public class Connector {

    private static final String USER = "artur";//Логин пользователя
    private static final String PASSWORD = "12345";//Пароль пользователя
    private static final String URL = "jdbc:postgresql://localhost:5432/students"; //+
            //"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";//URL адрес
    private static final String driver = "org.postgresql.Driver";//Имя драйвера

    public static Connection getConnection() throws SQLException {
        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
