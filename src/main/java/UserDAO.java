
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.cts.airticketreservation.model.User;
public class UserDAO {
    private static String USER = "root";
    private static String PASS = "root123";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/beecological?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static boolean checkUsername(String username) {
        Statement stmt = null;
        Connection conn = null;
        int res = 1;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            res = Queries.verifyUsernameAvailable(stmt, username);
            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(res == 1) {
            return false;
        }
        return true;
    }
    public static void saveUser(User instance) {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            Queries.insertUser(stmt, instance);
            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean verifyLogin(User instance) {
        Statement stmt = null;
        Connection conn = null;
        int res = 0;
        try {
            //caricamento driver mysql
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            res = Queries.verifyUserRegistered(stmt, instance);
            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (res == 0) {
            return false;   //utente immesso non esiste
        }
        return true;
    }
}
