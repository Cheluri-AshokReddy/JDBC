
import java.sql.*;

public class Databasemetadata {
    private static final String url="jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String user="root";
    private static final String password="Ashok@4849";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection=DriverManager.getConnection(url,user,password);
            DatabaseMetaData dbmd=connection.getMetaData();
            System.out.println("Database Product Name: " + dbmd.getDatabaseProductName());
            System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());

            System.out.println("Driver Information");
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());

            System.out.println("Connection Information");
            System.out.println("Database URL: " + dbmd.getURL());
            System.out.println("Database User Name: " + dbmd.getUserName());

            System.out.println("Maximum Connections");
            System.out.println("Max Connections: " + dbmd.getMaxConnections());

            System.out.println("Transaction Support");
            System.out.println("Supports Transactions: " + dbmd.supportsTransactions());

            System.out.println("Get other useful information");
            System.out.println("Supports Batch Updates: " + dbmd.supportsBatchUpdates());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
