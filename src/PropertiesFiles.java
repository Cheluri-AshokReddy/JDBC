import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
public class PropertiesFiles {

    public static void main(String[] args) {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Ashok Reddy\\Desktop\\Ak\\JavaDeveloper\\JDBC\\JDBCDemo\\db.properties");

            prop.load(fis);
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");
            String driver = prop.getProperty("db.driver");

            System.out.println("URL: " + url);
            System.out.println("User: " + user);
            System.out.println("Password: " + password);
            System.out.println("Driver: " + driver);

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(url, user, password);

            String Query = "SELECT * FROM students";
            PreparedStatement ps = connection.prepareStatement(Query);
            ResultSet rs = ps.executeQuery();

            System.out.printf("%-5s %-20s %-5s %-10s%n", "ID", "Name", "Age", "Marks");
            System.out.println("----------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-5d %-10.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("marks"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}