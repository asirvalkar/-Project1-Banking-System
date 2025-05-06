import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bankingsystem";
        String user = "root";
        String password = "root";

        return DriverManager.getConnection(url, user, password);
        
    }
}
